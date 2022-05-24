package Code;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
//import org.json.simple.parser.ParseException;
//import org.json.simple.parser.JSONParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//import java.util.Map;
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.*;


public class WeatherAPI {

	private double temperature;
	private Long humidity;
	private Long windspeed;
	private String weatherDescription;
	private String weatherCondition;
	
	public WeatherAPI()
	{
		//Our request to the API.
		HttpRequest request = HttpRequest.newBuilder()
		        .uri(URI.create("https://community-open-weather-map.p.rapidapi.com/weather?q=Thessaloniki%2Cgr&lat=40.6403&lon=22.9439&callback=0&id=2172797&lang=null&units=imperial&mode=xml"))
		        .header("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
		        .header("X-RapidAPI-Key", "bc9d42c52amshf9f60ca66e7b81fp17604fjsn229b971851b5")
		        .method("GET", HttpRequest.BodyPublishers.noBody())
		        .build();
		
		
		//Our response from the API.
		HttpResponse<String> response;
		
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

			String data = response.body();
			
			//We take the preffered data from the body of the response.
			data = data.substring(1, data.length() -1);
			data = data.substring(1);			

		    JSONObject jsonObject = (JSONObject) JSONValue.parse(data);
		    
		    //--We take temperature and humidity--//
		    JSONObject takeKeyForTemp = (JSONObject) jsonObject.get("main");
		    temperature = (Double) takeKeyForTemp.get("temp");
		    temperature = (temperature - 32) * 0.5556;
		    humidity = (Long) takeKeyForTemp.get("humidity");
		    
		    
		    //--We take beaufort--//
		    JSONObject takeKeyForWind = (JSONObject) jsonObject.get("wind");
		    windspeed = (Long) takeKeyForWind.get("speed");
		    
		    //We take Weather description and weather condition--//
		    JSONArray weatherDetailsArray = (JSONArray) jsonObject.get("weather");
		    
		    String temp = weatherDetailsArray.toString();
		    temp = temp.substring(1, temp.length() -1);
		    
		    JSONObject weatherObject = (JSONObject) JSONValue.parse(temp);
		    
		    weatherDescription = (String) weatherObject.get("description");
		    weatherCondition = (String) weatherObject.get("main");
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} 
		
	}

	public double getTemperature() {
		return temperature;
	}

	public long getHumidity() {
		return humidity;
	}

	public Long getWindspeed() {
		return windspeed;
	}

	public String getWeatherDescription() {
		return weatherDescription;
	}

	public String getWeatherCondition() {
		return weatherCondition;
	}
	
}
