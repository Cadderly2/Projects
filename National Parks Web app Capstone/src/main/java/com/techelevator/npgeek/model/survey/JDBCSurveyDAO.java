package com.techelevator.npgeek.model.survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.park.model.Park;
import com.techelevator.npgeek.park.model.ParkDAO;

@Component
public class JDBCSurveyDAO implements SurveyDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCSurveyDAO (DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Autowired
	private ParkDAO parkDao;

	@Override
	public void saveSurvey(Survey survey) {
		String sql = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) "
				   + "VALUES (?, ?, ?, ?);";
		jdbcTemplate.update(sql, survey.getFavoritePark(), survey.getEmail(), survey.getStateOfResidence(),
							survey.getActivityLevel());
	}

	@Override
	public Map<Park, Integer> getAllSurveys() {
		Map<Park, Integer> surveyMap = new LinkedHashMap<Park, Integer>();
		String sql = "SELECT parkcode, COUNT(*) AS num_of_surveys "
				   + "FROM survey_result "
				   + "GROUP BY parkcode "
				   + "ORDER BY num_of_surveys DESC, parkcode;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			surveyMap.put(parkDao.getParkById(results.getString("parkcode")), results.getInt("num_of_surveys"));
		}
		return surveyMap;
	}

//	private Survey makeSurveyFromRow(SqlRowSet row) {
//		Survey survey = new Survey();
//		survey.setFavoritePark(row.getString("parkcode"));
//		survey.setEmail(row.getString("emailaddress"));
//		survey.setStateOfResidence(row.getString("state"));
//		survey.setActivityLevel(row.getString("activitylevel"));
//		return survey;
//	}
	
	
	
	
	
}
