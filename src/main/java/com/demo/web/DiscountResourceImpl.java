package com.demo.web;

import com.demo.domain.Discount;
import com.demo.service.DiscountService;
import com.demo.web.api.DiscountResource;
import com.demo.web.vm.DiscountVM;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DiscountResourceImpl implements DiscountResource {
    private final DiscountService discountService;
    @Override
    public ResponseEntity<Discount> createDiscount(DiscountVM discountVM) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(discountService.add(discountVM));
    }
}
