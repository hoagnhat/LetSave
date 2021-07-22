package com.letsave.finance.request;
/*
    @Created: 20 / 06 / 2021 - 2:35 PM
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
public class RegisterAccountRequest {

  private String username;
  private String password;

}
