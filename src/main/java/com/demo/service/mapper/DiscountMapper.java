package com.demo.service.mapper;

import com.demo.domain.Discount;
import com.demo.service.util.ConverterUtil;
import com.demo.web.vm.DiscountVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {ConverterUtil.class})
public interface DiscountMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startAt",
            expression = "java(ConverterUtil.convertStringToInstant(discountVM.startAt(), discountVM.timezone()))")
    @Mapping(target = "endAt",
            expression = "java(ConverterUtil.convertStringToInstant(discountVM.endAt(), discountVM.timezone()))")
    Discount toEntity(DiscountVM discountVM);
}
