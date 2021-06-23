package com.letsave.finance.mapper;
/*
    @Created: 23 / 06 / 2021 - 1:50 PM
    @Author: Dummy
*/

import com.letsave.finance.model.BalanceModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BalancesMapper {

  void insertBalance(BalanceModel balanceModel);

  void updateBalance(BalanceModel balanceModel);

  BalanceModel findBalanceByColumn(@Param("column") String column, @Param("value") String value);

}
