package com.demo.service.mapper;

import com.demo.annotation.IgnoreAuditProperties;
import com.demo.domain.Customer;
import com.demo.exception.ResourceNotFoundException;
import com.demo.service.GenderService;
import com.demo.service.dto.CustomerDto;
import com.demo.web.vm.CustomerVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = {GenderService.class, ResourceNotFoundException.class})
public interface CustomerMapper {
    //CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "gender",
            expression = "java(genderService.getGenderByName(customerVM.gender()))")
    @IgnoreAuditProperties
    Customer toEntity(CustomerVM customerVM, GenderService genderService);

    @Mapping(target = "gender",
            expression = "java(genderService.getGenderByName(customerVM.gender()))")
    @IgnoreAuditProperties
    void map(CustomerVM customerVM, @MappingTarget Customer customer, GenderService genderService);

    @Mapping(target = "rankName", source = "rank.name")
    @Mapping(target = "rankDiscount", source = "rank.discount")
    @Mapping(target = "genderName", source = "customer.gender.name")
    CustomerDto toDto(Customer customer);
}
