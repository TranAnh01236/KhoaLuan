package controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.trananh3010.model.User;

import com.google.gson.Gson;

import ultilities.Constants;

public class UserController {
	public UserController() {
		
	}
	public User getUserById(String id) throws IOException, InterruptedException {
		String url = Constants.getUserByIdURL+ id.toString();
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
		HttpClient client = HttpClient.newBuilder().build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		Gson gson = new Gson();
		User user = gson.fromJson(response.body(), User.class);
		
		if(user!= null)
			return user;
		return null;
	}
}
