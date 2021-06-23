package com.letsave.finance.controller;
/*
    @Created: 20 / 06 / 2021 - 2:08 PM
    @Author: Dummy
*/

import com.letsave.finance.common.routes.AccountRoutes;
import com.letsave.finance.model.AccountModel;
import com.letsave.finance.model.ProfileModel;
import com.letsave.finance.request.ChangePasswordRequest;
import com.letsave.finance.request.LoginRequest;
import com.letsave.finance.request.RegisterAccountRequest;
import com.letsave.finance.request.UpdateProfileRequest;
import com.letsave.finance.service.AccountService;
import com.letsave.finance.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping(value = AccountRoutes.ACCOUNT_ROOT_URL)
public class AccountController {

  private final AccountService service;
  private final ProfileService profileService;

  @Autowired
  public AccountController(AccountService service, ProfileService profileService) {
    this.service = service;
    this.profileService = profileService;
  }

  @PostMapping(value = AccountRoutes.ACCOUNT_REGISTER_URL)
  @ResponseStatus(HttpStatus.OK)
  public void registerAccount(HttpServletRequest httpServletRequest, @RequestBody RegisterAccountRequest registerAccountRequest) throws SQLIntegrityConstraintViolationException, ServletException {
    service.registerAccount(httpServletRequest, registerAccountRequest);
  }

  @PutMapping(value = AccountRoutes.ACCOUNT_CHANGE_PASSWORD_URL)
  @ResponseStatus(HttpStatus.OK)
  public void changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
    service.changePassword(service.getCurrentAccount().getId(), changePasswordRequest);
  }

  @PutMapping(value = AccountRoutes.ACCOUNT_PROFILE_URL)
  @ResponseStatus(HttpStatus.OK)
  public void updateProfile(@RequestBody UpdateProfileRequest updateProfileRequest) {
    profileService.updateProfile(service.getCurrentAccount().getId(), updateProfileRequest);
  }

  @GetMapping(value = AccountRoutes.ACCOUNT_PROFILE_URL)
  public ProfileModel getProfile() {
    return profileService.findProfileByColumn("accountId", String.valueOf(service.getCurrentAccount().getId()));
  }

}
