package org.trananh3010.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.trananh3010.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, String>{
	@Query(value = "select * from subjects where grade_id = ?1", nativeQuery=true)
	List<Subject> findByGrade(String grade_id);
}
