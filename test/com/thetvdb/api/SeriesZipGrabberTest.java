package com.thetvdb.api;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.moviejukebox.thetvdb.TheTVDB;
import com.moviejukebox.thetvdb.model.Series;

public class SeriesZipGrabberTest {

	@Test
	public void grabOrigionalDrWho() throws IOException, ParserConfigurationException, SAXException{
		Integer whoId = 76107;
		SeriesZipGrabber gb = new SeriesZipGrabber();
		gb.downloadSeriesZip(whoId);
		File zipFile = new File(Confs.cachedSeriesZipPath()+File.separator+whoId+".zip");
		assertTrue(zipFile.exists());
	}
	

}
