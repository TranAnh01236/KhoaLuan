package org.trananh3010.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.trananh3010.model.ExamDetail;
import org.trananh3010.model.ExamDetailPK;

public interface ExamDetailRepository extends JpaRepository<ExamDetail, ExamDetailPK>{
	
	@Query(value = "select * from exam_details where exam_id = ?1", nativeQuery=true)
	List<ExamDetail> findByExamId(String examId);
	
	@Query(value = "select top 1 * from exam_details where exam_id = ?1 and question_id = ?2", nativeQuery=true)
	ExamDetail findByExamAndQuestion(String examId, String questionId);
}
