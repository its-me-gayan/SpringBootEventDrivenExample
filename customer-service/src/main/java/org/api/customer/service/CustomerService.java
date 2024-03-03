package org.api.customer.service;

import org.common.core.dto.common.AbstractResponse;
import org.common.core.dto.customerService.CustomerDTO;

import java.util.List;

public interface CustomerService {
    AbstractResponse<List<CustomerDTO>> getAllCustomer() throws Exception;
    AbstractResponse<CustomerDTO> getCustomerById(int id) throws Exception;
    AbstractResponse<CustomerDTO> getCustomerByUserName(String userName) throws Exception;
}
