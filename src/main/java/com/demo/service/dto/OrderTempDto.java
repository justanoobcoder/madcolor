package com.demo.service.dto;

import com.demo.exception.ResourceBadRequestException;
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
            int n = orderItem.getQuantity() + ((quantity == null) ? 1 : quantity);
            if (n > product.getQuantity()) {
                throw new ResourceBadRequestException("Requested quantity is not available");
            }
            orderItem.setQuantity(n);
        } else {
            OrderItemTempDto orderItem = new OrderItemTempDto();
            orderItem.setProduct(product);
            int n = (quantity == null) ? 1 : quantity;
            if (n > product.getQuantity()) {
                throw new ResourceBadRequestException("Requested quantity is not available");
            }
            orderItem.setQuantity(n);
            cart.put(product.getSku(), orderItem);
        }
    }
}
