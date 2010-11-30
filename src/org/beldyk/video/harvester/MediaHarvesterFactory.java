package org.beldyk.video.harvester;

public class MediaHarvesterFactory {
	public static AbstractMediaHarvester getHarvesterInstance(String path) throws Exception{
		
		String lcPath = path.toLowerCase();
		
		if(lcPath.endsWith("avi")){
			return new AviHarvester(path);
		}else if (lcPath.endsWith("mp3")){
			return new Mp3Harvester(path);
		}else if (lcPath.endsWith("mkv")){
			return new MkvHarvester(path);
		}
		throw new Exception("Unknown filetype: "+path);
		
	}

}
