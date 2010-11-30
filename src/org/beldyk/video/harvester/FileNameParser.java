package org.beldyk.video.harvester;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameParser {
	private Collection<PattMatcher> pattMatch;

	public FileNameParser(String pattFile) throws FileNotFoundException{
		pattMatch = getPattsFromFile(pattFile);
	}

	/**
	 * creates a collection of pattern matchers from a file that describes how 
	 * they should be defined
	 * 
	 * @param pattFile
	 * @return
	 * @throws FileNotFoundException
	 */
	private Collection<PattMatcher> getPattsFromFile(String pattFile) throws FileNotFoundException{
		Collection<PattMatcher> patts = new ArrayList<PattMatcher>();
		Scanner scanner = new Scanner(new File(pattFile),"UTF-8");
		try {
			while (scanner.hasNextLine()){
				String line = scanner.nextLine();
				if(! ( 
						line.startsWith("#")||
						line.length() <1
						)){
					patts.add(pattLine2Matcher(line));
				}
			}
		}finally{
			scanner.close();
		}

		return patts;
	}

	protected PattMatcher pattLine2Matcher(String line){
		return new PattMatcher(line.split("##"));
	}

	public Map<String, String> parseFileName(String fileName){
		for(PattMatcher pm: pattMatch){
			Map<String, String> tags = pm.parseString(fileName);
			if(!(tags == null)){ // I've found a match
				return tags;
			}
			
		}
		return null;
	}
}
