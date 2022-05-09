package com.example.demo.repository;

import com.example.demo.model.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Long> { }
