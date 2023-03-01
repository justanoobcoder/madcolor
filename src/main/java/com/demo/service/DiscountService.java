package com.demo.service;

import com.demo.domain.Discount;
import com.demo.exception.ResourceAlreadyExistsException;
import com.demo.repository.DiscountRepository;
import com.demo.service.mapper.DiscountMapper;
import com.demo.web.vm.DiscountVM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class DiscountService {
    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    public Discount add(DiscountVM discountVM) {
        if (discountRepository.existsByName(discountVM.name())) {
            throw new ResourceAlreadyExistsException("Discount with name " + discountVM.name() + " already existed");
        }
        Discount discount = discountMapper.toEntity(discountVM);
        return discountRepository.save(discount);
    }
}
