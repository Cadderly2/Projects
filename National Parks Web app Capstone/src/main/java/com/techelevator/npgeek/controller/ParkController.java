package com.techelevator.npgeek.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.park.model.Park;
import com.techelevator.npgeek.park.model.ParkDAO;
import com.techelevator.npgeek.weather.model.RestWeatherDAO;
import com.techelevator.npgeek.weather.model.WeatherDAO;

@Controller
public class ParkController {
	
	@Autowired
	private ParkDAO parkDao;
	
	@Autowired
	private RestWeatherDAO weatherDao;

	@RequestMapping(path="/", method = RequestMethod.GET)
	public String getHomeScreen(ModelMap mm) {
		mm.put("parks", parkDao.getAllParks());
		return "homepage";
	}
	
	@RequestMapping(path="/detail", method = RequestMethod.GET)
	public String getDetailPage(ModelMap mm, @RequestParam String parkId) {
		Park park = parkDao.getParkById(parkId);
		mm.put("park", park);
		mm.put("forecasts", weatherDao.getWeather(park.getLatitude(), park.getLongitude()));
		return "detail";
	}
	
	@PostMapping(path="/detail")
	public String updateParkTemperature(HttpSession session, @RequestParam String temp,
										RedirectAttributes ra, @RequestParam String parkId) {
		session.setAttribute("tempPreference", temp);
		ra.addAttribute("parkId", parkId);
		return "redirect:/detail";
	}

}
