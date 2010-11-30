package com.thetvdb.api;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.xml.sax.SAXException;

public class SeriesZipGrabber {
	private String mirrorPath;
	public SeriesZipGrabber() throws ClientProtocolException, IOException, ParserConfigurationException, SAXException{
		Mirrors m = new Mirrors();
		mirrorPath = m.getZipMirror();
	}

	public void downloadSeriesZip(Integer seriesID) throws IOException{
		InputStream is = null;
		OutputStream ost = null;
		try{
			URL u = new URL( mirrorPath + "/api/" + Confs.APIKey() + "/series/" +seriesID +"/all/en.zip");
			is =u.openConnection().getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			ost = new BufferedOutputStream(
					new FileOutputStream(
							new File(
									Confs.cachedSeriesZipPath() +File.separator+seriesID+".zip")));

			byte[] buf = new byte[4*1024];
			int ByteRead;
			int ByteWritten = 0;
			while ((ByteRead = bis.read(buf)) != -1) {
				ost.write(buf, 0, ByteRead);
				ByteWritten += ByteRead;
			}

		}finally{
			ost.close();
			is.close();
		}
	}

}
