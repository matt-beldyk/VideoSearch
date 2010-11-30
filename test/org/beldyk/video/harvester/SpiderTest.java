package org.beldyk.video.harvester;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Set;

import org.junit.Test;

public class SpiderTest {
	@Test
	public void findSomeFiles (){
		AbstractSpider spider = 
			new FileSystemSpider("/home/beldyk/union/media/video/Television/Doctor Who/");
		spider.findMedia();
		Set<String> urls = spider.getUrls();
		
		for(String ur: urls){
			File fl = new File(ur);
			assertTrue(fl.exists());
			assertTrue(fl.isFile());
		}
	}

}
