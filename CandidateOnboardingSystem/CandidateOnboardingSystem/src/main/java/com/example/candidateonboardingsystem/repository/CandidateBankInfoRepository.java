package com.example.candidateonboardingsystem.repository;

import com.example.candidateonboardingsystem.model.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CandidateBankInfoRepository extends JpaRepository<BankInfo, Long> {
    Optional<BankInfo> findByCandidateId(Long candidateId);
}
