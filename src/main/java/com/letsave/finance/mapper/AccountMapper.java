package com.letsave.finance.mapper;
/*
    @Created: 20 / 06 / 2021 - 2:14 PM
    @Author: Dummy
*/

import com.letsave.finance.model.AccountModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {

  AccountModel findAccountByColumn(@Param("column") String column, @Param("value") String value);

  void insertAccount(AccountModel account);

  void updateAccountPassword(@Param("id") long id, @Param("value") String value);

}
