package com.letsave.finance.service;
/*
    @Created: 23 / 06 / 2021 - 2:31 PM
    @Author: Dummy
*/

import com.letsave.finance.mapper.BudgetMapper;
import com.letsave.finance.model.BugetModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

  private final BudgetMapper mapper;

  public BudgetService(BudgetMapper mapper) {
    this.mapper = mapper;
  }

  public void insertBudget(BugetModel bugetModel) {
    mapper.insertBudget(bugetModel);
  }

  public void updateBudget(long categoryId, String name, float amount, float status, float actualAmount,
                           long id, long accountId, int month, int year) {
    mapper.updateBudget(categoryId, name, amount, status, actualAmount, id, accountId, month, year);
  }

  public List<BugetModel> findBudgetByColumn(String column, String value) {
    return mapper.findBudgetByColumn(column, value);
  }

  public BugetModel findBudgetByCategoryId(long categoryId, long accountId, int month, int year) {
    return mapper.findBudgetByCategoryId(categoryId, accountId, month, year);
  }

  public List<BugetModel> findBudgetByMonth(long accountId, int month, int year) {
    return mapper.findBudgetByMonth(accountId, month, year);
  }

}
