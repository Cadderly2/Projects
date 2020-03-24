package com.techelevator.npgeek.weather.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAnySetter;

@Component
public class RestWeatherDAO implements WeatherDAO {
	
	private static class DarkSkyForecast {
		public DarkSkyDataBlock daily;
	}
	
	private static class DarkSkyDataBlock {
		public List<DarkSkyDataPoint> data;
	}
	
	private static class DarkSkyDataPoint {
		public String icon;
		public double temperatureLow;
		public double temperatureHigh;
	}
	
	private static final String BASE_URL = "https://api.darksky.net/forecast/c897caa090b66df544ce280895fce5cb/";
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public List<Weather> getWeather(double latitude, double longitude) {
		String url = BASE_URL + latitude + "," + longitude;
		DarkSkyForecast forecast = restTemplate.getForObject(url, DarkSkyForecast.class);
		List<Weather> results = new ArrayList<Weather>();	
		for (DarkSkyDataPoint dp : forecast.daily.data) {
			Weather w = new Weather();
			w.setLowInF((int)dp.temperatureLow);
			w.setHighInF((int)dp.temperatureHigh);
			w.setForecast(dp.icon);
			results.add(w);
		}
		return results;
	}
	
}
