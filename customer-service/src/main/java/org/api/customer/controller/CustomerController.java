package org.api.customer.controller;

import org.api.customer.service.CustomerService;
import org.common.core.dto.common.AbstractResponse;
import org.common.core.dto.customerService.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @GetMapping("/")
     ResponseEntity<AbstractResponse<List<CustomerDTO>>> getAllCustomer()throws Exception{
        AbstractResponse<List<CustomerDTO>> allCustomer = customerService.getAllCustomer();
        return ResponseEntity.status(allCustomer.getStatus()).body(allCustomer);
     }

    @GetMapping("/{id}")
    ResponseEntity<AbstractResponse<CustomerDTO>> getCustomerById(@PathVariable("id") Integer id )throws Exception{
        AbstractResponse<CustomerDTO> customerById = customerService.getCustomerById(id);
        return ResponseEntity.status(customerById.getStatus()).body(customerById);
    }

}
