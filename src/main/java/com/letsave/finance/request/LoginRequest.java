package com.letsave.finance.request;
/*
    @Created: 20 / 06 / 2021 - 3:52 PM
    @Author: Dummy
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginRequest {

  private String username;
  private String password;

}
