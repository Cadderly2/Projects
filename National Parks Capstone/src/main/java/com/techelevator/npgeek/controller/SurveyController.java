package com.techelevator.npgeek.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.model.survey.Survey;
import com.techelevator.npgeek.model.survey.SurveyDAO;
import com.techelevator.npgeek.park.model.Park;
import com.techelevator.npgeek.park.model.ParkDAO;

@Controller
public class SurveyController {
	
	@Autowired
	private ParkDAO parkDao;
	
	@Autowired
	private SurveyDAO surveyDao;
	
	@RequestMapping(path="/survey", method = RequestMethod.GET)
	public String getSurveyPage(ModelMap mm) {
		if(mm.containsAttribute("surveyData") == false) {
			Survey empty = new Survey();
			mm.put("surveyData", empty);
		}
		mm.put("parks", parkDao.getAllParks());
		mm.put("states", Survey.STATES);
		mm.put("activityLevels", Survey.ACTIVITY_LEVELS);
		return "survey";
	}
	
	@RequestMapping(path="/survey", method= RequestMethod.POST)
	public String postSurvey(@Valid @ModelAttribute Survey survey,
							  BindingResult result, RedirectAttributes ra) {
		String favoriteParkCode = survey.getFavoritePark();
		boolean validPark = false;
		List<String> validParkCodes = parkDao.getAllParkCodes();
		if (validParkCodes.contains(favoriteParkCode)) {
			validPark = true;
		}
		if (validPark == false) {
			result.rejectValue("favoritePark", "error.user", "Don't hack my stuff!");
		}
		if(result.hasErrors()) {
			ra.addFlashAttribute("surveyData", survey);
			ra.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "surveyData", result);
			return "redirect:/survey";
		} else {
			surveyDao.saveSurvey(survey);
			return "redirect:/allsurveys";
		}
	}
	
	@GetMapping(path="/allsurveys")
	public String showAllSurveys(ModelMap mm) {
		mm.put("surveys", surveyDao.getAllSurveys());
		return "/allsurveys";
	}
	
}
