package com.onlineParking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.onlineParking.DTO.AuditLogReqDto;
import com.onlineParking.DTO.AuditLogRespDto;
import com.onlineParking.Services.AuditLogService;

@RestController
@RequestMapping("/audit-logs")
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    // Add a new audit log
    @PostMapping("/add/{adminId}")
    public String addAuditLog(
            @PathVariable Long adminId, 
            @RequestBody AuditLogReqDto auditLogReqDto) {
        auditLogService.addAuditLog(adminId, auditLogReqDto);
        return "Audit log added successfully";
    }

    // Fetch all audit logs
    @GetMapping("/all")
    public List<AuditLogRespDto> getAllAuditLogs() {
        return auditLogService.getAllAuditLogs();
    }

    // Fetch audit logs by admin ID
    @GetMapping("/admin/{adminId}")
    public List<AuditLogRespDto> getAuditLogsByAdmin(@PathVariable Long adminId) {
        return auditLogService.getAuditLogsByAdmin(adminId);
    }
}
