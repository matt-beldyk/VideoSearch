package com.videoSearch.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;

public class LuQuerier {
	protected Directory iDir;
	protected IndexSearcher iSearcher;
	protected QueryParser qParser;
	
	public LuQuerier(Directory iDir) throws CorruptIndexException, IOException{
		this.iDir = iDir;
		this.iSearcher = new IndexSearcher(iDir);
		
		String[] fields = { "fileUrl", "seriesName", "seriesDesc"};
		this.qParser = new MultiFieldQueryParser(
				Version.LUCENE_30, 
				fields, 
				new VidAnalyzer()
				);
	}
	
	
	public Collection<Document> search(String queryString, Integer howMany) throws IOException, ParseException{
		Query query = qParser.parse(queryString);

		ScoreDoc[] hits = iSearcher.search(query, howMany).scoreDocs;
		System.err.printf("score docs count:%d\n", hits.length);
		Collection<Document> docs = new ArrayList<Document>();
		for (ScoreDoc hit: hits) {
			Document hitDoc = iSearcher.doc(hit.doc);
			docs.add(hitDoc);
		//	System.out.println(hitDoc.toString());
		}
		return docs;
		
	}
	
	
	

}
