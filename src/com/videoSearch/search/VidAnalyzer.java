package com.videoSearch.search;

import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseTokenizer;
import org.apache.lucene.analysis.PorterStemFilter;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;

public class VidAnalyzer extends Analyzer{

	


	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		StopFilter stopFilter = new StopFilter(true,
				new LowerCaseTokenizer(reader),
				StopAnalyzer.ENGLISH_STOP_WORDS_SET);
		stopFilter.setEnablePositionIncrements(true);
		return new PorterStemFilter(stopFilter);

	}

}



