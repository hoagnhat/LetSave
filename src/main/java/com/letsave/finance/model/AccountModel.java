package com.letsave.finance.model;
/*
    @Created: 20 / 06 / 2021 - 2:10 PM
    @Author: Dummy
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountModel {

  private long id;
  private String username;
  private String password;

}
