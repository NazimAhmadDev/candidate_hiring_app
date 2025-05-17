package com.example.candidateonboardingsystem.repository;

import com.example.candidateonboardingsystem.model.CandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateModelRepository extends JpaRepository<CandidateModel, Long> {

    List<CandidateModel> findByStatus(CandidateModel.Status status);
    //Optional<CandidateModel> findById(Long id);

}