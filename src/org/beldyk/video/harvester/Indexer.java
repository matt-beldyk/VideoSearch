package org.beldyk.video.harvester;

import java.io.FileNotFoundException;
import java.util.Set;

import org.beldyk.util.FileNameParser;

public class Indexer {

	protected FileNameParser fnameParser;
	protected AbstractSpider spider;
	protected Set<String> fileNames;
	
	private String videoPattConf;
	private String mediaRoot;
	
	public Set<String> getFileNames(){
		return fileNames;
	}
	
	public String getVideoPattConf() {
		return videoPattConf;
	}

	public void setVideoPattConf(String videoPattConf) {
		this.videoPattConf = videoPattConf;
	}

	public String getMediaRoot() {
		return mediaRoot;
	}

	public void setMediaRoot(String mediaRoot) {
		this.mediaRoot = mediaRoot;
	}

	public Indexer(String vPat, String mediaRoot) throws FileNotFoundException{
		this.videoPattConf = vPat;
		this.mediaRoot = mediaRoot;
		fnameParser = new FileNameParser(this.videoPattConf);
		spider = new FileSystemSpider(this.mediaRoot);
	}
	
	public void spiderFS(){
		spider.findMedia();
		fileNames = spider.getUrls();
	}
	
	
}
