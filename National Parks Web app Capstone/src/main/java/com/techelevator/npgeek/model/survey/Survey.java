package com.techelevator.npgeek.model.survey;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.techelevator.npgeek.park.model.ParkDAO;

public class Survey {

	@NotBlank(message="* required")
	@Email(message="Invalid email format")
	private String email;
	
	@NotEmpty(message="* required")
	private String favoritePark;
	
	@NotBlank(message="* required")
	private String stateOfResidence;
	
	@NotBlank(message="* required")
	private String activityLevel;
	
	@AssertTrue(message="Invalid activity level")
	public boolean isActivityLevelValid() {
		boolean result = false;
		if (activityLevel != null && ACTIVITY_LEVELS.contains(activityLevel)) {
			result = true;
		}
		return result;
	}
	
	@AssertTrue(message="Not a valid state")
	public boolean isStateValid() {
		boolean result = false;
		if (stateOfResidence != null && STATES.contains(stateOfResidence)) {
			result = true;
		}
		return result;
	}

	public static final List<String> ACTIVITY_LEVELS = Arrays.asList(new String[] {
			"Inactive", "Sedintary", "Active", "Extremely Active" });
	
	public static final List<String> STATES = Arrays.asList(new String[] {
			"AK", "AL", "AR", "AS", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", 
			"GU", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", 
			"MI", "MN", "MO", "MP", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", 
			"NV", "NY", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", 
			"UM", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY" });
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFavoritePark() {
		return favoritePark;
	}
	public void setFavoritePark(String favoritePark) {
		this.favoritePark = favoritePark;
	}
	public String getStateOfResidence() {
		return stateOfResidence;
	}
	public void setStateOfResidence(String stateOfResidence) {
		this.stateOfResidence = stateOfResidence;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
}
