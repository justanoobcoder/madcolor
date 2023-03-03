package com.demo.service;

import com.demo.domain.*;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.OrderRepository;
import com.demo.service.dto.CustomerDto;
import com.demo.service.dto.OrderTempDto;
import com.demo.service.mapper.CustomerMapper;
import com.demo.service.mapper.ProductMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final RankService rankService;
    private final CacheManager cacheManager;
    private final ProductMapper productMapper;

    private String getRandomOrderCode() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        int leftLimit = 65;
        int rightLimit = 90;
        int targetStringLength = 4;
        Random random = new Random();

        String randomString = random
                .ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return date + randomString;
    }

    public OrderTempDto createTempOrder() {
        String orderId = getRandomOrderCode();
        OrderTempDto order = new OrderTempDto();
        order.setId(orderId);
        Objects.requireNonNull(cacheManager.getCache("orders")).put(orderId, order);
        return order;
    }

    @Cacheable(value = "orders", key = "#id")
    public OrderTempDto getTempOrderById(String id) {
        OrderTempDto o = Objects.requireNonNull(cacheManager.getCache("orders")).get(id, OrderTempDto.class);
        if (o == null) {
            throw new ResourceNotFoundException("Order with id " + id + " not found");
        }
        return o;
    }

    @CachePut(value = "orders", key = "#id")
    public OrderTempDto addProductToTempOrder(String id, String sku, Integer quantity) {
        OrderTempDto order = getTempOrderById(id);
        Product product = productService.getProductBySku(sku);
        order.addToCart(productMapper.toDto(product), quantity);
        return order;
    }

    @CacheEvict(value = "orders", key = "#id")
    public PaidOrder checkoutOrder(String id, String telephone) {
        Customer customer;
        if (telephone != null) {
            customer = customerService.getCustomerByTelephone(telephone);
        } else {
            customer = null;
        }
        Order order = new Order();
        order.setId(id);
        if (customer != null) {
            order.setCustomer(customer);
        }
        order.setEmployee(employeeService.getCurrentEmployeeLogin());
        OrderTempDto orderTemp = getTempOrderById(id);
        order.setTotalPrice(0.0);
        List<OrderItem> orderItems = orderTemp.getCart().values().stream()
                        .map(e -> {
                            OrderItem o = new OrderItem();
                            o.setOrder(order);
                            Product p = productService.getProductBySku(e.getProduct().getSku());
                            Discount discount = p.getDiscount();
                            double cost = p.getPrice() - p.getPrice() * ((discount == null ? 0 : discount.getPercent()) / 100.0);
                            o.setCost(cost * e.getQuantity());
                            order.setTotalPrice(order.getTotalPrice() + o.getCost());
                            o.setProduct(p);
                            o.setQuantity(e.getQuantity());
                            p.setQuantity(p.getQuantity() - o.getQuantity());
                            return o;
                        }).toList();
        order.setOrderItems(orderItems);
        PaidOrder paidOrder = new PaidOrder();
        if (customer != null) {
            order.setTotalPrice(order.getTotalPrice() - order.getTotalPrice() * (rankService.getRankByCustomerPoint(customer.getPoint()).getDiscountPercent() / 100.0));
            paidOrder.setCustomer(customerMapper.toDto(customer));
        }
        Order insertedOrder = orderRepository.save(order);
        paidOrder.setId(insertedOrder.getId());
        paidOrder.setEmployee(insertedOrder.getEmployee().getFullName());
        paidOrder.setCreatedAt(insertedOrder.getCreatedAt());
        paidOrder.setTotal(insertedOrder.getTotalPrice());
        return paidOrder;
    }

    @Setter
    @Getter
    public static class PaidOrder {
        private String id;
        private CustomerDto customer;
        private Instant createdAt;
        private String employee;
        private Double total;
    }
}
