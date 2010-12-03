package com.videoSearch.search;

import java.io.IOException;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;

public class LuQuerier {
	protected Directory iDir;
	protected IndexSearcher iSearcher;
	protected QueryParser qParser;
	
	public LuQuerier(Directory iDir) throws CorruptIndexException, IOException{
		this.iDir = iDir;
		this.iSearcher = new IndexSearcher(iDir);
	}
	
	

}
