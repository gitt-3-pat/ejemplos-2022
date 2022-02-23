package edu.icai.gittpat.spring.jdbc.h2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.icai.gittpat.spring.jdbc.h2.model.Tutorial;


@Repository
public interface TutorialRepository extends CrudRepository<Tutorial, Long> {
  List<Tutorial> findByPublished(boolean published);

  List<Tutorial> findByTitleContaining(String title);
}
