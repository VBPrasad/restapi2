package com.expensemanager.restapi.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpensesRequest {

    private String name;
    private String note;
    private String category;
    private Date date;
    private BigDecimal amount;
}
