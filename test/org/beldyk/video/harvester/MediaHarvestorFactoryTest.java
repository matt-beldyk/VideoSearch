package org.beldyk.video.harvester;

import static org.junit.Assert.*;

import org.junit.Test;

public class MediaHarvestorFactoryTest {
	@Test
	public void mkvTest() throws Exception{
		assertEquals(MkvHarvester.class, MediaHarvesterFactory.getHarvesterInstance("foobar.mkv").getClass());
		assertEquals("mkv", MediaHarvesterFactory.getHarvesterInstance("foobar.mkv").getMediaType());
		assertEquals("mkv", MediaHarvesterFactory.getHarvesterInstance("foobar.MKV").getMediaType());
	}
	@Test
	public void aviTest() throws Exception{
		assertEquals(AviHarvester.class, MediaHarvesterFactory.getHarvesterInstance("foobar.avi").getClass());

		assertEquals("avi", MediaHarvesterFactory.getHarvesterInstance("foobar.avi").getMediaType());
		assertEquals("avi", MediaHarvesterFactory.getHarvesterInstance("foobar.AVI").getMediaType());
	}
	@Test
	public void mp3Test() throws Exception{
		assertEquals(Mp3Harvester.class, MediaHarvesterFactory.getHarvesterInstance("foobar.mp3").getClass());

		assertEquals("mp3", MediaHarvesterFactory.getHarvesterInstance("foobar.mp3").getMediaType());
		assertEquals("mp3", MediaHarvesterFactory.getHarvesterInstance("foobar.MP3").getMediaType());
	}
}
