package com.expensemanager.restapi.io;

import lombok.*;

import java.util.Date;
@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorObject {
    private Integer statusCode;
    private String errorCode;
    private Date timeStamp;
    private String message;

}
