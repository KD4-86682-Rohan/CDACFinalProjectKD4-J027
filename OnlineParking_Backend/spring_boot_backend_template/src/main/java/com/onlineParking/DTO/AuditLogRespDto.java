package com.onlineParking.DTO;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditLogRespDto {
    private Long id;
    private Long adminId;
    private String adminName;
    private String action;
    private String description;
    private LocalDateTime timestamp;
}
