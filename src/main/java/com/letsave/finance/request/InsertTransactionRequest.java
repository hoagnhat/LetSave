package com.letsave.finance.request;
/*
    @Created: 23 / 06 / 2021 - 10:38 AM
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
public class InsertTransactionRequest {

  private String type;
  private float amount;
  private long categoryId;
  private String note;

}
