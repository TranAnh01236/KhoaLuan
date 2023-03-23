package controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.HashMap;

import org.trananh3010.ultilities.MyHttpResponse;

import com.google.gson.Gson;

import ultilities.Constants;

public class AuthController {
	public AuthController() {
		
	}
	public MyHttpResponse login(String account, String password) throws IOException, InterruptedException {
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("account", account);
		map.put("password", password);
		String json = new Gson().toJson(map);
		String url = Constants.loginURL;
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("Content-Type", "application/json")
				.POST(BodyPublishers.ofString(json))
				.build();
		HttpClient client = HttpClient.newBuilder().build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		Gson gson = new Gson();
		MyHttpResponse myResponse = gson.fromJson(response.body(), MyHttpResponse.class);
//		System.out.println(myResponse.toJson());
//		User user =  new Gson().fromJson(myResponse.payloadJSON(), User.class);
		
		if(myResponse!= null)
			return myResponse;
		return null;
	}
}
