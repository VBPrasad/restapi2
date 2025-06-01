package com.expensemanager.restapi.service.Impl;

import com.expensemanager.restapi.dto.ExpenseDTO;
import com.expensemanager.restapi.entity.ExpensesEntity;
import com.expensemanager.restapi.exception.ResourceNotFoundException;
import com.expensemanager.restapi.repos.ExpensesRepos;
import com.expensemanager.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpensesRepos expensesRepos;
    private final ModelMapper modelMapper;


    @Override
    public List<ExpenseDTO> getAllExpenseServices() {
        //Call the Repository
        List<ExpensesEntity> listOfEntity = expensesRepos.findAll();
        log.info(" Printing the data from repository{}", listOfEntity);
        //Convert the Entity Object to Entity DTO
        List<ExpenseDTO> listOfDTO = listOfEntity.stream().map( expensesEntity -> mapToExpenseDTO(expensesEntity)).collect(Collectors.toList());
       //Return the list
        return listOfDTO;
    }

    @Override
    public ExpenseDTO getExpensesByExpensesId(String expensesId) {
       ExpensesEntity expensesEntity= getExpenseEntity(expensesId);
        log.info("Get the expense By ExpenseId " + expensesEntity  );
       return mapToExpenseDTO(expensesEntity);
    }

    @Override
    public void deleteExpenseByExpensesId(String expensesId) {
        ExpensesEntity expensesEntity = getExpenseEntity(expensesId);
        log.info("Delete the expense By ExpenseId " + expensesEntity );
        expensesRepos.delete(expensesEntity);
    }

    @Override
    public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDto) {
        ExpensesEntity expensesEntity=mapToExpenseEntity(expenseDto);
        expensesEntity.setExpensesId(UUID.randomUUID().toString());
        expensesEntity=expensesRepos.save( expensesEntity );
        return mapToExpenseDTO( expensesEntity );
    }

    private ExpensesEntity mapToExpenseEntity(ExpenseDTO expenseDto) {
        return modelMapper.map(expenseDto, ExpensesEntity.class );
    }

    private ExpenseDTO mapToExpenseDTO(ExpensesEntity expensesEntity) {
      return   modelMapper.map(expensesEntity, ExpenseDTO.class);
    }

    private ExpensesEntity getExpenseEntity(String expensesId){
       return  expensesRepos.findByExpensesId( expensesId )
                .orElseThrow( () -> new ResourceNotFoundException( "Resource not found {}" + expensesId ) );
    }
}
