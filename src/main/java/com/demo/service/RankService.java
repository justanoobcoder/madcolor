package com.demo.service;

import com.demo.domain.Rank;
import com.demo.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class RankService {
    private final RankRepository rankRepository;

    public Rank getRankByCustomerPoint(Integer point) {
        return rankRepository
                .findRankByPoint(point)
                .orElse(null);
    }
}
