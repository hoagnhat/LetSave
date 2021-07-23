package com.letsave.finance.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudgetRequest {

    private long categoryId;
    private long accountId;
    private Integer month;
    private Integer year;

}
