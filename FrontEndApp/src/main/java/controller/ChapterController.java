package controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.trananh3010.model.Chapter;
import org.trananh3010.ultilities.MyHttpResponseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ultilities.Constants;

public class ChapterController {
	public ChapterController() {
		
	}
	public List<Chapter> getAllChapters(){
		try {
			String url = Constants.getAllChaptersURL;
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
			HttpClient client = HttpClient.newBuilder().build();
			HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());
			Gson gson = new Gson();
			MyHttpResponseArray myRes = gson.fromJson(response.body(), MyHttpResponseArray.class);
			List<Chapter> chapters = gson.fromJson(myRes.payloadJSON(), new TypeToken<List<Chapter>>(){}.getType());
			
			if (chapters != null && chapters.size()>0) {
				return chapters;
			}
			return null;
		} catch (IOException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
		
	}
	
	public List<Chapter> getChaptersBySubject(String subjectId){
		try {
			String url = Constants.getChaptersBySubjectURL + subjectId;
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
			HttpClient client = HttpClient.newBuilder().build();
			HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());
			Gson gson = new Gson();
			MyHttpResponseArray myRes = gson.fromJson(response.body(), MyHttpResponseArray.class);
			List<Chapter> subjects = gson.fromJson(myRes.payloadJSON(), new TypeToken<List<Chapter>>(){}.getType());
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
