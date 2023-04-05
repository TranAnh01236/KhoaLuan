package controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.trananh3010.model.Subject;
import org.trananh3010.ultilities.MyHttpResponseArray;

import com.google.gson.reflect.TypeToken;

import ultilities.Constants;

public class SubjectsController {
	public SubjectsController() {
		
	}
	public List<Subject> getAllSubjects(){
		try {
			String url = Constants.getAllSubjectsURL;
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
			HttpClient client = HttpClient.newBuilder().build();
			HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());
			
			MyHttpResponseArray myRes = org.trananh3010.ultilities.Constants.gson.fromJson(response.body(), MyHttpResponseArray.class);
			List<Subject> subjects = org.trananh3010.ultilities.Constants.gson.fromJson(myRes.payloadJSON(), new TypeToken<List<Subject>>(){}.getType());
			
			if (subjects != null && subjects.size()>0) {
				return subjects;
			}
			return null;
		} catch (IOException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
		
	}
	
	public List<Subject> getSubjectsByGrade(String gradeId){
		try {
			String url = Constants.getSubjectsByGradeURL + gradeId;
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
			HttpClient client = HttpClient.newBuilder().build();
			HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());

			MyHttpResponseArray myRes = org.trananh3010.ultilities.Constants.gson.fromJson(response.body(), MyHttpResponseArray.class);
			List<Subject> subjects = org.trananh3010.ultilities.Constants.gson.fromJson(myRes.payloadJSON(), new TypeToken<List<Subject>>(){}.getType());
			if (subjects != null && subjects.size()>0) {
				return subjects;
			}
			return null;
		} catch (IOException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
		
	}
}
