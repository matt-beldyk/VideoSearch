package com.videoSearch.media;

import org.beldyk.util.FileNameParser;

public class MediaItemFactory {
	public static AbstractMediaItem getHarvesterInstance(String path, FileNameParser fp) throws Exception{
		
		String lcPath = path.toLowerCase();
		
		if(lcPath.endsWith("avi")){
			return new VideoItem(path, fp);
			
		}else if (lcPath.endsWith("mp3")){
			return new AudioItem(path, fp);
			
		}else if (lcPath.endsWith("mkv")){
			return new MkvItem(path, fp);
			
		}
		throw new Exception("Unknown filetype: "+path);
		
	}

}
