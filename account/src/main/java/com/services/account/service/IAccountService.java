package com.services.account.service;

import com.services.account.dto.AccountsDTO;
import com.services.account.dto.CustomerDTO;

public interface IAccountService {
    void createAccount(CustomerDTO customerDTO);
    CustomerDTO fetchCustomerDetails(String mobileNumber);
}
