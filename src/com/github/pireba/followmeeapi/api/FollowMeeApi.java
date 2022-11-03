package com.github.pireba.followmeeapi.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.github.pireba.followmeeapi.Constants;
import com.github.pireba.followmeeapi.FollowMeeException;

public class FollowMeeApi {
	
	private final String apiKey;
	private final HttpClient httpClient;
	private final String serverUrl;
	private final String username;
	
	private DeviceApi deviceApi;
	private LocationApi locationApi;
	
	public FollowMeeApi(String username, String apiKey) {
		this(Constants.DEFAULT_SERVER_URL, username, apiKey, HttpClient.newBuilder().build());
	}
	
	public FollowMeeApi(String serverUrl, String username, String apiKey) {
		this(serverUrl, username, apiKey, HttpClient.newBuilder().build());
	}
	
	public FollowMeeApi(String serverUrl, String username, String apiKey, HttpClient httpClient) {
		this.serverUrl = serverUrl;
		this.username = username;
		this.apiKey = apiKey;
		this.httpClient = httpClient;
	}
	
	public String getApiKey() {
		return this.apiKey;
	}
	
	public HttpClient getHttpClient() {
		return this.httpClient;
	}
	
	public String getServerUrl() {
		return this.serverUrl;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public DeviceApi getDeviceApi() {
		if ( this.deviceApi == null ) {
			synchronized (this) {
				this.deviceApi = new DeviceApi(this);
			}
		}
		
		return this.deviceApi;
	}
	
	public LocationApi getLocationApi() {
		if ( this.locationApi == null ) {
			synchronized (this) {
				this.locationApi = new LocationApi(this);
			}
		}
		
		return this.locationApi;
	}
	
	private HttpResponse<String> http(HttpRequest request) throws IOException, InterruptedException {
		return this.httpClient.send(request, BodyHandlers.ofString());
	}
	
	protected HttpResponse<String> httpGET(URI uri) throws FollowMeeException {
		try {
			Builder builder = HttpRequest.newBuilder();
			
			builder.header("accept", "application/json");
			builder.uri(uri);
			builder.GET();
			
			return this.http(builder.build());
		} catch (Exception e) {
			throw new FollowMeeException(e);
		}
	}
}