package org.trananh3010.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.trananh3010.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, String>{
	@Query(value = "select e.id, e.name, e.description, e.duration, e.image, e.type, e.created_at, e.updated_at\n"
			+ "from exams e\n"
			+ "inner join exam_details d on\n"
			+ "e.id = d.exam_id\n"
			+ "inner join questions q on q.id = d.question_id\n"
			+ "where q.lesson_id = ?1\n"
			+ "group by e.id, e.name, e.description, e.duration, e.image, e.type, e.created_at, e.updated_at", nativeQuery=true)
	List<Exam> findExamsByLesson(String lessonId);
	
	@Query(value = "select e.id, e.name, e.description, e.duration, e.image, e.type, e.created_at, e.updated_at\n"
			+ "from exams e\n"
			+ "inner join exam_details d on\n"
			+ "e.id = d.exam_id\n"
			+ "inner join questions q on q.id = d.question_id\n"
			+ "inner join lessons l on l.id = q.lesson_id\n"
			+ "where l.chapter_id = ?1\n"
			+ "group by e.id, e.name, e.description, e.duration, e.image, e.type, e.created_at, e.updated_at", nativeQuery=true)
	List<Exam> findExamsByChapter(String chapterId);
	
	@Query(value = "select e.id, e.name, e.description, e.duration, e.image, e.type, e.created_at, e.updated_at\n"
			+ "from exams e\n"
			+ "inner join exam_details d on\n"
			+ "e.id = d.exam_id\n"
			+ "inner join questions q on q.id = d.question_id\n"
			+ "inner join lessons l on l.id = q.lesson_id\n"
			+ "inner join chapters c on c.id = l.chapter_id\n"
			+ "where c.subject_id = ?1\n"
			+ "group by e.id, e.name, e.description, e.duration, e.image, e.type, e.created_at, e.updated_at", nativeQuery=true)
	List<Exam> findExamsBySubject(String subjectId);
	
	@Query(value = "select e.id, e.name, e.description, e.duration, e.image, e.type, e.created_at, e.updated_at\n"
			+ "from exams e\n"
			+ "inner join exam_details d on\n"
			+ "e.id = d.exam_id\n"
			+ "inner join questions q on q.id = d.question_id\n"
			+ "inner join lessons l on l.id = q.lesson_id\n"
			+ "inner join chapters c on c.id = l.chapter_id\n"
			+ "inner join subjects s on s.id = c.subject_id\n"
			+ "where s.grade_id = ?1\n"
			+ "group by e.id, e.name, e.description, e.duration, e.image, e.type, e.created_at, e.updated_at", nativeQuery=true)
	List<Exam> findExamsByGrade(String gradeId);
}
