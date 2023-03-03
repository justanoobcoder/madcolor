package com.demo.web;

import com.demo.service.OrderService;
import com.demo.service.dto.OrderTempDto;
import com.demo.web.api.OrderResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderResourceImpl implements OrderResource {
    private final OrderService orderService;

    @Override
    public ResponseEntity<OrderTempDto> createOrder() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.createTempOrder());
    }

    @Override
    public ResponseEntity<OrderTempDto> addProductToOrder(String orderId, String sku, Integer quantity) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.addProductToTempOrder(orderId, sku, quantity));
    }

    @Override
    public ResponseEntity<OrderService.PaidOrder> checkoutOrder(String orderId, String telephone) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.checkoutOrder(orderId, telephone));
    }
}
