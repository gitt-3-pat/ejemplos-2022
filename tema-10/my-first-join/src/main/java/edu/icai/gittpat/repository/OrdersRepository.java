package edu.icai.gittpat.repository;

import org.springframework.data.repository.CrudRepository;

import edu.icai.gittpat.model.OrderTable;

public interface OrdersRepository extends CrudRepository<OrderTable, Long> { }
