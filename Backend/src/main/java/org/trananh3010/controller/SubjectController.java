package org.trananh3010.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trananh3010.exception.ResourceNotFoundException;
import org.trananh3010.model.Subject;
import org.trananh3010.repository.SubjectRepository;
import org.trananh3010.ultilities.MyHttpResponse;
import org.trananh3010.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
	@Autowired(required = true)
	private SubjectRepository subjectRepository;
	
	@GetMapping("/grade/{id}")
	public MyHttpResponseArray getSubjectsByGrade(@PathVariable(value = "id") String gradeId) {
		List<Subject> subjects = subjectRepository.findByGrade(gradeId);
		ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < subjects.size(); i++) {
			objects.add(subjects.get(i));
		}
        
        if (subjects!=null && subjects.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
        
//        for (Object object : objects) {
//			System.out.println(object);
//		}
        return new MyHttpResponseArray(404, "Không tìm thấy", null);
	}
	
	@GetMapping("/")
    public MyHttpResponseArray getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
      
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < subjects.size(); i++) {
			objects.add(subjects.get(i));
		}
        if (subjects!=null && subjects.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
        return new MyHttpResponseArray(404, "Không tìm thấy", null);
    }

    @GetMapping("/{id}")
    public MyHttpResponse getSubjectById(@PathVariable(value = "id") String subjectId)
        throws ResourceNotFoundException {
        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        if (subject == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(200, "Tìm thành công", subject);
    }
    
    @PostMapping("/")
    public MyHttpResponse createSubject(@Validated @RequestBody Subject subject) {
    	Subject subject1 = subjectRepository.save(subject);
        if (subject1 == null) {
			return new MyHttpResponse(404, "Thêm không thành công", null);
		}
        return new MyHttpResponse(200, "Thêm thành công" , subject1);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Grade> updateGrade(@PathVariable(value = "id") String gradeId,
//         @Validated @RequestBody Grade grade) throws ResourceNotFoundException {
//        Grade grade1 = subjectController.findById(gradeId)
//        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + gradeId));
//
//        grade1.setName(grade.getName());
//        
//        final Grade updatedGrade = subjectController.save(grade);
//        return ResponseEntity.ok(updatedGrade);
//    }
//
//    @DeleteMapping("/{id}")
//    public MyHttpResponse deleteUser(@PathVariable(value = "id") String gradeId)
//         throws ResourceNotFoundException {
//        Grade grade = subjectController.findById(gradeId)
//       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + gradeId));
//
//        subjectController.delete(grade);
//        return new MyHttpResponse(200, "Xóa thành công", null);
//    }
}
