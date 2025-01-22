package com.project.banking_application.service.impl;

import com.project.banking_application.entity.Account;
import com.project.banking_application.repository.AccountRepository;
import com.project.banking_application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Accountserviceimpl implements AccountService {

    @Autowired
    AccountRepository repository;

    @Override
    public Account createAccount(Account account) {
        Account accountsaved = repository.save(account);
        return accountsaved;
    }

    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber) {
        Optional<Account> account = repository.findById(accountNumber);
        if(account.isEmpty())
            throw new RuntimeException("Account does not exist");
        Account accountfound = account.get();
        return accountfound;
    }

    @Override
    public List<Account> getAllAccountDetails() {
        List<Account> listofaccounts = repository.findAll();
        return listofaccounts;
    }

    @Override
    public Account depositAmount(Long accountNumber, Double amount) {
        Optional<Account> account = repository.findById(accountNumber);
        if(account.isEmpty())
            throw new RuntimeException("Account is not present");
        Account accountpresent = account.get();
        double totalbalance = accountpresent.getBalance() + amount;
        accountpresent.setBalance(totalbalance);
        repository.save(accountpresent);
        return accountpresent;
    }

    @Override
    public Account withdrawAmount(Long accountnumber, Double amount) {
        Optional<Account> account = repository.findById(accountnumber);
        if(account.isEmpty())
            throw new RuntimeException("Account is not present");
        Account accountpresent = account.get();
        double accountbalance = accountpresent.getBalance() - amount;
        accountpresent.setBalance(accountbalance);
        repository.save(accountpresent);
        return accountpresent;
    }

    @Override
    public void closeAccount(Long accountnumber) {
        getAccountDetailsByAccountNumber(accountnumber);
        repository.deleteById(accountnumber);

    }
}
