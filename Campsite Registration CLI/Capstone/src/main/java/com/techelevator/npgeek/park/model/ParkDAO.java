package com.techelevator.npgeek.park.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techelevator.npgeek.weather.model.Weather;

public interface ParkDAO {

	public List<Park> getAllParks();
	public Park getParkById(String parkId);
	public List<String> getAllParkCodes();
}
