package com.expensemanager.restapi.controller;

import com.expensemanager.restapi.dto.ExpensesDto;
import com.expensemanager.restapi.io.ExpensesResponse;
import com.expensemanager.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ExpensesController {

    private final ExpenseService expenseService;
    private final ModelMapper modelMapper;


     @GetMapping("/expenses")
    public List<ExpensesResponse> getExpenses(){
         log.info("API Get  /expenses called");
        List<ExpensesDto>  expensesDtoList=expenseService.getAllExpenseServices();
        log.info("Printing the data from the service {}", expensesDtoList);
        List<ExpensesResponse> expensesResponses=expensesDtoList.stream().map(expensesDto->mapToExpenseDTO(expensesDto)).collect(Collectors.toList());
        return expensesResponses;



    }

    @GetMapping("/expenses/{expensesId}")
    public ExpensesResponse getExpenseByExpensesId(@PathVariable String expensesId){

        ExpensesDto expensesDto= expenseService.getExpensesByExpensesId(expensesId);
        return mapToExpenseDTO(expensesDto);

    }
    private ExpensesResponse mapToExpenseDTO(ExpensesDto expensesDto) {
       return   modelMapper.map(expensesDto,ExpensesResponse.class);

    }
    }

