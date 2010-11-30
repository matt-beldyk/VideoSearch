package org.beldyk.video.harvester;

import java.io.FileNotFoundException;
import java.util.Set;

import org.junit.Test;

import com.videoSearch.media.AbstractMediaItem;
import com.videoSearch.media.VideoItem;

public class IndexerTest {

	@Test
	public void indexVideoTV() throws Exception{
		Indexer dex = new Indexer("videoPatterns.txt", "/home/beldyk/union/media/video/Television/");
		dex.spiderFS();
		Set<String> fnames = dex.getFileNames();
		
		for (String fname:fnames){
			System.out.println(fname);
		}
		dex.parseFiles();
		
		for(AbstractMediaItem item:dex.getMediaItems()){
			if(VideoItem.class.equals(item.getClass())){
				System.out.println(((VideoItem)item).getSeriesName());
			}
		}
		
	}
}
