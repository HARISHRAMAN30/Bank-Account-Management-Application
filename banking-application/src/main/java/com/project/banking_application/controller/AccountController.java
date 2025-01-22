package com.project.banking_application.controller;

import com.project.banking_application.entity.Account;
import com.project.banking_application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController
{
    @Autowired
    AccountService accountService;
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account)
    {
        Account createAccount = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
    }

    @GetMapping("/{accountNumber}")     //should be same as the object
    public Account getAccountByAccountNumber(@PathVariable Long accountNumber)
    {
        Account account = accountService.getAccountDetailsByAccountNumber(accountNumber);
        return account;
    }

    @GetMapping("/getallaccounts")
    public List<Account> getallaccountsdetails(){
        List<Account> allaccountsdetails = accountService.getAllAccountDetails();
        return allaccountsdetails;
    }

    @PutMapping("/deposit/{accountnumber}/{amount}")
    public Account depositAccount(@PathVariable Long accountnumber,@PathVariable double amount)
    {
        Account account = accountService.depositAmount(accountnumber,amount);
        return account;
    }

    @PutMapping("/withdraw/{accountnumber}/{}amount")
    public Account withdrawAccount(@PathVariable Long accountnumber,@PathVariable double amount)
    {
        Account account = accountService.withdrawAmount(accountnumber,amount);
        return account;
    }

    @DeleteMapping("/delete/{accountnumber}")
    public ResponseEntity<String> deleteaccount(@PathVariable Long accountnumber)
    {
        accountService.closeAccount(accountnumber);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account closed"); //if body <String> the body returns as output.
    }

}
