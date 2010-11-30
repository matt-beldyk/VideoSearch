package com.videoSearch.media;

import org.beldyk.util.FileNameParser;

public class MkvItem extends VideoItem {

	protected String subtitles;
	
	public MkvItem(String fileUrl, FileNameParser np) {
		super(fileUrl, np);
		// TODO Auto-generated constructor stub
		// shove in some logic around subtitles
	}

}
