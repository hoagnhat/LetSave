package com.letsave.finance.model;
/*
    @Created: 23 / 06 / 2021 - 2:14 PM
    @Author: Dummy
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class BugetModel {

  private long id;
  private long accountId;
  private long categoryId;
  private String name;
  private float amount;
  private float status;
  private Date date;
  private float actualAmount;

}
