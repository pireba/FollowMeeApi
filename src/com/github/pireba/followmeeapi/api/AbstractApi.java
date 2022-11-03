package com.github.pireba.followmeeapi.api;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.github.pireba.followmeeapi.FollowMeeException;
import com.github.pireba.followmeeapi.UriBuilder;
import com.github.pireba.followmeeapi.gson.FollowMeeGson;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class AbstractApi {
	
	private final FollowMeeApi followMeeApi;
	private final Gson gson;
	
	protected AbstractApi(FollowMeeApi followMeeApi) {
		this.followMeeApi = followMeeApi;
		this.gson = FollowMeeGson.create();
	}
	
	protected FollowMeeApi getFollowMeeApi() {
		return this.followMeeApi;
	}
	
	private JsonElement createJsonElement(HttpResponse<String> response) throws FollowMeeException {
		if ( response.statusCode() != 200 ) {
			throw new FollowMeeException("The HTTP status code is not 200: "+response.statusCode());
		}
		
		JsonObject object = this.gson.fromJson(response.body(), JsonObject.class);
		
		if ( object.has("Error") ) {
			return object.get("Error");
		}
		
		if ( object.has("Data") ) {
			return object.get("Data");
		}
		
		throw new FollowMeeException("No valid JSON element exists.");
	}
	
	protected <T> T httpGet(URI uri, Class<T> clazz) throws FollowMeeException {
		HttpResponse<String> response = this.followMeeApi.httpGET(uri);
		JsonElement element = this.createJsonElement(response);
		return this.gson.fromJson(element, clazz);
	}
	
	protected <T> List<T> httpGetList(URI uri, Class<T> clazz) throws FollowMeeException {
		HttpResponse<String> response = this.followMeeApi.httpGET(uri);
		JsonElement element = this.createJsonElement(response);
		
		try {
			JsonArray array = element.getAsJsonArray();
			
			List<T> list = new ArrayList<>();
			for ( JsonElement e : array ) {
				list.add(this.gson.fromJson(e, clazz));
			}
			
			return list;
		} catch ( IllegalStateException e ) {
			return new ArrayList<>();
		}
	}
	
	protected UriBuilder createUriBuilder(Object... path) {
		UriBuilder builder = new UriBuilder(this.getFollowMeeApi().getServerUrl(), path);
		builder.addParameter("key", this.getFollowMeeApi().getApiKey());
		builder.addParameter("username", this.getFollowMeeApi().getUsername());
		return builder;
	}
}