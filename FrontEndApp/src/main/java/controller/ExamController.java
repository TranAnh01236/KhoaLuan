package controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.trananh3010.model.Exam;
import org.trananh3010.ultilities.MyHttpResponseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ultilities.Constants;

public class ExamController {
	public ExamController() {
		
	}
	public List<Exam> getAllExams(){
		try {
			String url = Constants.getAllExamsURL;
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
			HttpClient client = HttpClient.newBuilder().build();
			HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());
			
			Gson gson = new Gson();
			
			MyHttpResponseArray myRes = gson.fromJson(response.body(), MyHttpResponseArray.class);
			List<Exam> exams = gson.fromJson(myRes.payloadJSON(), new TypeToken<List<Exam>>(){}.getType());
			if (exams != null && exams.size()>0) {
				return exams;
			}
			return null;
		} catch (IOException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
	}
	
	public List<Exam> getExamsByLesson(String lessonId){
		try {
			String url = Constants.getExamsByLesson + lessonId;
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
			HttpClient client = HttpClient.newBuilder().build();
			HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());

			MyHttpResponseArray myRes = org.trananh3010.ultilities.Constants.gson.fromJson(response.body(), MyHttpResponseArray.class);
			List<Exam> exams = org.trananh3010.ultilities.Constants.gson.fromJson(myRes.payloadJSON(), new TypeToken<List<Exam>>(){}.getType());
			if (exams != null && exams.size()>0) {
				return exams;
			}
			return null;
		} catch (IOException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
	}
	
	public List<Exam> getExamsByChapter(String chapterId){
		try {
			String url = Constants.getExamsByChapter + chapterId;
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
			HttpClient client = HttpClient.newBuilder().build();
			HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());

			MyHttpResponseArray myRes = org.trananh3010.ultilities.Constants.gson.fromJson(response.body(), MyHttpResponseArray.class);
			List<Exam> exams = org.trananh3010.ultilities.Constants.gson.fromJson(myRes.payloadJSON(), new TypeToken<List<Exam>>(){}.getType());
			if (exams != null && exams.size()>0) {
				return exams;
			}
			return null;
		} catch (IOException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
	}
	
	public List<Exam> getExamsBySubject(String subjectId){
		try {
			String url = Constants.getExamsBySubject + subjectId;
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
			HttpClient client = HttpClient.newBuilder().build();
			HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());

			MyHttpResponseArray myRes = org.trananh3010.ultilities.Constants.gson.fromJson(response.body(), MyHttpResponseArray.class);
			List<Exam> exams = org.trananh3010.ultilities.Constants.gson.fromJson(myRes.payloadJSON(), new TypeToken<List<Exam>>(){}.getType());
			if (exams != null && exams.size()>0) {
				return exams;
			}
			return null;
		} catch (IOException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
	}
	
	public List<Exam> getExamsByGrade(String gradeId){
		try {
			String url = Constants.getExamsByGrade + gradeId;
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
			HttpClient client = HttpClient.newBuilder().build();
			HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());

			MyHttpResponseArray myRes = org.trananh3010.ultilities.Constants.gson.fromJson(response.body(), MyHttpResponseArray.class);
			List<Exam> exams = org.trananh3010.ultilities.Constants.gson.fromJson(myRes.payloadJSON(), new TypeToken<List<Exam>>(){}.getType());
			if (exams != null && exams.size()>0) {
				return exams;
			}
			return null;
		} catch (IOException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
	}
}
