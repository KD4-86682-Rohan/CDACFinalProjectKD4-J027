package com.onlineParking.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditLogReqDto {
    private String action;
    private String description;
}
