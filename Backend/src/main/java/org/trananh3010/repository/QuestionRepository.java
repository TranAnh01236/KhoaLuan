package org.trananh3010.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trananh3010.model.Question;

public interface QuestionRepository extends JpaRepository<Question, String>{

}
