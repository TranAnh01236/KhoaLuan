package org.trananh3010.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.trananh3010.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, String>{
	@Query(value = "select * from lessons where chapter_id = ?1 order by name", nativeQuery=true)
	List<Lesson> findByChapter(String chapter_id);
}
