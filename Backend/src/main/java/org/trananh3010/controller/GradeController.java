package org.trananh3010.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trananh3010.exception.ResourceNotFoundException;
import org.trananh3010.model.Grade;
import org.trananh3010.repository.GradeRepository;
import org.trananh3010.ultilities.MyHttpResponse;
import org.trananh3010.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/grades")
public class GradeController {
	@Autowired(required = true)
	private GradeRepository gradeRepository;
	
	@GetMapping("/")
    public MyHttpResponseArray getAllGrades() {
        List<Grade> grades = gradeRepository.findAll();
      
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < grades.size(); i++) {
			objects.add(grades.get(i));
		}
        if (grades!=null && grades.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
        return new MyHttpResponseArray(404, "Không tìm thấy", null);
    }

    @GetMapping("/{id}")
    public MyHttpResponse getGradeById(@PathVariable(value = "id") String gradeId)
        throws ResourceNotFoundException {
        Grade grade = gradeRepository.findById(gradeId).orElse(null);
        if (grade == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(200, "Tìm thành công", grade);
    }
    
    @PostMapping("/")
    public MyHttpResponse createGrade(@Validated @RequestBody Grade grade) {
    	Grade grade1 = gradeRepository.save(grade);
        if (grade1 == null) {
			return new MyHttpResponse(404, "Thêm không thành công", null);
		}
        return new MyHttpResponse(200, "Thêm thành công" , grade1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable(value = "id") String gradeId,
         @Validated @RequestBody Grade grade) throws ResourceNotFoundException {
        Grade grade1 = gradeRepository.findById(gradeId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + gradeId));

        grade1.setName(grade.getName());
        
        final Grade updatedGrade = gradeRepository.save(grade);
        return ResponseEntity.ok(updatedGrade);
    }

    @DeleteMapping("/{id}")
    public MyHttpResponse deleteUser(@PathVariable(value = "id") String gradeId)
         throws ResourceNotFoundException {
        Grade grade = gradeRepository.findById(gradeId)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + gradeId));

        gradeRepository.delete(grade);
        return new MyHttpResponse(200, "Xóa thành công", null);
    }
}
