package edu.icai.gittpat.repository;

import edu.icai.gittpat.model.MyTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface MyTableRepository extends CrudRepository<MyTable, Long> { }
