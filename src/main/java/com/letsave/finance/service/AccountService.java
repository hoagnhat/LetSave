package com.letsave.finance.service;
/*
    @Created: 20 / 06 / 2021 - 2:09 PM
    @Author: Dummy
*/

import com.letsave.finance.exception.exceptions.EntityNotFoundException;
import com.letsave.finance.exception.exceptions.PasswordException;
import com.letsave.finance.mapper.AccountMapper;
import com.letsave.finance.model.AccountModel;
import com.letsave.finance.model.BalanceModel;
import com.letsave.finance.model.CategoryModel;
import com.letsave.finance.model.ProfileModel;
import com.letsave.finance.request.ChangePasswordRequest;
import com.letsave.finance.request.RegisterAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class AccountService {

  private final AccountMapper mapper;
  private final ProfileService profileService;
  private final PasswordEncoder passwordEncoder;
  private final CategoryService categoryService;
  private final BalanceService balanceService;

  @Autowired
  public AccountService(AccountMapper mapper, ProfileService profileService, PasswordEncoder passwordEncoder, CategoryService categoryService, BalanceService balanceService) {
    this.mapper = mapper;
    this.profileService = profileService;
    this.passwordEncoder = passwordEncoder;
    this.categoryService = categoryService;
    this.balanceService = balanceService;
  }

  public AccountModel findAccountByColumn(String column, String value) {

    AccountModel account = mapper.findAccountByColumn(column, value);

    if (account == null) {
      throw new EntityNotFoundException(value + " is not existed!");
    }

    return account;

  }

  public boolean isColumnExist(String column, String value) {

    AccountModel account = mapper.findAccountByColumn(column, value);

    if (account != null) {
      return true;
    }

    return false;

  }

  @Transactional
  public void registerAccount(HttpServletRequest httpServletRequest, RegisterAccountRequest request) throws SQLIntegrityConstraintViolationException, ServletException {

    // Check username existed
    if (isColumnExist("username", request.getUsername())) {
      throw new SQLIntegrityConstraintViolationException("Username is existed!");
    }

    AccountModel account = AccountModel.builder()
                                       .username(request.getUsername())
                                       .password(passwordEncoder.encode(request.getPassword()))
                                       .build();

    // Insert new Account and get id auto-generate
    mapper.insertAccount(account);

    ProfileModel profile = ProfileModel.builder()
                                       .accountId(account.getId())
                                       .build();

    profileService.insertProfile(profile);

    // Add default categories for this account
    categoryService.addDefaultCatgories(account.getId());

    BalanceModel balance = BalanceModel.builder()
            .accountId(account.getId())
            .total(0)
            .build();

    balanceService.insertBalance(balance);

    // Login after register
    httpServletRequest.login(request.getUsername(), request.getPassword());

  }

  @Transactional
  public void changePassword(long id, ChangePasswordRequest request) {

    AccountModel account = findAccountByColumn("id", String.valueOf(id));

    if (account == null) {
      throw new EntityNotFoundException("Account is not existed!");
    }

    String oldPassword = request.getOldPassword();
    String newPassword = request.getNewPassword();

    if (oldPassword.equals(newPassword)) {
      throw new PasswordException("The new password must be different from the old password!");
    }

    if (passwordEncoder.matches(oldPassword, account.getPassword())) {
      mapper.updateAccountPassword(id, passwordEncoder.encode(newPassword));
    } else {
      throw new PasswordException("The old password is wrong!");
    }

  }

  public AccountModel getCurrentAccount() {
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    String username = authentication.getName();

    AccountModel account = findAccountByColumn("username", username);

    return account;
  }

  public void login(String username, String password) {

    AccountModel account = mapper.findAccountByColumn("username", username);
    if (account == null) {
      throw new EntityNotFoundException("Username doesn't existed");
    }

    if (!passwordEncoder.matches(password, account.getPassword())) {
      throw new PasswordException("Wrong password!");
    }

  }


}
