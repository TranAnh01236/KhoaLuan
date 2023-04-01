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
import org.trananh3010.model.ExamDetail;
import org.trananh3010.repository.ExamDetailRepository;
import org.trananh3010.ultilities.MyHttpResponse;
import org.trananh3010.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/exam_details")
public class ExamDetailController {
	@Autowired(required = true)
	private ExamDetailRepository examDetailRepository;
	
	@GetMapping("/")
    public MyHttpResponseArray getAllExamDetails() {
        List<ExamDetail> examDetails = examDetailRepository.findAll();
      
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < examDetails.size(); i++) {
			objects.add(examDetails.get(i));
		}
        if (examDetails!=null && examDetails.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
        return new MyHttpResponseArray(404, "Không tìm thấy", null);
    }

    @GetMapping("/{id}")
    public MyHttpResponseArray getExamDetailByExamId(@PathVariable(value = "id") String examId) {
	    List<ExamDetail> examDetails = examDetailRepository.findByExamId(examId);
	    
	    ArrayList<Object> objects = new ArrayList<>();
	    for (int i = 0; i < examDetails.size(); i++) {
			objects.add(examDetails.get(i));
		}
	    if (examDetails!=null && examDetails.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
	    return new MyHttpResponseArray(404, "Không tìm thấy", null);
    }
    
    @PostMapping("/")
    public MyHttpResponse createExamDetail(@Validated @RequestBody ExamDetail examDetail) {
    	ExamDetail examDetail1 = examDetailRepository.save(examDetail);
        if (examDetail1 == null) {
			return new MyHttpResponse(404, "Thêm không thành công", null);
		}
        return new MyHttpResponse(200, "Thêm thành công" , examDetail1);
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
    public MyHttpResponse deleteExamDetail(@PathVariable(value = "id") String examId, String questionId)
         throws ResourceNotFoundException {
        ExamDetail examDetail = examDetailRepository.findByExamAndQuestion(examId, questionId);
        examDetailRepository.delete(examDetail);
        return new MyHttpResponse(200, "Xóa thành công", null);
    }
}
