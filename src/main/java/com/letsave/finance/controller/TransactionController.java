package com.letsave.finance.controller;
/*
    @Created: 23 / 06 / 2021 - 10:47 AM
    @Author: Dummy
*/

import com.letsave.finance.common.routes.TransactionRoutes;
import com.letsave.finance.model.TransactionDTO;
import com.letsave.finance.model.TransactionModel;
import com.letsave.finance.request.InsertTransactionRequest;
import com.letsave.finance.request.TransactionRequest;
import com.letsave.finance.service.AccountService;
import com.letsave.finance.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(TransactionRoutes.TRANSACTION_ROOT_URL)
public class TransactionController {

  private final TransactionService service;
  private final AccountService accountService;

  public TransactionController(TransactionService service, AccountService accountService) {
    this.service = service;
    this.accountService = accountService;
  }

  @PostMapping
  public void insertTransaction(@RequestBody InsertTransactionRequest request) {
    service.insertTransaction(accountService.getCurrentAccount().getId(), request);
  }

  @GetMapping(TransactionRoutes.TRANSACTION_DAILY_URL)
  public List<TransactionDTO> getCurrentDailyTransaction() {
    return service.findDailyTransaction(accountService.getCurrentAccount().getId(), service.getCurrentDate());
  }

  @PostMapping(TransactionRoutes.TRANSACTION_BY_DATE_URL)
  public List<TransactionDTO> getTransactionByDate(@RequestBody TransactionRequest request) {
    return service.findDailyTransaction(accountService.getCurrentAccount().getId(), request.getDate());
  }

  @PostMapping(TransactionRoutes.TRANSACTION_BY_MONTH)
  public List<TransactionDTO> getCurrentYearTransactionByMonth(@RequestBody TransactionRequest request) {

    if (request.getYear() == null) {
      String date = service.getCurrentDate();
      String[] parts = date.split("-");
      request.setYear(Integer.parseInt(parts[0]));
    }

    return service.findMonthlyTransaction(request.getYear(), request.getType());
  }

  @PostMapping("/month/all")
  public List<TransactionDTO> getAllTransactionByMonth(@RequestBody TransactionRequest request) {

    String date = service.getCurrentDate();
    String[] parts = date.split("-");
    if (request.getYear() == null) {
      request.setYear(Integer.parseInt(parts[0]));
    }

    if (request.getMonth() == null) {
      request.setMonth(Integer.parseInt(parts[1]));
    }

    return service.findAllMonthlyTransactions(request.getYear(), request.getMonth(), request.getType());
  }

  @PutMapping(TransactionRoutes.TRANSACTION_BY_ID_URL)
  public void updateTransaction(@PathVariable("id") long id, @RequestBody TransactionModel transactionModel) {
    transactionModel.setId(id);
    service.updateTransaction(transactionModel);
  }

  @GetMapping(TransactionRoutes.TRANSACTION_BY_ID_URL)
  public TransactionDTO getTransactionById(@PathVariable("id") long id) {
    return service.findTransactionByColumn("t.id", String.valueOf(id), accountService.getCurrentAccount().getId());
  }

}
