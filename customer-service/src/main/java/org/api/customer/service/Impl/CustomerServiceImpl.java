package org.api.customer.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import org.api.customer.entity.User;
import org.api.customer.repository.CustomerRepository;
import org.api.customer.service.CustomerService;
import org.common.core.dto.common.AbstractResponse;
import org.common.core.dto.customerService.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public AbstractResponse<List<CustomerDTO>> getAllCustomer() throws Exception {

        List<CustomerDTO> list = customerRepository.findAll().stream().map(
                user -> CustomerDTO.builder()
                        .id(user.getId())
                        .createdAt(user.getCreatedAt())
                        .updatedAt(user.getUpdatedAt())
                        .username(user.getUsername())
                        .userRole(user.getUserRole())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .build()
        ).toList();

        return AbstractResponse.<List<CustomerDTO>>builder()
                .data(list)
                .message("Customer List Inquiry success")
                .isSuccess(Boolean.TRUE)
                .message_description("Customer List Inquiry success")
                .status(HttpStatus.OK)
                .status_code(HttpStatus.OK.value())
                .timestamp(Timestamp.from(Instant.now())).build();

    }

    @Override
    public AbstractResponse<CustomerDTO> getCustomerById(int id) throws Exception {

        User user = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No customer found for given id - " + id));
        CustomerDTO build = CustomerDTO.builder()
                .lastName(user.getLastName())
                .userRole(user.getUserRole())
                .firstName(user.getFirstName())
                .username(user.getUsername())
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt()).build();

        return AbstractResponse.<CustomerDTO>builder()
                .data(build)
                .message("Customer get by id success")
                .isSuccess(Boolean.TRUE)
                .message_description("Customer get by id success")
                .message_code("000")
                .status(HttpStatus.OK)
                .status_code(HttpStatus.OK.value())
                .timestamp(Timestamp.from(Instant.now())).build();
    }

    @Override
    public AbstractResponse<CustomerDTO> getCustomerByUserName(String userName) throws Exception {
        User user = customerRepository.findUserByusername(userName).orElseThrow(() -> new EntityNotFoundException("No customer found for username - " + userName));
        CustomerDTO build = CustomerDTO.builder()
                .lastName(user.getLastName())
                .userRole(user.getUserRole())
                .firstName(user.getFirstName())
                .username(user.getUsername())
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt()).build();

        return AbstractResponse.<CustomerDTO>builder()
                .data(build)
                .message("Customer get by username success")
                .isSuccess(Boolean.TRUE)
                .message_description("Customer get by username success")
                .status(HttpStatus.OK)
                .status_code(HttpStatus.OK.value())
                .timestamp(Timestamp.from(Instant.now())).build();
    }
}
