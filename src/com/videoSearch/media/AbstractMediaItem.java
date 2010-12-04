package com.videoSearch.media;

import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
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

	}
	public void init(){
		this.tokenizedFname = nameParser.parseFileName(fileUrl);

	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Document toDocument(){
		Document doc = new Document();
		doc.add(new Field( "fileUrl", this.fileUrl, 
				Field.Store.YES, Field.Index.ANALYZED));


		return doc;
	}



}
