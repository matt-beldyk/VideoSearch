package org.beldyk.video.harvester;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Set;

import org.junit.Test;

public class SpiderTest {
	@Test
	public void findSomeFiles (){
		AbstractSpider spider = 
			new FileSystemSpider("testData/television/Doctor Who/");
		spider.findMedia();
		Set<String> urls = spider.getUrls();
		
		for(String ur: urls){
			//System.out.println(ur);
			File fl = new File(ur);
			assertTrue(fl.exists());
			assertTrue(fl.isFile());
		}
	}

}
