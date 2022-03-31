package edu.icai.gittpat.repository;

import org.springframework.data.repository.CrudRepository;

import edu.icai.gittpat.model.CustomerTable;

public interface CustomersRepository extends CrudRepository<CustomerTable, Long> { }
