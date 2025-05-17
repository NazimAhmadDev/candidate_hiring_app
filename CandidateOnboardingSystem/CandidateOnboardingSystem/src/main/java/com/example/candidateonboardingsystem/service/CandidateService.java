package com.example.candidateonboardingsystem.service;


import com.example.candidateonboardingsystem.dto.BankInfoRequest;
import com.example.candidateonboardingsystem.dto.EducationalInfoRequest;
import com.example.candidateonboardingsystem.dto.PersonalInfoRequest;
import com.example.candidateonboardingsystem.model.BankInfo;
import com.example.candidateonboardingsystem.model.CandidateDocument;
import com.example.candidateonboardingsystem.model.CandidateEducation;
import com.example.candidateonboardingsystem.model.CandidateModel;
import com.example.candidateonboardingsystem.repository.CandidateBankInfoRepository;
import com.example.candidateonboardingsystem.repository.CandidateDocumentRepository;
import com.example.candidateonboardingsystem.repository.CandidateEducationRepository;
import com.example.candidateonboardingsystem.repository.CandidateModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class CandidateService {

    private final CandidateModelRepository candidateModelRepository;
    private final CandidateBankInfoRepository bankInfoRepository;
    private final CandidateEducationRepository educationRepository;
    private final CandidateDocumentRepository documentRepository;



    public CandidateService(CandidateModelRepository candidateModelRepository, CandidateBankInfoRepository bankInfoRepository, CandidateEducationRepository educationRepository,CandidateDocumentRepository documentRepository) {
        this.candidateModelRepository = candidateModelRepository;
        this.bankInfoRepository = bankInfoRepository;
        this.educationRepository = educationRepository;
        this.documentRepository = documentRepository;
    }

    // Get all hired candidates
    public List<CandidateModel> getHiredCandidates() {
        return candidateModelRepository.findByStatus(CandidateModel.Status.valueOf("ONBOARDED"));
    }



    // Get candidates by id
    public List<CandidateModel> getAllCandidates(Long id) {
        return candidateModelRepository.findAll();
    }


    // count of candidates
    public long countCandidates() {
        return candidateModelRepository.count();
    }



    public boolean updateStatus(Long id, String status) {
        Optional<CandidateModel> candidateOpt = candidateModelRepository.findById(id);
        if (candidateOpt.isPresent()) {
            CandidateModel candidate = candidateOpt.get();
            candidate.setStatus(CandidateModel.Status.valueOf(status));
            candidateModelRepository.save(candidate);
            return true;
        }
        return false;
    }

    public boolean updateOnboardingStatus(Long id, String onboardingStatus) {
        Optional<CandidateModel> candidateOpt = candidateModelRepository.findById(id);
        if (candidateOpt.isPresent()) {
            CandidateModel candidate = candidateOpt.get();
            candidate.setOnboardingStatus(CandidateModel.OnboardingStatus.valueOf(onboardingStatus));
            candidateModelRepository.save(candidate);
            return true;
        }
        return false;
    }


    public boolean updatePersonalInfo(Long id, PersonalInfoRequest request) {
        Optional<CandidateModel> optionalCandidate = candidateModelRepository.findById(id);
        if (optionalCandidate.isPresent()) {
            CandidateModel candidate = optionalCandidate.get();
            candidate.setFirst_name(request.getFirstName());
            candidate.setLast_name(request.getLastName());
            candidate.setEmail(request.getEmail());
            candidate.setPhone_no(request.getPhoneNo());
            candidate.setUpdated_at(LocalDateTime.now());
            candidateModelRepository.save(candidate);
            return true;
        }
        return false;
    }


    public boolean updateBankInfo(Long id, BankInfoRequest request) {
        Optional<BankInfo> optionalBankInfo = bankInfoRepository.findByCandidateId(id);
        if (optionalBankInfo.isPresent()) {
            BankInfo bankInfo = optionalBankInfo.get();
            bankInfo.setBank_name(request.getBankName());
            bankInfo.setAccount_number(request.getAccountNumber());
            bankInfo.setIfsc_code(request.getIfscCode());
            bankInfoRepository.save(bankInfo);
            return true;
        }
        return false;
    }


    public boolean updateEducationalInfo(Long id, EducationalInfoRequest request) {
        Optional<CandidateEducation> optionalEducation = educationRepository.findByCandidateId(id);
        if (optionalEducation.isPresent()) {
            CandidateEducation education = optionalEducation.get();
            education.setDegree(request.getHighestDegree());
            education.setInstitution(request.getUniversity());
            education.setPassing_year(request.getYearOfGraduation());
            educationRepository.save(education);
            return true;
        }
        return false;
    }









    public CandidateDocument uploadDocument(Long candidateId, MultipartFile file) throws IOException {
        CandidateDocument document = new CandidateDocument();
        document.setDocument_type(file.getOriginalFilename());
        document.setFile_url(document.getFile_url());
        document.setFile_verified(false);

        return documentRepository.save(document);
    }

    public boolean isFileVerified(Long candidateId) {
        CandidateDocument doc = (CandidateDocument) documentRepository.findByCandidateId(candidateId)
                .orElseThrow(() -> new RuntimeException("Document not found for candidate ID: " + candidateId));

        return doc.isFile_verified(); // Returns the boolean value
    }

}

