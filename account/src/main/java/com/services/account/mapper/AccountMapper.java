package com.services.account.mapper;

import com.services.account.dto.AccountsDTO;
import com.services.account.entity.Accounts;

public class AccountMapper {

    public static AccountsDTO mapToAccountDTO(Accounts accounts, AccountsDTO accountsDTO){
        accountsDTO.setAccountNumber(accounts.getAccountNumber());
        accountsDTO.setBranchAddress(accounts.getBranchAddress());
        accountsDTO.setAccountType(accounts.getAccountType());
        return accountsDTO;
    }

    public static Accounts mapToAccount(AccountsDTO accountsDTO, Accounts accounts){
        accounts.setAccountNumber(accountsDTO.getAccountNumber());
        accounts.setBranchAddress(accountsDTO.getBranchAddress());
        accounts.setAccountType(accountsDTO.getAccountType());
        return accounts;
    }
}
