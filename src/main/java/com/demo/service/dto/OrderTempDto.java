package com.demo.service.dto;

import com.demo.domain.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class OrderTempDto implements Serializable {
    private static final Long serialVersionUID = 1L;
    private String id;
    private Map<String, OrderItemTempDto> cart = new HashMap<>();

    public void addToCart(ProductDto product, Integer quantity) {
        if (cart.containsKey(product.getSku())) {
            OrderItemTempDto orderItem = cart.get(product.getSku());
            orderItem.setQuantity(orderItem.getQuantity() + ((quantity == null) ? 1 : quantity));
        } else {
            OrderItemTempDto orderItem = new OrderItemTempDto();
            orderItem.setProduct(product);
            orderItem.setQuantity((quantity == null) ? 1 : quantity);
            cart.put(product.getSku(), orderItem);
        }
    }
}
