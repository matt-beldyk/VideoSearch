package com.thetvdb.api;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.xml.sax.SAXException;

public class MirrorsTest {

	@Test
	public void getAMirror() throws ClientProtocolException, IOException, ParserConfigurationException, SAXException{
		Mirrors m = new Mirrors();
		assertEquals("http://thetvdb.com", m.getZipMirror());
	}
	@Test
	public void grabXMLTest() throws ClientProtocolException, IOException, ParserConfigurationException, SAXException{
		Mirrors m = new Mirrors();
		String response = m.getMirrorXML();
		System.err.println(response);
		assertTrue(response.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"));
	}
	
	@Test
	public void mirrorsListTest() throws ClientProtocolException, IOException, ParserConfigurationException, SAXException {
		Mirrors m = new Mirrors();
		List<String > response = m.findListMirrors();
		assertEquals("http://thetvdb.com", response.get(0));
	}
}
