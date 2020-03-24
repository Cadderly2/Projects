package com.techelevator.npgeek.weather.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.CaseUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {

	private String parkCode;
	private int fiveDayForecastValue;

	@JsonProperty("temperatureHigh")
	private int highInF;

	@JsonProperty("temperatureLow")
	private int lowInF;

	@JsonProperty("icon")
	private String forecast;
	private List<String> messages;

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public int getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}

	public void setFiveDayForecastValue(int fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}

	public int getHighInF() {
		return highInF;
	}

	public void setHighInF(double highInF) {
		this.highInF = (int) highInF;
	}

	public int getLowInF() {
		return lowInF;
	}

	public void setLowInF(double lowInF) {
		this.lowInF = (int) lowInF;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast; 
	}
	
	public String getForecastImage() {
		return CaseUtils.toCamelCase(forecast, false, new char[] { ' ' });
	}

	public List<String> getMessages() {
		messages = new ArrayList<String>();
		if (forecast.contains("snow")) {
			messages.add("Pack your snow shoes.");
		}
		if (forecast.contains("rain")) {
			messages.add("Pack rain gear and wear waterproof shoes.");
		}
		if (forecast.contains("thunderstorms")) {
			messages.add("Seek shelter and avoid hiking on exposed ridges.");
		}
		if (forecast.contains("clear-day")) {
			messages.add("Pack sunblock.");
		}
		if (highInF > 75) {
			messages.add("Bring an extra gallon of water.");
		}
		if (Math.abs(highInF - lowInF) > 20) {
			messages.add("Wear breathable layers.");
		}
		if (lowInF < 20) {
			messages.add("Watch out for frostbite.");
		}
		return messages;
	}
	
	public int getHighInC() {
		return ((highInF - 32) * 5) / 9;
	}

	public int getLowInC() {
		return ((lowInF - 32) * 5) / 9;
	}

}
