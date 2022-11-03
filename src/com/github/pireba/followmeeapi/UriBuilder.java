package com.github.pireba.followmeeapi;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class UriBuilder {
	
	private final String serverUrl;
	private final Map<String, String> parameters = new LinkedHashMap<>();
	private final List<Object> path = new LinkedList<>();
	
	public UriBuilder(String serverUrl, Object... path) {
		this.serverUrl = serverUrl.replaceAll("/$", "");
		this.addPath(path);
	}
	
	public void addParameter(String key, Boolean value) {
		if ( value == null ) {
			return;
		}
		
		if ( value ) {
			this.addParameter(key, "1");
		} else {
			this.addParameter(key, "0");
		}
	}
	
	public void addParameter(String key, Object object) {
		if ( object == null ) {
			return;
		}
		
		this.addParameter(key, object.toString());
	}
	
	public void addParameter(String key, String value) {
		if ( value == null || value.isEmpty() ) {
			return;
		}
		
		this.parameters.put(key, value);
	}
	
	public void addPath(Object... path) {
		for ( Object o : path ) {
			this.path.add(o.toString());
		}
	}
	
	public URI build() {
		return URI.create(this.buildString());
	}
	
	public String buildString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.serverUrl);
		
		for ( Object path : this.path ) {
			sb.append("/");
			sb.append(path.toString());
		}
		
		sb.append("?");
		
		for ( Entry<String, String> entry : this.parameters.entrySet() ) {
			String key = entry.getKey();
			String value = entry.getValue();
			
			sb.append(key);
			sb.append("=");
			sb.append(value);
			sb.append("&");
		}
		
		return sb.substring(0, sb.length()-1);
	}
}