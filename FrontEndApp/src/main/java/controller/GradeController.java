package controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.trananh3010.model.Grade;
import org.trananh3010.ultilities.MyHttpResponseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ultilities.Constants;

public class GradeController {
	public GradeController() {
		
	}
	public List<Grade> getAllGrades(){
		try {
			String url = Constants.getAllGradesURL;
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
			HttpClient client = HttpClient.newBuilder().build();
			HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());
			Gson gson = new Gson();
			MyHttpResponseArray myRes = gson.fromJson(response.body(), MyHttpResponseArray.class);
			List<Grade> grades = gson.fromJson(myRes.payloadJSON(), new TypeToken<List<Grade>>(){}.getType());
			if (grades != null && grades.size()>0) {
				return grades;
			}
			return null;
		} catch (IOException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
		
		
	}
}
