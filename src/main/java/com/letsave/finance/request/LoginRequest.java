package com.letsave.finance.request;
/*
    @Created: 20 / 06 / 2021 - 3:52 PM
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
public class LoginRequest {

  private String username;
  private String password;

}
