package com.expensemanager.restapi.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponse {
    private  String  name;
    private  String email;
    private String profileId;
    private String createdAt;
    private String updatedAt;
}
