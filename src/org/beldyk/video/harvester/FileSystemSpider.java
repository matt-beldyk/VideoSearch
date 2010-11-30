package org.beldyk.video.harvester;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.util.Set;
import java.util.TreeSet;

public class FileSystemSpider implements AbstractSpider {

	private String fsRoot;
	private Set<String> allMediaFiles;
	
	
	public FileSystemSpider(String rootPath){
		fsRoot = rootPath;
		allMediaFiles = new TreeSet<String>();
	}
	@Override
	public void findMedia() {
		this.parseFs(new File(fsRoot));

	}

	@Override
	public Set<String> getUrls() {
		return allMediaFiles;
	}
	
	private void parseFs(File path){
		if(path.isHidden()){
			return;
		}
		if(path.isDirectory()){
			for( String pth: path.list()){
					parseFs(new File(path.getAbsolutePath() + File.separator + pth));
					}
		} else {
				allMediaFiles.add(path.getAbsolutePath());
		}
	}

}
