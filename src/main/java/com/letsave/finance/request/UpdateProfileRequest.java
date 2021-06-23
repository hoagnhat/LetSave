package com.letsave.finance.request;
/*
    @Created: 22 / 06 / 2021 - 4:09 PM
    @Author: Dummy
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UpdateProfileRequest {

  private String fullname;
  private String email;
  private String phone;
  private Date birthday;
  private String avatar;

}
