package com.example.football_championship.audit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private String entityName;
    private String entityId;
    private String details;
    private String performedBy="Admin";
    private LocalDateTime timestamp;

    // Getters and Setters

    public void setAction(String action) {
        this.action = action;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setTimestamp(LocalDateTime dateTime) {
        this.timestamp = dateTime;
    }
}
