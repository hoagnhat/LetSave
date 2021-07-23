package com.letsave.finance.controller;

import com.letsave.finance.model.BalanceModel;
import com.letsave.finance.service.AccountService;
import com.letsave.finance.service.BalanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balances")
public class BalanceController {

    private final BalanceService service;
    private final AccountService accountService;

    public BalanceController(BalanceService service, AccountService accountService) {
        this.service = service;
        this.accountService = accountService;
    }

    @GetMapping
    public BalanceModel findBalanceByAccountId() {
        return service.findBalanceByColumn("accountId", String.valueOf(accountService.getCurrentAccount().getId()));
    }

}
