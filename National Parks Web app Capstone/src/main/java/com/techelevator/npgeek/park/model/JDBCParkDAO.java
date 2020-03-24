package com.techelevator.npgeek.park.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCParkDAO implements ParkDAO {

	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCParkDAO (DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Park> getAllParks() {
		List<Park> parks = new ArrayList<Park>();
		String sql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, "
					+ "climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, "
					+ "parkdescription, entryfee, numberofanimalspecies, latitude, longitude "
					+ "FROM park "
					+ "ORDER BY parkname;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			parks.add(mapRowToPark(results));
		}
		return parks;
	}
	
	@Override
	public Park getParkById(String parkId) {
		Park park = new Park();
		String sql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, "
					+ "climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, "
					+ "parkdescription, entryfee, numberofanimalspecies, latitude, longitude "
					+ "FROM park "
					+ "WHERE parkcode = ?;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkId.toUpperCase());
		while(result.next()) {
			park = mapRowToPark(result);
		}
		return park;
	}
	
	@Override
	public List<String> getAllParkCodes() {
		List<String> parkCodes = new ArrayList<String>();
		String sql = "SELECT parkcode "
				   + "FROM park;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			parkCodes.add(results.getString("parkcode"));
		}
		return parkCodes;
	}


	private Park mapRowToPark(SqlRowSet row) {
		Park park = new Park();
		park.setParkCode(row.getString("parkcode").toLowerCase());
		park.setParkName(row.getString("parkname"));
		park.setState(row.getString("state"));
		park.setAcreage(row.getInt("acreage"));
		park.setElevationInFeet(row.getInt("elevationinfeet"));
		park.setMilesOfTrail(row.getDouble("milesoftrail"));
		park.setNumberOfCampsites(row.getInt("numberofcampsites"));
		park.setClimate(row.getString("climate"));
		park.setYearFounded(row.getInt("yearfounded"));
		park.setVisitorCount(row.getInt("annualvisitorcount"));
		park.setQuote(row.getString("inspirationalquote"));
		park.setQuoteSource(row.getString("inspirationalquotesource"));
		park.setParkDescription(row.getString("parkdescription"));
		park.setEntryFee(row.getInt("entryfee"));
		park.setNumberOfAnimalSpecies(row.getInt("numberofanimalspecies"));
		park.setLatitude(row.getDouble("latitude"));
		park.setLongitude(row.getDouble("longitude"));
		return park;
	}
	
}
