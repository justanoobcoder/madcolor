package com.demo.repository;

import com.demo.domain.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
    @Query("SELECT r FROM Rank r WHERE r.point <= :point ORDER BY r.point DESC")
    Optional<Rank> findRankByPoint(Integer point);
}
