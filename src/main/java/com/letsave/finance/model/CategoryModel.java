package com.letsave.finance.model;
/*
    @Created: 20 / 06 / 2021 - 8:26 PM
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
public class CategoryModel {

  private long id;
  private String name;
  private String image;

}
