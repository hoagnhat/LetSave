package com.letsave.finance.controller;
/*
    @Created: 20 / 06 / 2021 - 8:28 PM
    @Author: Dummy
*/

import com.letsave.finance.common.routes.CategoryRoutes;
import com.letsave.finance.model.AccountModel;
import com.letsave.finance.model.CategoryModel;
import com.letsave.finance.request.CreateCategoryRequest;
import com.letsave.finance.service.AccountService;
import com.letsave.finance.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CategoryRoutes.CATEGORY_ROOT_URL)
public class CategoryController {

  private final CategoryService service;
  private final AccountService accountService;

  public CategoryController(CategoryService service, AccountService accountService) {
    this.service = service;
    this.accountService = accountService;
  }

  @GetMapping
  public List<CategoryModel> getAll() {
    AccountModel account = accountService.getCurrentAccount();
    return service.findCategoriesByAccountId(account.getId());
  }

  @PostMapping(value = CategoryRoutes.CATEGORY_CREATE_URL)
  public void createCategory(@RequestBody CreateCategoryRequest request) {
    AccountModel account = accountService.getCurrentAccount();
    service.insertCategory(account.getId(), request);
  }

}
