package com.videoSearch.media;

import java.util.Map;

import org.beldyk.util.FileNameParser;

abstract public class AbstractMediaItem{
	protected String fileUrl;
	protected String format;
	
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	protected Map <String, String> tokenizedFname;
	protected FileNameParser nameParser;
	
	public AbstractMediaItem(String fileUrl, FileNameParser np){
		this.fileUrl = fileUrl;
		this.nameParser = np;
		
		this.tokenizedFname = nameParser.parseFileName(fileUrl);
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	

}
