package com.techelevator.npgeek.weather.model;

import java.util.List;

public interface WeatherDAO {

	public List<Weather> getWeather(double latitude, double longitude);
}
