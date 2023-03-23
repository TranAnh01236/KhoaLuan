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
import org.trananh3010.model.Question;
import org.trananh3010.repository.QuestionRepository;
import org.trananh3010.ultilities.MyHttpResponse;
import org.trananh3010.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/questions")
public class QuestionController {
	@Autowired(required = true)
	private QuestionRepository questionRepository;
	
	@GetMapping("/")
    public MyHttpResponseArray getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
      
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
			objects.add(questions.get(i));
		}
        if (questions!=null && questions.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
        return new MyHttpResponseArray(404, "Không tìm thấy", null);
    }

    @GetMapping("/{id}")
    public MyHttpResponse getQuestionById(@PathVariable(value = "id") String gradeId)
        throws ResourceNotFoundException {
        Question question = questionRepository.findById(gradeId).orElse(null);
        if (question == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(200, "Tìm thành công", question);
    }
    
    @PostMapping("/")
    public MyHttpResponse createQuestion(@Validated @RequestBody Question question) {
    	Question question1 = questionRepository.save(question);
        if (question1 == null) {
			return new MyHttpResponse(404, "Thêm không thành công", null);
		}
        return new MyHttpResponse(200, "Thêm thành công" , question1);
    }

    @DeleteMapping("/{id}")
    public MyHttpResponse deleteQuestion(@PathVariable(value = "id") String questionId)
         throws ResourceNotFoundException {
        Question question = questionRepository.findById(questionId)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + questionId));

        questionRepository.delete(question);
        return new MyHttpResponse(200, "Xóa thành công", null);
    }
}
