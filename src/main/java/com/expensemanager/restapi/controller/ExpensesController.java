package com.expensemanager.restapi.controller;

import com.expensemanager.restapi.dto.ExpenseDTO;
import com.expensemanager.restapi.io.ExpensesRequest;
import com.expensemanager.restapi.io.ExpensesResponse;
import com.expensemanager.restapi.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.event.WindowFocusListener;
import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ExpensesController {

    private final ExpenseService expenseService;
    private final ModelMapper modelMapper;


    @GetMapping("/expenses")
    public List<ExpensesResponse> getExpenses() {
        log.info("Get expenses details API called");
        List<ExpenseDTO> expensesDtoList = expenseService.getAllExpenseServices();
        log.info("Printing the data from the service {}", expensesDtoList);
        List<ExpensesResponse> expensesResponses = expensesDtoList.stream().map(expensesDto -> mapToExpenseDTO(expensesDto)).collect(Collectors.toList());
        return expensesResponses;
    }

    @GetMapping("/expenses/{expensesId}")
    public ExpensesResponse getExpenseByExpensesId(@PathVariable String expensesId) {
        ExpenseDTO expensesDto = expenseService.getExpensesByExpensesId(expensesId);
        return mapToExpenseDTO(expensesDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses/{expensesId}")
    public void  deleteExpendByExpenseId(@PathVariable String expensesId){
        log.info("API delete expenses {} called", expensesId);
       expenseService.deleteExpenseByExpensesId(expensesId);
    }

    @PostMapping("/expenses")
    public ExpensesResponse saveExpenseDetails(@Valid @RequestBody  ExpensesRequest expensesRequest){
        log.info("Post Expense details API called {}", expensesRequest);
       ExpenseDTO expenseDto= mapToExpenseDTO(expensesRequest);
       expenseDto=expenseService.saveExpenseDetails(expenseDto);
       return mapToExpenseResponse(expenseDto);
    }
    @PutMapping("/expenses/{expensesId}")
    public ExpensesResponse updateExpenseDetails(@RequestBody ExpensesRequest updateExpenseDetails, @PathVariable String expensesId){
        log.info("Put Expense deatils API called expenseRequest {}, expensesId {}", updateExpenseDetails,expensesId  );
        ExpenseDTO expenseDTO=mapToExpenseDTO( updateExpenseDetails );


   expenseDTO =expenseService.updateExpenseDetails( expenseDTO,expensesId );
        return mapToExpenseResponse( expenseDTO );

    }

    private ExpenseDTO mapToExpenseDTO(ExpensesRequest expensesRequest){
        return modelMapper.map(expensesRequest,ExpenseDTO.class );

    }

    private ExpensesResponse mapToExpenseResponse(ExpenseDTO expenseDTO){
       return  modelMapper.map( expenseDTO,ExpensesResponse.class );
    }

    private ExpensesResponse mapToExpenseDTO(ExpenseDTO expenseDto) {
        return modelMapper.map(expenseDto, ExpensesResponse.class);
    }

}

