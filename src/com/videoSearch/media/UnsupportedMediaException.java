package com.videoSearch.media;

public class UnsupportedMediaException extends Exception {

	String naughtyPath;
	public UnsupportedMediaException(String path) {
		this.naughtyPath = path;
	}

	public String getNaughtyPath() {
		return naughtyPath;
	}

	public void setNaughtyPath(String naughtyPath) {
		this.naughtyPath = naughtyPath;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6550034237241307413L;

}
