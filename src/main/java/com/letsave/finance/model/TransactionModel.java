package com.letsave.finance.model;
/*
    @Created: 23 / 06 / 2021 - 10:26 AM
    @Author: Dummy
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionModel {

  private long id;
  private long accountId;
  private String type;
  private float amount;
  private long categoryId;
  private Date date;
  private String note;

}
