package org.beldyk.video.harvester;

public class AviHarvester extends VideoHarvester {

	public AviHarvester(String path) {
		super(path);
	}

	@Override
	public String getMediaType() {
		// TODO Auto-generated method stub
		return "avi";
	}

	
}
