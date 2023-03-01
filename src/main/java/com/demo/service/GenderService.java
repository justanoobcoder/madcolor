package com.demo.service;

import com.demo.domain.Gender;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.GenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class GenderService {
    private final GenderRepository genderRepository;

    public Gender getGenderByName(String name) {
        return genderRepository
                .findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Gender " + name + " not found"));
    }
}
