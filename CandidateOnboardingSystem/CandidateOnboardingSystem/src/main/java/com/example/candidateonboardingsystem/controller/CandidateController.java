package com.example.candidateonboardingsystem.controller;

import com.example.candidateonboardingsystem.dto.*;
import com.example.candidateonboardingsystem.model.CandidateDocument;
import com.example.candidateonboardingsystem.model.CandidateModel;
import com.example.candidateonboardingsystem.repository.CandidateDocumentRepository;
import com.example.candidateonboardingsystem.service.CandidateService;
import com.example.candidateonboardingsystem.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;
    private final CandidateDocumentRepository candidateDocumentRepository;


    public CandidateController(CandidateService candidateService, CandidateDocumentRepository candidateDocumentRepository) {
        this.candidateService = candidateService;
        this.candidateDocumentRepository = candidateDocumentRepository;
    }

    @GetMapping("/hired")
    public List<CandidateModel> getHiredCandidates() {
        return candidateService.getHiredCandidates();
    }



    @GetMapping("/onboarded")
    public List<CandidateModel> getAllOnBoardedCandidates() {
        return candidateService.getHiredCandidates();
    }


    @GetMapping("/count")
    public long getCandidateCount() {
        return candidateService.countCandidates();
    }



    @PostMapping("/{id}/status")
    public ResponseEntity<String> updateCandidateStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest request) {
        try {
            CandidateModel.Status status = CandidateModel.Status.valueOf(request.getStatus().toUpperCase());
            boolean updated = candidateService.updateStatus(id, String.valueOf(status));
            if (updated) {
                return ResponseEntity.ok("Status updated");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidate not found");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid status value");
        }
    }



    @PutMapping("/{id}/onboard-status")
    public ResponseEntity<String> updateOnboardingStatus(@PathVariable Long id,
                                                         @RequestBody OnboardingStatusUpdateRequest request) {
        try {
            CandidateModel.OnboardingStatus onboardingStatus = CandidateModel.OnboardingStatus.valueOf(request.getOnboardingstatus().toUpperCase());
            boolean updated = candidateService.updateOnboardingStatus(id, String.valueOf(onboardingStatus));
            if (updated) {
                return ResponseEntity.ok("Onboarding status updated");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidate not found");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid onboarding status value");
        }
    }











    @PutMapping("/{id}/personal-info")
    public ResponseEntity<String> updatePersonalInfo(@PathVariable Long id, @RequestBody PersonalInfoRequest request) {
        boolean updated = candidateService.updatePersonalInfo(id, request);
        return updated ?
                ResponseEntity.ok("Personal info updated successfully") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidate not found");
    }


    @PutMapping("/{id}/bank-info")
    public ResponseEntity<String> updateBankInfo(@PathVariable Long id, @RequestBody BankInfoRequest request) {
        boolean updated = candidateService.updateBankInfo(id, request);
        return updated ?
                ResponseEntity.ok("Bank info updated successfully") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidate not found");
    }


    @PutMapping("/{id}/educational-info")
    public ResponseEntity<String> updateEducationalInfo(@PathVariable Long id, @RequestBody EducationalInfoRequest request) {
        boolean updated = candidateService.updateEducationalInfo(id, request);
        return updated ?
                ResponseEntity.ok("Educational info updated successfully") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidate not found");
    }






    @Autowired
    private CandidateService documentService;

    @PostMapping("/{id}/upload-document")
    public ResponseEntity<String> uploadDocument(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            documentService.uploadDocument(id, file);
            return ResponseEntity.ok("Document uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: " + e.getMessage());
        }
    }


    @PutMapping("/{id}/verify-document")
    public ResponseEntity<String> verifyDocument(@PathVariable Long id) {
        Optional<CandidateDocument> optionalDoc = candidateDocumentRepository.findById(id);

        if (optionalDoc.isPresent()) {
            CandidateDocument document = optionalDoc.get();
            document.setFile_verified(true); // Set verified
            candidateDocumentRepository.save(document); // Save changes
            return ResponseEntity.ok("Document verified successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Document not found with ID: " + id);
        }
    }



    @Autowired
    private EmailService emailService;

    @PostMapping("/send/{candidateId}")
    public ResponseEntity<String> sendMailToCandidate(@PathVariable Long candidateId) {
        return emailService.sendMailToCandidate(candidateId);
        //return ResponseEntity.ok("Email sending process triggered.");
    }

}
