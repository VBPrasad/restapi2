package com.expensemanager.restapi.service.Impl;

import com.expensemanager.restapi.dto.ExpensesDto;
import com.expensemanager.restapi.entity.ExpensesEntity;
import com.expensemanager.restapi.exception.ResourceNotFoundException;
import com.expensemanager.restapi.io.ExpensesResponse;
import com.expensemanager.restapi.repos.ExpensesRepos;
import com.expensemanager.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;


@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpensesRepos expensesRepos;
    private final ModelMapper modelMapper;


    @Override
    public List<ExpensesDto> getAllExpenseServices() {
        //Call the Repository
        List<ExpensesEntity> listOfEntity = expensesRepos.findAll();
        log.info(" Printing the data from repository{}", listOfEntity);
        //Convert the Entity Object to Entity DTO
        List<ExpensesDto> listOfDTO = listOfEntity.stream().map(expensesEntity -> mapToExpenseDTO(expensesEntity)).collect(Collectors.toList());
       //Return the list
        return listOfDTO;
    }

    @Override
    public ExpensesDto getExpensesByExpensesId(String expensesId) {
       ExpensesEntity expensesEntity= getExpenseEntity(expensesId);
        log.info("Get the expense By ExpenseId " + expensesEntity  );
       return mapToExpenseDTO(expensesEntity);
    }

    @Override
    public void deleteExpenseByExpensesId(String expensesId) {
        ExpensesEntity expensesEntity = getExpenseEntity(expensesId);
        log.info("Delete the expense By ExpenseId " + expensesEntity  );
        expensesRepos.delete(expensesEntity);
    }

    private ExpensesDto mapToExpenseDTO(ExpensesEntity expensesEntity) {
      return   modelMapper.map(expensesEntity, ExpensesDto.class);
    }

    private ExpensesEntity getExpenseEntity(String expensesId){
       return  expensesRepos.findByExpensesId( expensesId )
                .orElseThrow( () -> new ResourceNotFoundException( "Resource not found {}" + expensesId ) );
    }
}
