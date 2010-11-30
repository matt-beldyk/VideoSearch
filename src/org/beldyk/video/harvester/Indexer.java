package org.beldyk.video.harvester;

import java.io.FileNotFoundException;
import java.util.Set;
import java.util.HashSet;

import org.beldyk.util.FileNameParser;

import com.videoSearch.media.AbstractMediaItem;
import com.videoSearch.media.MediaItemFactory;
import com.videoSearch.media.UnsupportedMediaException;

public class Indexer {

	protected FileNameParser fnameParser;
	protected AbstractSpider spider;
	protected Set<String> fileNames;
	protected Set<AbstractMediaItem> parsedMediaFiles;

	private String videoPattConf;
	private String mediaRoot;

	public Indexer(String vPat, String mediaRoot) throws FileNotFoundException{
		this.videoPattConf = vPat;
		this.mediaRoot = mediaRoot;
		fnameParser = new FileNameParser(this.videoPattConf);
		spider = new FileSystemSpider(this.mediaRoot);

		this.parsedMediaFiles  = new HashSet<AbstractMediaItem>();
	}

	public void spiderFS(){
		spider.findMedia();
		fileNames = spider.getUrls();
	}

	public void parseFiles() throws Exception{
		for(String url: fileNames){
			try{
				AbstractMediaItem item = MediaItemFactory.getHarvesterInstance(url,this.fnameParser);
				if(item != null){
					this.parsedMediaFiles.add(item);
				}else{
					//System.err.printf("Ignoring file: '%s'\n",url);
				}
			} catch(UnsupportedMediaException ex){
				System.err.printf("Unknown File format '%s'\n", ex.getNaughtyPath());
			}

		}
	}

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


}
