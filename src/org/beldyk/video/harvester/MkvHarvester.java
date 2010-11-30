package org.beldyk.video.harvester;

public class MkvHarvester extends VideoHarvester {

	public MkvHarvester(String path) {
		super(path);
	}

	@Override
	public String getMediaType() {
		return "mkv";
	}

}
