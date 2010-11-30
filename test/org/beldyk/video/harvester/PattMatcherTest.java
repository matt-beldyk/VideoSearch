package org.beldyk.video.harvester;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class PattMatcherTest {

	@Test
	public void setupOfPattMatcherExamples(){
		String [] tgs = { "series", "ep"};
		//PattMatcher pat = new PattMatcher("(\\w+)\\s+(S\\d+E\\d+)", tgs );
		PattMatcher pat = new PattMatcher("(\\w+)\\s+(S\\d+E\\d+)", tgs );

		Map<String, String> tags = pat.parseString("foo S42E57 bar.avi");
		assertEquals("foo", tags.get("series"));
		assertEquals("S42E57", tags.get("ep"));
		}
	
	@Test
	public void semanticsOfJavaRegularEx(){
		Pattern patt = Pattern.compile("(foo)(bar)");
		Matcher mat = patt.matcher("foobar");
		mat.find();
		assertEquals("foo", mat.group(1));
		assertEquals("bar", mat.group(2));
	}
}
