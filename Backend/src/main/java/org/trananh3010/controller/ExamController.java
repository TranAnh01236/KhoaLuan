package org.trananh3010.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trananh3010.exception.ResourceNotFoundException;
import org.trananh3010.model.Exam;
import org.trananh3010.repository.ExamRepository;
import org.trananh3010.ultilities.Constants;
import org.trananh3010.ultilities.MyHttpResponse;
import org.trananh3010.ultilities.MyHttpResponseArray;

import com.google.gson.JsonObject;

@RestController
@RequestMapping("/exams")
public class ExamController {
	@Autowired(required = true)
	private ExamRepository examRepository;
	
	@GetMapping("/")
    public MyHttpResponseArray getAllExams() {
        List<Exam> exams = examRepository.findAll();
      
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < exams.size(); i++) {
			objects.add(exams.get(i));
		}
        if (exams!=null && exams.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
        return new MyHttpResponseArray(404, "Không tìm thấy", null);
    }
	
	@GetMapping("/lesson/{id}")
    public MyHttpResponseArray getExamsByLesson(@PathVariable(value = "id") String lessonId) {
        List<Exam> exams = examRepository.findExamsByLesson(lessonId);
      
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < exams.size(); i++) {
			objects.add(exams.get(i));
		}
        if (exams!=null && exams.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
        return new MyHttpResponseArray(404, "Không tìm thấy", null);
    }
	
	@GetMapping("/chapter/{id}")
    public MyHttpResponseArray getExamsByChapter(@PathVariable(value = "id") String chapterId) {
        List<Exam> exams = examRepository.findExamsByChapter(chapterId);
      
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < exams.size(); i++) {
			objects.add(exams.get(i));
		}
        if (exams!=null && exams.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
        return new MyHttpResponseArray(404, "Không tìm thấy", null);
    }
	
	@GetMapping("/subject/{id}")
    public MyHttpResponseArray getExamsBySubject(@PathVariable(value = "id") String subjectId) {
        List<Exam> exams = examRepository.findExamsBySubject(subjectId);
      
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < exams.size(); i++) {
			objects.add(exams.get(i));
		}
        if (exams!=null && exams.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
        return new MyHttpResponseArray(404, "Không tìm thấy", null);
    }
	
	@GetMapping("/grade/{id}")
    public MyHttpResponseArray getExamsByGrade(@PathVariable(value = "id") String gradeId) {
        List<Exam> exams = examRepository.findExamsByGrade(gradeId);
      
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < exams.size(); i++) {
			objects.add(exams.get(i));
		}
        if (exams!=null && exams.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
        return new MyHttpResponseArray(404, "Không tìm thấy", null);
    }

    @GetMapping("/{id}")
    public MyHttpResponse getExamById(@PathVariable(value = "id") String examId)
        throws ResourceNotFoundException {
        Exam exam = examRepository.findById(examId).orElse(null);
        if (exam == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(404, "không tìm thấy", exam);
    }
    
    @PostMapping("/")
    public MyHttpResponse createExam(@Validated @RequestBody Exam exam) {
    	Exam exam1 = examRepository.save(exam);
        if (exam1 == null) {
			return new MyHttpResponse(404, "Thêm không thành công", null);
		}
        return new MyHttpResponse(200, "Thêm thành công" , exam1);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Grade> updateGrade(@PathVariable(value = "id") String gradeId,
//         @Validated @RequestBody Grade grade) throws ResourceNotFoundException {
//        Grade grade1 = examRepository.findById(gradeId)
//        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + gradeId));
//
//        grade1.setName(grade.getName());
//        
//        final Grade updatedGrade = examRepository.save(grade);
//        return ResponseEntity.ok(updatedGrade);
//    }

    @DeleteMapping("/{id}")
    public MyHttpResponse deleteExam(@PathVariable(value = "id") String examId)
         throws ResourceNotFoundException {
        Exam exam = examRepository.findById(examId)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + examId));

        examRepository.delete(exam);
        return new MyHttpResponse(200, "Xóa thành công", null);
    }
}
