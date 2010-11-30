package org.beldyk.video.harvester;

import java.io.File;

public abstract class AbstractMediaHarvester {
	protected String path;
	protected String fileName;
	
	public abstract String getMediaType();
	public  AbstractMediaHarvester(String path){
		this.path = path;
		String [] tmp = path.split(File.separator);
		setFileName(tmp[tmp.length - 1]);
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path){
		this.path = path;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileName() {
		return fileName;
	}
}
