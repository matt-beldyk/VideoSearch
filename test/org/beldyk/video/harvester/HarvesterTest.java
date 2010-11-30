package org.beldyk.video.harvester;

import static org.junit.Assert.*;

import org.junit.Test;

public class HarvesterTest {

	private AbstractMediaHarvester getAbrHarb(){
		return new AbstractMediaHarvester("/asdf/ghj/foobar.avi"){

			@Override
			public String getMediaType() {
				// TODO Auto-generated method stub
				return null;
			}};
	}
	@Test
	public void fileNameFindingTest(){
		AbstractMediaHarvester harvester = getAbrHarb();

		assertEquals("foobar.avi", harvester.getFileName());
	}

}
