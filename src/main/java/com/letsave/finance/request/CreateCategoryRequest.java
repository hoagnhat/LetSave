package com.letsave.finance.request;
/*
    @Created: 22 / 06 / 2021 - 9:40 PM
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
public class CreateCategoryRequest {

  private String name;
  private String image;

}
