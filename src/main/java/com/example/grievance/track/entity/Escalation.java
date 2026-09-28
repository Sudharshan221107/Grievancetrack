package com.example.grievance.track.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

@Entity
public class Escalation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "grievance_id", unique = true)
    private Grievance grievance;

    private String escalatedToOfficer;

    private LocalDateTime escalatedAt;

    public Escalation() {
    }

    public Long getId() {
        return id;
    }

    public Grievance getGrievance() {
        return grievance;
    }

    public void setGrievance(Grievance grievance) {
        this.grievance = grievance;
    }

    public String getEscalatedToOfficer() {
        return escalatedToOfficer;
    }

    public void setEscalatedToOfficer(String escalatedToOfficer) {
        this.escalatedToOfficer = escalatedToOfficer;
    }

    public LocalDateTime getEscalatedAt() {
        return escalatedAt;
    }

    public void setEscalatedAt(LocalDateTime escalatedAt) {
        this.escalatedAt = escalatedAt;
    }
}