package com.letsave.finance.service;
/*
    @Created: 22 / 06 / 2021 - 3:53 PM
    @Author: Dummy
*/

import com.letsave.finance.mapper.CategoriesAccountsMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoriesAccountsService {

  public final CategoriesAccountsMapper mapper;

  public CategoriesAccountsService(CategoriesAccountsMapper mapper) {
    this.mapper = mapper;
  }

  void insertAccountCategory(long accountId, long categoryId) {
    mapper.insertAccountCategory(accountId, categoryId);
  }

}
