package com.letsave.finance.mapper;
/*
    @Created: 22 / 06 / 2021 - 3:52 PM
    @Author: Dummy
*/

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CategoriesAccountsMapper {

  void insertAccountCategory(@Param("accountId") long accountId, @Param("categoryId") long categoryId);

}
