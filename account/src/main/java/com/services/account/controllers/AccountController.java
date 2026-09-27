package com.services.account.controllers;

import com.services.account.contants.AccountConstants;
import com.services.account.dto.CustomerDTO;
import com.services.account.dto.ResponseDTO;
import com.services.account.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/account/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountController {

    IAccountService iAccountService;

    @GetMapping("/welcome")
    public String sayHello(){
        return "Hello Sunny!";
    }

    /**
     * Endpoint to create a new account for the customer.
     * @param customerDTO
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO){
        iAccountService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountConstants.STATUS_CODE_CREATED,AccountConstants.STATUS_MSG_ACCOUNT_CREATED));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchCustomer(@RequestParam String mobileNumber){
        CustomerDTO customerDTO = iAccountService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }
}
