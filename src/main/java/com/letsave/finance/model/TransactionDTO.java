package com.letsave.finance.model;
/*
    @Created: 23 / 06 / 2021 - 11:58 AM
    @Author: Dummy
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class TransactionDTO {

  private long id;
  private String type;
  private float amount;
  private long categoryId;
  private String date;
  private String note;
  private String categoryName;
  private String categoryImage;

}
