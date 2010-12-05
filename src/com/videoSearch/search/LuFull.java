package com.videoSearch.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.beldyk.video.harvester.Indexer;

public class LuFull {
	LuIndexer luIndexer;
	LuQuerier luQuerier;

	private Directory indexDirectory;


	public LuFull() throws CorruptIndexException, LockObtainFailedException, IOException{


	}

	public void init() throws CorruptIndexException, IOException{
		indexDirectory = new RAMDirectory();

		IndexWriter iWriter = new IndexWriter( indexDirectory, 
				new VidAnalyzer(),
				true,
				IndexWriter.MaxFieldLength.UNLIMITED
		);

		Indexer dexer  = new Indexer("videoPatterns.txt", "testData/smallTest/");
		//Indexer dexer  = new Indexer("videoPatterns.txt", "testData/television/");

		luIndexer = new LuIndexer(iWriter, indexDirectory, dexer);
		//luIndexer.fullIndex();
		luIndexer.fullIndex();


		luQuerier = new LuQuerier(luIndexer.getIDir());
		
	}

	public Collection<String> search(String query) throws IOException, ParseException{
		Collection<Document> docs = this.luQuerier.search(query, 50);
		Collection<String> urls = new ArrayList<String>();
		for(Document doc: docs){
			urls.add(doc.getField("fileUrl").stringValue());
		}

		return urls;
	}

	public static void main(String[] args) throws CorruptIndexException, LockObtainFailedException, IOException, ParseException{
		LuFull luFull = new LuFull();
		luFull.init();
		java.io.BufferedReader stdin = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

		while(true){
			System.out.print("Enter your query:");
			String query = stdin.readLine();
			try {
				Collection<String> results = luFull.search(query);
				System.out.printf("Found %d results\n", results.size());
				for(String i:results){
					System.out.println(i);
				}
			}catch(ParseException e){
				System.err.println("Unable to parse '"+query+"'");
			}
		}
	}

}
