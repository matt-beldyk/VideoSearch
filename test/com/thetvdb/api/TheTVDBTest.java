package com.thetvdb.api;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.moviejukebox.thetvdb.TheTVDB;
import com.moviejukebox.thetvdb.model.Series;

public class TheTVDBTest {
	TheTVDB tvdb;
	
	@Before
	public void setup(){
		tvdb = new TheTVDB(Confs.APIKey());
	}
	
	@After
	public void teardown(){
		tvdb = null;
	}
	
	@Test
	public void verifyCanGrabViaID(){
		Series ser = tvdb.getSeries("76107", "en");
		assertEquals("Doctor Who", ser.getSeriesName());
		System.out.println(ser.getOverview());
	}
	
	@Test
	public void lookupByNameTest(){
		List <Series> ids = tvdb.searchSeries("Doctor Who", "en");
		
		for (Series ser: ids){
			System.out.printf("id:%s,imdb:%s,name:%s\n", 
					ser.getId(), ser.getImdbId(), ser.getSeriesName());
		}
	}
	
	
	
}
