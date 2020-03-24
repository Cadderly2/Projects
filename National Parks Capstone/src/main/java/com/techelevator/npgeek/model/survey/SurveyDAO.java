package com.techelevator.npgeek.model.survey;
import java.util.Map;

import com.techelevator.npgeek.park.model.Park;

public interface SurveyDAO {

	public void saveSurvey(Survey survey);
	public Map<Park, Integer> getAllSurveys();
}
