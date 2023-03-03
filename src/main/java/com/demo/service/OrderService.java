package com.demo.service;

import com.demo.domain.Product;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.OrderRepository;
import com.demo.service.dto.OrderTempDto;
import com.demo.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
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
        return Objects.requireNonNull(cacheManager.getCache("orders")).get(id, OrderTempDto.class);
    }

    @CachePut(value = "orders", key = "#id")
    public OrderTempDto addProductToTempOrder(String id, String sku, Integer quantity) {
        OrderTempDto order = getTempOrderById(id);
        Product product = productService.getProductBySku(sku);
        order.addToCart(productMapper.toDto(product), quantity);
        return order;
    }
}
