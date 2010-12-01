package com.videoSearch.media;

import org.beldyk.util.FileNameParser;

public class VideoItem extends AbstractMediaItem {

	protected String seriesName;
	protected String title;
	protected Integer seasonNum;
	protected Integer episodeNum;

	public VideoItem(String fileUrl, FileNameParser nameParser) {
		super(fileUrl, nameParser);

	}

	@Override
	public void init(){
		super.init();
		if(this.tokenizedFname != null){
			if(this.tokenizedFname.containsKey("series")){
				this.seriesName = this.tokenizedFname.
							get("series").
							replaceAll("[\\s\\.\\_\\-]+", " ").toLowerCase();
			}
			this.title = this.tokenizedFname.get("title");
			
			if(this.tokenizedFname.get("ep") != null){
				this.episodeNum = new Integer(this.tokenizedFname.get("ep"));
			}
			
			if(this.tokenizedFname.get("season") != null){
				this.seasonNum = new Integer(this.tokenizedFname.get("season"));
			}
		}
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
