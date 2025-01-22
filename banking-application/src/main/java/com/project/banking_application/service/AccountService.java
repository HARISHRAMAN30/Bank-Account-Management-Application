package com.project.banking_application.service;

import com.project.banking_application.entity.Account;

import java.util.List;


public interface AccountService {

    public Account createAccount(Account account);
    public Account getAccountDetailsByAccountNumber(Long accountNumber);
    public List<Account> getAllAccountDetails();
    public Account depositAmount(Long accountNumber, Double amount);
    public Account withdrawAmount(Long Account, Double amount);
    public void closeAccount(Long accountnumber);


}
