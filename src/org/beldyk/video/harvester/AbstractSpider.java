package org.beldyk.video.harvester;

import java.util.Set;

public interface  AbstractSpider {
	abstract public void findMedia();
	
	abstract public Set<String> getUrls();

}
