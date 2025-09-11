package com.expensemanager.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDTO {
    private  String  name;
    private  String email;
    private String profileId;
    private String password;
    private String createdAt;
    private String updatedAt;
}
