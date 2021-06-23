package com.letsave.finance.mapper;
/*
    @Created: 23 / 06 / 2021 - 2:17 PM
    @Author: Dummy
*/

import com.letsave.finance.model.BugetModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BudgetMapper {

  void insertBudget(BugetModel bugetModel);

  void updateBudget(@Param("categoryId") long categoryId, @Param("name") String name, @Param("amount") float amount, @Param("status") float status,
                    @Param("actualAmount") float actualAmount, @Param("id") long id, @Param("accountId") long accountId, @Param("month") int month, @Param("year") int year);

  BugetModel findBudgetByColumn(@Param("column") String column, @Param("value") String value);

  BugetModel findBudgetByCategoryId(@Param("categoryId") long categoryId, @Param("accountId") long accountId,
                                    @Param("month") int month, @Param("year") int year);

}
