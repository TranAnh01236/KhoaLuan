package controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.trananh3010.model.Lesson;
import org.trananh3010.ultilities.MyHttpResponseArray;

import com.google.gson.reflect.TypeToken;

import ultilities.Constants;

public class LessonController {
	public LessonController() {
		
	}
	public List<Lesson> getAllLesson(){
		try {
			String url = Constants.getAllLessonsURL;
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
			HttpClient client = HttpClient.newBuilder().build();
			HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());
			
			MyHttpResponseArray myRes = org.trananh3010.ultilities.Constants.gson.fromJson(response.body(), MyHttpResponseArray.class);
			List<Lesson> lessons = org.trananh3010.ultilities.Constants.gson.fromJson(myRes.payloadJSON(), new TypeToken<List<Lesson>>(){}.getType());
			
			if (lessons != null && lessons.size()>0) {
				return lessons;
			}
			return null;
		} catch (IOException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
		
	}
	
	public List<Lesson> getLessonByChapter(String chapterId){
		try {
			String url = Constants.getLessonsByChapterURL + chapterId;
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
			HttpClient client = HttpClient.newBuilder().build();
			HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());

			MyHttpResponseArray myRes = org.trananh3010.ultilities.Constants.gson.fromJson(response.body(), MyHttpResponseArray.class);
			List<Lesson> lessons = org.trananh3010.ultilities.Constants.gson.fromJson(myRes.payloadJSON(), new TypeToken<List<Lesson>>(){}.getType());
			if (lessons != null && lessons.size()>0) {
				return lessons;
			}
			return null;
		} catch (IOException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
		
	}
}
