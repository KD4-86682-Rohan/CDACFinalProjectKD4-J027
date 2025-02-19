//package com.onlineParking.Services;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.onlineParking.DTO.AuditLogReqDto;
//import com.onlineParking.DTO.AuditLogRespDto;
//import com.onlineParking.Dao.AuditLogDao;
//import com.onlineParking.Dao.UserDao;
//import com.onlineParking.Pojos.AuditLog;
//import com.onlineParking.Pojos.Role;
//import com.onlineParking.Pojos.User;
//
//@Service
//@Transactional
//public class AuditLogServiceImpl implements AuditLogService {
//
//    @Autowired
//    private AuditLogDao auditLogDao;
//
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    // Add a new audit log
//    @Override
//    public void addAuditLog(Long adminId, AuditLogReqDto dto) {
//        User admin = userDao.findById(adminId)
//                .orElseThrow(() -> new RuntimeException("Admin not found"));
//
//        if (admin.getRole() != Role.Admin) {
//            throw new RuntimeException("User is not an admin");
//        }
//
//        AuditLog auditLog = new AuditLog();
//        auditLog.setAdmin(admin);
//        auditLog.setAction(dto.getAction());
//        auditLog.setDescription(dto.getDescription());
//        auditLog.setTimestamp(LocalDateTime.now());
//
//        auditLogDao.save(auditLog);
//    }
//
//    // Fetch all audit logs
//    @Override
//    public List<AuditLogRespDto> getAllAuditLogs() {
//        return auditLogDao.findAll().stream()
//                .map(log -> {
//                    AuditLogRespDto dto = modelMapper.map(log, AuditLogRespDto.class);
//                    dto.setAdminId(log.getAdmin().getId());
//                    dto.setAdminName(log.getAdmin().getFirstName() + " " + log.getAdmin().getLastName());
//                    return dto;
//                })
//                .collect(Collectors.toList());
//    }
//
//    // Fetch audit logs by admin ID
//    @Override
//    public List<AuditLogRespDto> getAuditLogsByAdmin(Long adminId) {
//        User admin = userDao.findById(adminId)
//                .orElseThrow(() -> new RuntimeException("Admin not found"));
//
//        return auditLogDao.findByAdmin(admin).stream()
//                .map(log -> {
//                    AuditLogRespDto dto = modelMapper.map(log, AuditLogRespDto.class);
//                    dto.setAdminId(log.getAdmin().getId());
//                    dto.setAdminName(log.getAdmin().getFirstName() + " " + log.getAdmin().getLastName());
//                    return dto;
//                })
//                .collect(Collectors.toList());
//    }
//}
