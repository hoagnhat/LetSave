package com.letsave.finance.service;
/*
    @Created: 23 / 06 / 2021 - 10:33 AM
    @Author: Dummy
*/

import com.letsave.finance.mapper.TransactionMapper;
import com.letsave.finance.model.BalanceModel;
import com.letsave.finance.model.BugetModel;
import com.letsave.finance.model.TransactionDTO;
import com.letsave.finance.model.TransactionModel;
import com.letsave.finance.request.InsertTransactionRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TransactionService {

  private final TransactionMapper mapper;
  private final BalanceService balanceService;
  private final BudgetService budgetService;

  public TransactionService(TransactionMapper mapper, BalanceService balanceService, BudgetService budgetService) {
    this.mapper = mapper;
    this.balanceService = balanceService;
    this.budgetService = budgetService;
  }

  public void insertTransaction(long accountId, InsertTransactionRequest request) {

    TransactionModel transaction = TransactionModel.builder()
            .accountId(accountId)
            .type(request.getType())
            .amount(request.getAmount())
            .categoryId(request.getCategoryId())
            .note(request.getNote())
            .build();

    mapper.insertTransaction(transaction);

    String datee = getCurrentDate();
    String[] parts = datee.split("-");

    BugetModel bugetModel = budgetService.findBudgetByCategoryId(transaction.getCategoryId(), accountId, Integer.valueOf(parts[1]), Integer.valueOf(parts[0]));

    if (transaction.getType().equals("Income")) {
      BalanceModel balanceModel = balanceService.findBalanceByColumn("accountId", String.valueOf(accountId));
      balanceModel.setTotal(balanceModel.getTotal() + transaction.getAmount());
      balanceService.updateBalance(balanceModel);

    } else {
      BalanceModel balanceModel = balanceService.findBalanceByColumn("accountId", String.valueOf(accountId));
      balanceModel.setTotal(balanceModel.getTotal() - transaction.getAmount());
      balanceService.updateBalance(balanceModel);

      if (bugetModel != null) {
        float newActualAmount = bugetModel.getActualAmount() + transaction.getAmount();
        bugetModel.setActualAmount(newActualAmount);
        bugetModel.setStatus(newActualAmount / bugetModel.getAmount() * 100);
        budgetService.updateBudget(bugetModel.getCategoryId(), bugetModel.getName(), bugetModel.getAmount(),
                bugetModel.getStatus(), bugetModel.getActualAmount(), bugetModel.getId(), bugetModel.getAccountId()
                , Integer.valueOf(parts[1]), Integer.valueOf(parts[0]));
      }

    }

  }

  public List<TransactionDTO> findDailyTransaction(long accountId, String date) {
    return mapper.findDailyTransaction(accountId, date);
  }

  public String getCurrentDate() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime now = LocalDateTime.now();
    return dtf.format(now);
  }

  public List<TransactionDTO> findMonthlyTransaction(int year, String type) {
    return mapper.findMonthlyTransaction(year, "type", type);
  }

  public void updateTransaction(TransactionModel transactionModel) {
    mapper.updateTransaction(transactionModel);
  }

  public TransactionDTO findTransactionByColumn(String column, String value, long accountId) {
    return mapper.findTransactionByColumn(column, value, accountId);
  }

}
