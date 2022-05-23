package Code;

import org.json.simple.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

	public static void main(String[] args) {
		
		//new LoginPage();
		HttpRequest request = HttpRequest.newBuilder()
		        .uri(URI.create("https://community-open-weather-map.p.rapidapi.com/weather?q=Thessaloniki%2Cgr&lat=0&lon=0&callback=test&id=2172797&lang=null&units=imperial&mode=xml%22"))
		        .header("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
		        .header("X-RapidAPI-Key", "bc9d42c52amshf9f60ca66e7b81fp17604fjsn229b971851b5")
		        .method("GET", HttpRequest.BodyPublishers.noBody())
		        .build();
		HttpResponse<String> response;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
			
			JSONObject obj = new JSONObject();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
