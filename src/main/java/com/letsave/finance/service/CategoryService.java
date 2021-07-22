package com.letsave.finance.service;
/*
    @Created: 20 / 06 / 2021 - 8:19 PM
    @Author: Dummy
*/

import com.letsave.finance.mapper.CategoryMapper;
import com.letsave.finance.model.AccountModel;
import com.letsave.finance.model.CategoryModel;
import com.letsave.finance.request.CreateCategoryRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

  private final CategoryMapper mapper;
  private final CategoriesAccountsService categoriesAccountsService;

  public CategoryService(CategoryMapper mapper, CategoriesAccountsService categoriesAccountsService) {
    this.mapper = mapper;
    this.categoriesAccountsService = categoriesAccountsService;
  }

  @Transactional
  public List<CategoryModel> findCategoriesByAccountId(long accountId) {
    return mapper.findCategoriesByAccountId(accountId);
  }

  @Transactional
  public void addDefaultCatgories(long accountId) {

    ArrayList<String> defaultCategories = new ArrayList<>();
    defaultCategories.add("food");
    defaultCategories.add("travel");
    defaultCategories.add("hospital");
    defaultCategories.add("bank");

    for (String i : defaultCategories) {
      CategoryModel cat = mapper.findCategoryByColumn("name", i);
      categoriesAccountsService.insertAccountCategory(accountId, cat.getId());
    }

  }

  public void insertCategory(long accountId, CreateCategoryRequest request) {

    CategoryModel category = CategoryModel.builder()
            .name(request.getName())
            .image(request.getImage())
            .build();

    mapper.insertCategory(category);

    categoriesAccountsService.insertAccountCategory(accountId, category.getId());

  }

}
