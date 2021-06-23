package com.letsave.finance.mapper;
/*
    @Created: 23 / 06 / 2021 - 8:55 AM
    @Author: Dummy
*/

import com.letsave.finance.model.TransactionDTO;
import com.letsave.finance.model.TransactionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TransactionMapper {

  void insertTransaction(TransactionModel transactionModel);

  List<TransactionDTO> findDailyTransaction(@Param("accountId") long accountId, @Param("date") String date);

  List<TransactionDTO> findMonthlyTransaction(@Param("year") int year, @Param("column") String column, @Param("value") String value);

  void updateTransaction(TransactionModel transactionModel);

  TransactionDTO findTransactionByColumn(@Param("column") String column, @Param("value") String value, @Param("accountId") long accountId);

}
