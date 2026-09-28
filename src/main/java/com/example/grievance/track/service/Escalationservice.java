package com.example.grievance.track.service;

import com.example.grievance.track.entity.Escalation;
import com.example.grievance.track.entity.Grievance;
import com.example.grievance.track.repository.EscalationRepository;
import com.example.grievance.track.repository.GrievanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EscalationService {

    private final EscalationRepository escalationRepository;
    private final GrievanceRepository grievanceRepository;

    public EscalationService(
            EscalationRepository escalationRepository,
            GrievanceRepository grievanceRepository) {

        this.escalationRepository = escalationRepository;
        this.grievanceRepository = grievanceRepository;
    }

    public Escalation escalate(
            Grievance grievance) {

        // Prevent duplicate escalation
        if (escalationRepository
                .findByGrievanceId(
                        grievance.getId())
                .isPresent()) {

            throw new RuntimeException(
                    "Grievance already escalated");
        }

        if (grievance.getDepartment() == null) {

            throw new RuntimeException(
                    "No department assigned");
        }

        Escalation escalation =
                new Escalation();

        escalation.setGrievance(grievance);

        escalation.setEscalatedToOfficer(
                grievance.getDepartment()
                        .getOfficerName()
        );

        escalation.setEscalatedAt(
                LocalDateTime.now()
        );

        grievance.setStatus(
                Grievance.Status.ESCALATED
        );

        grievanceRepository.save(grievance);

        return escalationRepository.save(
                escalation
        );
    }

    public List<Escalation> getAll() {
        return escalationRepository.findAll();
    }

    public Escalation getById(Long id) {

        return escalationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Escalation not found"));
    }
}