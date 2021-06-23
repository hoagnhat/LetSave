package com.letsave.finance.service;
/*
    @Created: 23 / 06 / 2021 - 1:56 PM
    @Author: Dummy
*/

import com.letsave.finance.mapper.BalancesMapper;
import com.letsave.finance.model.BalanceModel;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

  private final BalancesMapper mapper;

  public BalanceService(BalancesMapper mapper) {
    this.mapper = mapper;
  }

  public void insertBalance(BalanceModel balanceModel) {
    mapper.insertBalance(balanceModel);
  }

  public void updateBalance(BalanceModel balanceModel) {
    mapper.updateBalance(balanceModel);
  }

  public BalanceModel findBalanceByColumn(String column, String value) {
    return mapper.findBalanceByColumn(column, value);
  }

}
