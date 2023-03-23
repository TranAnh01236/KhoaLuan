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
import org.trananh3010.model.Chapter;
import org.trananh3010.repository.ChapterRepository;
import org.trananh3010.ultilities.MyHttpResponse;
import org.trananh3010.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/chapters")
public class ChapterController {
	@Autowired(required = true)
	private ChapterRepository chapterRepository;
	
	@GetMapping("/subject/{id}")
	public MyHttpResponseArray getChapterBySubject(@PathVariable(value = "id") String subjectId) {
		List<Chapter> chapters = chapterRepository.findBySubject(subjectId);
		ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < chapters.size(); i++) {
			objects.add(chapters.get(i));
			System.out.println(objects.get(i));
		}
        
        if (chapters!=null && chapters.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
        
//        for (Object object : objects) {
//			System.out.println(object);
//		}
        return new MyHttpResponseArray(404, "Không tìm thấy", null);
	}
	
	@GetMapping("/")
    public MyHttpResponseArray getAllChapters() {
        List<Chapter> chapters = chapterRepository.findAll();
      
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < chapters.size(); i++) {
			objects.add(chapters.get(i));
		}
        if (chapters!=null && chapters.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
        return new MyHttpResponseArray(404, "Không tìm thấy", null);
    }

//    @GetMapping("/{id}")
//    public MyHttpResponse getSubjectById(@PathVariable(value = "id") String subjectId)
//        throws ResourceNotFoundException {
//        Subject subject = chapterRepository.findById(subjectId).orElse(null);
//        if (subject == null) {
//			return new MyHttpResponse(404, "không tìm thấy", null);
//		}
//        return new MyHttpResponse(200, "Tìm thành công", subject);
//    }
    
    @PostMapping("/")
    public MyHttpResponse createChapter(@Validated @RequestBody Chapter chapter) {
    	Chapter chapter1 = chapterRepository.save(chapter);
        if (chapter == null) {
			return new MyHttpResponse(404, "Thêm không thành công", null);
		}
        return new MyHttpResponse(200, "Thêm thành công" , chapter1);
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
