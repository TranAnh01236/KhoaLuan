package org.trananh3010.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.trananh3010.model.Question;

public interface QuestionRepository extends JpaRepository<Question, String>{
	@Query(value = "select * from questions where lesson_id = ?1", nativeQuery=true)
	List<Question> findByLesson(String lession_id);
}	
