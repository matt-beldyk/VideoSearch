package com.videoSearch.media;

import java.util.HashSet;
import java.util.Set;

import org.beldyk.util.FileNameParser;

public class MediaItemFactory {
	public static AbstractMediaItem getHarvesterInstance(String path, FileNameParser fp) throws UnsupportedMediaException{

		String lcPath = path.toLowerCase();
		String [] videoTypes = {"avi", "mpg", "mpeg", "mov", "wmv", "rm", "m4v", "mp4", "divx", "ogg", "asf"};
		String [] audioTypes = {"mp3", "wav", "ogm"};
		String [] ignorableTypes = {"txt", "nfo", "zip", "jpg", "jpeg", "png", "gif", "rar", "sfv", "srt"};
		
		
		for(String posfix:videoTypes){
			if(lcPath.endsWith(posfix)){
				return new VideoItem(path, fp);

			}
		}
		
		for(String posfix:audioTypes){
			if(lcPath.endsWith(posfix)){
				return new AudioItem(path, fp);
			}
		}

		if (lcPath.endsWith("mkv")){
			return new MkvItem(path, fp);

		}
		
		for(String posfix:ignorableTypes){
			if(lcPath.endsWith(posfix)){
				return null;
			}
		}
		

		throw new UnsupportedMediaException(path);

	}

}
