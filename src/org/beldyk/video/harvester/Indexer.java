package org.beldyk.video.harvester;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import org.beldyk.util.FileNameParser;

import com.moviejukebox.thetvdb.TheTVDB;
import com.moviejukebox.thetvdb.model.Series;
import com.thetvdb.api.Confs;
import com.videoSearch.media.AbstractMediaItem;
import com.videoSearch.media.MediaItemFactory;
import com.videoSearch.media.UnsupportedMediaException;
import com.videoSearch.media.VideoItem;

public class Indexer {

	protected FileNameParser fnameParser;
	protected AbstractSpider spider;

	protected TheTVDB tvdb;

	protected Set<String> fileNames;
	protected Set<AbstractMediaItem> parsedMediaFiles;

	protected Map<String,Series> serii;

	private String videoPattConf;
	private String mediaRoot;

	public Indexer(String vPat, String mediaRoot) throws FileNotFoundException{
		this.videoPattConf = vPat;
		this.mediaRoot = mediaRoot;
		tvdb = new TheTVDB(Confs.APIKey());

		fnameParser = new FileNameParser(this.videoPattConf);
		spider = new FileSystemSpider(this.mediaRoot);

		this.parsedMediaFiles  = new HashSet<AbstractMediaItem>();
		this.serii = new HashMap<String, Series>();
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
					item.init();

					this.parsedMediaFiles.add(item);
				}else{
					//System.err.printf("Ignoring file: '%s'\n",url);
				}
			} catch(UnsupportedMediaException ex){
				System.err.printf("Unknown File format '%s'\n", ex.getNaughtyPath());
			}

		}
	}

	public void pullInSeries(){
		Set<String> sSeries =new HashSet<String>();
		for(AbstractMediaItem item:this.parsedMediaFiles){

			// If it's an actual video item
			if(item.getClass().equals(VideoItem.class)){
				VideoItem vid = (VideoItem)item;

				// If I've found a series, shove it in my list
				if(vid.getSeriesName() != null){
					sSeries.add(vid.getSeriesName());
				}
			}
		}
		for(String s: sSeries){

			List <Series> ids = tvdb.searchSeries(s, "en");
			if(ids.size() >0){
				// just assuming that the first one in the list is my best match
				this.serii.put(s, ids.get(0));
				System.err.printf("Just pulled in '%s' but really '%s'\n", s, ids.get(0).getSeriesName());
			}else{
				System.err.printf("Could not find a results for '%s'\n", s);
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


	public Set<AbstractMediaItem> getMediaItems(){
		return this.parsedMediaFiles;
	}
}
