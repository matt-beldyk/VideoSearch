package com.videoSearch.media;

import org.beldyk.util.FileNameParser;

public class VideoItem extends AbstractMediaItem {

	protected String seriesName;
	protected Integer seasonNum;
	protected Integer episodeNum;
	
	public VideoItem(String fileUrl, FileNameParser nameParser) {
		super(fileUrl, nameParser);
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public Integer getSeasonNum() {
		return seasonNum;
	}

	public void setSeasonNum(Integer seasonNum) {
		this.seasonNum = seasonNum;
	}

	public Integer getEpisodeNum() {
		return episodeNum;
	}

	public void setEpisodeNum(Integer episodeNum) {
		this.episodeNum = episodeNum;
	}

	
	
}
