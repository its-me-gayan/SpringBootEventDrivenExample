package org.api.customer.repository;

import org.api.customer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<User , Integer> {

    Optional<User> findUserByusername(String userName);

}
