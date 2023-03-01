package com.demo.service.mapper;

import com.demo.domain.Customer;
import com.demo.exception.ResourceNotFoundException;
import com.demo.service.GenderService;
import com.demo.service.dto.CustomerDto;
import com.demo.web.vm.CustomerVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        imports = {GenderService.class, ResourceNotFoundException.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CustomerMapper {
    @Mapping(target = "gender",
            expression = "java(genderService.getGenderByName(customerVM.gender()))")
    Customer toEntity(CustomerVM customerVM, GenderService genderService);

    @Mapping(target = "gender",
            expression = "java(genderService.getGenderByName(customerVM.gender()))")
    void map(CustomerVM customerVM, @MappingTarget Customer customer, GenderService genderService);

    @Mapping(target = "rankName", source = "rank.name")
    @Mapping(target = "discountPercent", source = "rank.discountPercent")
    @Mapping(target = "genderName", source = "customer.gender.name")
    CustomerDto toDto(Customer customer);
}
