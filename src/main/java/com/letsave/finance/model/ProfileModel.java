package com.letsave.finance.model;
/*
    @Created: 20 / 06 / 2021 - 2:12 PM
    @Author: Dummy
*/


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class ProfileModel {

  private long accountId;
  private String fullname;
  private String email;
  private String phone;
  private Date birthday;
  private String avatar;
  private String job;
  private boolean isMale;
  private int age;

  public ProfileModel(long accountId) {
    this.accountId = accountId;
  }

}
