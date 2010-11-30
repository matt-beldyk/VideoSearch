package org.beldyk.video.harvester;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PattMatcher {
	private Map <String, Integer> tags;
	private Pattern patt;


	public PattMatcher(String pattern, String [] tgs){
		tags = new HashMap<String, Integer>();

		patt = Pattern.compile(pattern);
		for(Integer i = 0; i<tgs.length; i++){
			tags.put(tgs[i], i+1);
		}
	}
	public PattMatcher(String[] tgs) {
		tags = new HashMap<String, Integer>();

		patt = Pattern.compile(tgs[0]);
		for(Integer i = 1; i<tgs.length; i++){
			tags.put(tgs[i], i);
		}
	}
	public Map<String, String> parseString(String str){
		Matcher mat = patt.matcher(str);
		if (mat.find()){

			/*		if(!mat.matches()){
			System.err.printf("pattern:'%s' does not match string '%s'\n", patt.toString(), str);
			return null;
		}
			 */
			Map<String, String> pTags = new HashMap<String, String>();
			for(String tag: tags.keySet()){
				System.err.println(tag+":"+tags.get(tag));
				pTags.put(tag, mat.group(tags.get(tag)));
			}

			return pTags;
		}else{
			return null;
		}
	}
}
