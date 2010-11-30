package org.beldyk.video.harvester;

public class Mp3Harvester extends AbstractMediaHarvester {

	public Mp3Harvester(String path) {
		super(path);
	}

	@Override
	public String getMediaType() {
		return "mp3";
	}

}
