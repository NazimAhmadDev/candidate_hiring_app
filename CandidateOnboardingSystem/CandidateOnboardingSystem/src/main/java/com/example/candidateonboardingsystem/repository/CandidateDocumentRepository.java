package com.example.candidateonboardingsystem.repository;

import com.example.candidateonboardingsystem.model.CandidateDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateDocumentRepository extends JpaRepository<CandidateDocument, Long> {
    Optional<Object> findByCandidateId(Long candidateId);
}
