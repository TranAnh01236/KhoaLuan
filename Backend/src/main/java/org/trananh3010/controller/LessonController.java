package org.trananh3010.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trananh3010.model.Lesson;
import org.trananh3010.repository.LessonRepository;
import org.trananh3010.ultilities.MyHttpResponse;
import org.trananh3010.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/lessons")
public class LessonController {
	@Autowired(required = true)
	private LessonRepository lessonRepository;
	
	@GetMapping("/chapter/{id}")
	public MyHttpResponseArray getLessonByChapter(@PathVariable(value = "id") String chapterId) {
		List<Lesson> lessons = lessonRepository.findByChapter(chapterId);
		ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < lessons.size(); i++) {
			objects.add(lessons.get(i));
		}
        if (lessons!=null && lessons.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
        return new MyHttpResponseArray(404, "Không tìm thấy", null);
	}
	
	@GetMapping("/")
    public MyHttpResponseArray getAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
      
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < lessons.size(); i++) {
			objects.add(lessons.get(i));
		}
        if (lessons!=null && lessons.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
        return new MyHttpResponseArray(404, "Không tìm thấy", null);
    }

//    @GetMapping("/{id}")
//    public MyHttpResponse getSubjectById(@PathVariable(value = "id") String subjectId)
//        throws ResourceNotFoundException {
//        Subject subject = lessonRepository.findById(subjectId).orElse(null);
//        if (subject == null) {
//			return new MyHttpResponse(404, "không tìm thấy", null);
//		}
//        return new MyHttpResponse(200, "Tìm thành công", subject);
//    }
    
    @PostMapping("/")
    public MyHttpResponse createLesson(@Validated @RequestBody Lesson lesson) {
    	Lesson lesson1 = lessonRepository.save(lesson);
        if (lesson1 == null) {
			return new MyHttpResponse(404, "Thêm không thành công", null);
		}
        return new MyHttpResponse(200, "Thêm thành công" , lesson1);
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
