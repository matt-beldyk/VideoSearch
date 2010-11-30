package com.thetvdb.api;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;



public class Mirrors {

	private List<String> zipMirrors;

	public Mirrors() throws ClientProtocolException, IOException, ParserConfigurationException, SAXException{
		zipMirrors = this.findListMirrors();
	}
	
	public String getZipMirror(){
		Random rn = new Random();
		if(zipMirrors.size() > 1){
			return zipMirrors.get(rn.nextInt(zipMirrors.size()-1));
		}else{
			return zipMirrors.get(0);
		}
		
	}
	
	public String getMirrorXML() throws ClientProtocolException, IOException{
		String request = "http://thetvdb.com/api/"+Confs.APIKey()+"/mirrors.xml";
		HttpHost target = new HttpHost("thetvdb.com", 80, "http");

		// general setup
		SchemeRegistry supportedSchemes = new SchemeRegistry();

		// Register the "http" protocol scheme, it is required
		// by the default operator to look up socket factories.
		supportedSchemes.register(new Scheme("http", 
				PlainSocketFactory.getSocketFactory(), 80));

		// prepare parameters
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "UTF-8");
		HttpProtocolParams.setUseExpectContinue(params, true);

		ClientConnectionManager connMgr = new ThreadSafeClientConnManager(params, 
				supportedSchemes);
		DefaultHttpClient httpclient = new DefaultHttpClient(connMgr, params);

		HttpGet req = new HttpGet("/api/"+Confs.APIKey()+"/mirrors.xml");

		HttpResponse rsp = httpclient.execute(target, req);
		HttpEntity entity = rsp.getEntity();

		String mirrorsXml = null;
		if (entity != null) {

			mirrorsXml = EntityUtils.toString(entity);
		}

		// When HttpClient instance is no longer needed, 
		// shut down the connection manager to ensure
		// immediate deallocation of all system resources
		httpclient.getConnectionManager().shutdown();    
		return mirrorsXml;
	}

	public List<String> findListMirrors() throws ClientProtocolException, IOException, ParserConfigurationException, SAXException{
	//	String mirrorsXml = this.getMirrorXML();

		List <String> paths = new ArrayList<String>();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		Document doc = db.parse("http://thetvdb.com/api/"+Confs.APIKey()+"/mirrors.xml");
		doc.getDocumentElement().normalize();
	//	System.out.println("Root element '" + doc.getDocumentElement().getNodeName()+"'");
		NodeList nodeLst = doc.getElementsByTagName("Mirror");

		for (int s = 0; s < nodeLst.getLength(); s++) {

			Node fstNode = nodeLst.item(s);

			if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

				Element fstElmnt = (Element) fstNode;
				NodeList pthElmntLst = fstElmnt.getElementsByTagName("mirrorpath");
				Element pthElmnt = (Element) pthElmntLst.item(0);
				NodeList pth = pthElmnt.getChildNodes();
			//	System.out.println("Mirror Path : "  + ((Node) pth.item(0)).getNodeValue());
				paths.add( ( (Node) pth.item(0)).getNodeValue());
			}

			
		}
		return paths;
	}
}
	