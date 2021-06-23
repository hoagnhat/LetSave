package com.letsave.finance.model;
/*
    @Created: 23 / 06 / 2021 - 1:54 PM
    @Author: Dummy
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BalanceModel {

  private long accountId;
  private float total;

}
