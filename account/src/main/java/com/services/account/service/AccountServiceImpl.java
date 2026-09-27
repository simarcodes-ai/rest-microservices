package com.services.account.service;

import com.services.account.contants.AccountConstants;
import com.services.account.dto.AccountsDTO;
import com.services.account.dto.CustomerDTO;
import com.services.account.entity.Accounts;
import com.services.account.entity.Customer;
import com.services.account.exception.CustomerAlreadyExistsException;
import com.services.account.exception.ResourceNotFoundException;
import com.services.account.mapper.AccountMapper;
import com.services.account.mapper.CustomerMapper;
import com.services.account.repository.AccountsRepository;
import com.services.account.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService{

    private AccountsRepository accountRepo;
    private CustomerRepository customerRepo;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Optional<Customer> existingCustomer = customerRepo.findByMobileNumber(customerDTO.getMobileNumber());
        if(existingCustomer.isPresent()){
            throw new CustomerAlreadyExistsException(AccountConstants.ERR_MSG_CUST_ALREADY_EXISTS+customerDTO.getMobileNumber());
        }
        Customer newCustomer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        newCustomer.setCreatedBy("Anonymous");
        newCustomer.setCreatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepo.save(newCustomer);
        accountRepo.save(createAccountForCustomer(savedCustomer));
    }

    /**
     * Creating accounts for customer.
     * @param newCustomer
     * @return
     */
    private Accounts createAccountForCustomer(Customer newCustomer){
        int accountNumber = 10000000 + new Random().nextInt(9000000);
        Accounts newAccount = new Accounts(accountNumber, newCustomer.getCustomerId(),
                AccountConstants.SAVINGS_ACC_TYPE, AccountConstants.DEIRA_BRANCH_ADDRESS);
        newAccount.setCreatedBy("Anonymous");
        newAccount.setCreatedAt(LocalDateTime.now());
        return newAccount;
    }

    @Override
    public CustomerDTO fetchCustomerDetails(String mobileNumber) {
       Customer customer =  customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","Mobile Number",mobileNumber)
        );
       Accounts accounts = accountRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(
               ()->new ResourceNotFoundException("Accounts","Customer Id",customer.getCustomerId()+"")
       );

       CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer,new CustomerDTO());
       AccountsDTO accountsDTO = AccountMapper.mapToAccountDTO(accounts, new AccountsDTO());

       customerDTO.setAccountsDTO(accountsDTO);
        return customerDTO;
    }
}
