package com.letsave.finance.request;
/*
    @Created: 20 / 06 / 2021 - 7:54 PM
    @Author: Dummy
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ChangePasswordRequest {

  private String oldPassword;

  private String newPassword;

}
