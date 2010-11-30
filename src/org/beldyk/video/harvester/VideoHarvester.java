package org.beldyk.video.harvester;

public abstract class VideoHarvester extends AbstractMediaHarvester {

	protected String seriesName;
	protected String episodeName;
	protected Integer seasonNumber;
	protected Integer episodeNumber;
	
	public void parseFileName(){
		String cleanedFileName = this.fileName.replaceAll("_", " ").
									replaceAll("-", " ").
									replaceAll(" +", " ");
		
		if(cleanedFileName.matches("[ \\w]+s\\d+e\\d+[ \\w]+")){
			//TODO do stuff, actually put all this logic in a seperate parseing class
		}
			
	}
	
	public VideoHarvester(String path) {
		super(path);
	}


	public String getSeriesName() {
		return seriesName;
	}


	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}


	public String getEpisodeName() {
		return episodeName;
	}


	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}


	public Integer getSeasonNumber() {
		return seasonNumber;
	}


	public void setSeasonNumber(Integer seasonNumber) {
		this.seasonNumber = seasonNumber;
	}


	public Integer getEpisodeNumber() {
		return episodeNumber;
	}


	public void setEpisodeNumber(Integer episodeNumber) {
		this.episodeNumber = episodeNumber;
	}


}
