package org.beldyk.video.harvester;

import java.io.FileNotFoundException;
import java.util.Set;

import org.junit.Test;

import com.videoSearch.media.AbstractMediaItem;
import com.videoSearch.media.VideoItem;

public class IndexerTest {

	@Test
	public void indexVideoTV() throws Exception{
		Indexer dex = new Indexer("videoPatterns.txt", "/home/beldyk/homework_data/VideoSearch/television/");
		dex.spiderFS();
		Set<String> fnames = dex.getFileNames();

		for (String fname:fnames){
			System.out.println(fname);
		}
		dex.parseFiles();

		for(AbstractMediaItem item:dex.getMediaItems()){
			if(VideoItem.class.equals(item.getClass())){
				if( ((VideoItem)item).getSeriesName() != null){
					System.out.println(((VideoItem)item).getSeriesName());
				}else{
					System.out.println("Unable to parse '"+((VideoItem)item).getFileUrl()+"'");
				}

			}
		}

	}
}
