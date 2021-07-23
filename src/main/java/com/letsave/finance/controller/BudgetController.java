package com.letsave.finance.controller;
/*
    @Created: 23 / 06 / 2021 - 2:29 PM
    @Author: Dummy
*/

import com.letsave.finance.common.routes.BudgetRoutes;
import com.letsave.finance.model.BugetModel;
import com.letsave.finance.request.BudgetRequest;
import com.letsave.finance.service.AccountService;
import com.letsave.finance.service.BudgetService;
import com.letsave.finance.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BudgetRoutes.BUDGET_ROOT_URL)
public class BudgetController {

  private final TransactionService transactionService;
  private final BudgetService service;
  private final AccountService accountService;

  public BudgetController(TransactionService transactionService, BudgetService service, AccountService accountService) {
    this.transactionService = transactionService;
    this.service = service;
    this.accountService = accountService;
  }

  @PostMapping()
  @ResponseStatus(value = HttpStatus.OK)
  public void insertBudget(@RequestBody BugetModel bugetModel) {
    bugetModel.setAccountId(accountService.getCurrentAccount().getId());
    bugetModel.setStatus(0);
    bugetModel.setActualAmount(0);

    String[] parts = bugetModel.getDate().split("-");

    if (parts.length == 2) {
      bugetModel.setDate(bugetModel.getDate() + "-01");
    }

    service.insertBudget(bugetModel);
  }

  @PostMapping("/month")
  @ResponseStatus(value = HttpStatus.OK)
  public List<BugetModel> findCurrentMonthBudget(@RequestBody BudgetRequest request) {

    String date = transactionService.getCurrentDate();
    String[] parts = date.split("-");
    if (request.getYear() == null) {
      request.setYear(Integer.parseInt(parts[0]));
    }

    if (request.getMonth() == null) {
      request.setMonth(Integer.parseInt(parts[1]));
    }

    return service.findBudgetByMonth(accountService.getCurrentAccount().getId(), request.getMonth(), request.getYear());
  }

}
