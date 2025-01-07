package com.expensemanager.restapi.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ExpensesResponse {
    private String expensesId;
    private String name;
    private String note;
    private String category;
    private Date date;
    private BigDecimal amount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
