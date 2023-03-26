package controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.trananh3010.model.Lesson;
import org.trananh3010.model.Question;
import org.trananh3010.ultilities.MyHttpResponseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ultilities.Constants;

public class QuestionController {
	public QuestionController() {
		
	}
	public List<Question> getAllQuestions(){
		try {
			String url = Constants.getAllLessonsURL;
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
			HttpClient client = HttpClient.newBuilder().build();
			HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());
			
			Gson gson = new Gson();
			MyHttpResponseArray myRes = gson.fromJson(response.body(), MyHttpResponseArray.class);
			List<Question> questions = gson.fromJson(myRes.payloadJSON(), new TypeToken<List<Question>>(){}.getType());
			
			if (questions != null && questions.size()>0) {
				return questions;
			}
			return null;
		} catch (IOException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
		
	}
	
	public List<Question> getQuestionsByLesson(String lessonId){
		try {
			String url = Constants.getQuestionsByLessonURL + lessonId;
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
			HttpClient client = HttpClient.newBuilder().build();
			HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());
			Gson gson = new Gson();
			MyHttpResponseArray myRes = gson.fromJson(response.body(), MyHttpResponseArray.class);
			List<Question> questions = gson.fromJson(myRes.payloadJSON(), new TypeToken<List<Question>>(){}.getType());
			if (questions != null && questions.size()>0) {
				return questions;
			}
			return null;
		} catch (IOException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
		
	}
}
