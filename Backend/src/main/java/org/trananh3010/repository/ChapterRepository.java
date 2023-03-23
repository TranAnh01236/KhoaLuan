package org.trananh3010.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.trananh3010.model.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, String>{
	@Query(value = "select * from chapters where subject_id = ?1", nativeQuery=true)
	List<Chapter> findBySubject(String subjectId);
}
