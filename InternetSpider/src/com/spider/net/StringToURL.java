package com.spider.net;

import java.net.MalformedURLException;
import java.net.URL;

public class StringToURL {
	public static URL changeToURL(String url) throws MalformedURLException {
		return new URL(url);
	}
}
