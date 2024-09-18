package com.example.football_championship.audit;

import com.example.football_championship.audit.AuditLog;
import com.example.football_championship.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//import javax.persistence.EntityManager;
//import javax.persistence.PostPersist;
//import javax.persistence.PostRemove;
//import javax.persistence.PostUpdate;
import java.time.LocalDateTime;

@Component
public class AuditListener {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @EventListener
    public void onPostInsert(Object entity) {
        logAudit("INSERT", entity);
    }

    @EventListener
    public void onPostUpdate(Object entity) {
        logAudit("UPDATE", entity);
    }

    @EventListener
    public void onPostDelete(Object entity) {
        logAudit("DELETE", entity);
    }

    private void logAudit(String action, Object entity) {
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setEntityName(entity.getClass().getSimpleName());
        log.setDetails(entity.toString());
        log.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);
    }
}
