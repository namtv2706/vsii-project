package com.vsii.keycloak.repositories;

import com.vsii.keycloak.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepositories extends CrudRepository<Customer, Long> {

}
