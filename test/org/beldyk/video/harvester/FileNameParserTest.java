package org.beldyk.video.harvester;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Test;

public class FileNameParserTest {

	@Test
	public void BasicTest() throws FileNotFoundException{
		String fname = "videoPatterns.txt";
		FileNameParser ps = new FileNameParser(fname);
		String [] names = {
				"Doctor Who S01E05.avi",
				"Doctor Who - S01E05.avi",
				"Doctor Who - 105.avi",
				"Doctor Who 105.avi"
		};

		for(String n: names){
			System.out.println("Parseing "+n);
			Map<String, String> tags = ps.parseFileName(n);
			assertEquals("Doctor Who", tags.get("series"));
			assertEquals(1, new Integer(tags.get("season")));
			assertEquals(5, new Integer(tags.get("ep")));
			assertEquals("avi", tags.get("format"));
		}
	}
}
