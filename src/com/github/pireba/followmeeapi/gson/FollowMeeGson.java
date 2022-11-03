package com.github.pireba.followmeeapi.gson;

import java.time.OffsetDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FollowMeeGson {
	
	public static Gson create() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		
		BooleanSerializer booleanSerializer = new BooleanSerializer();
		gsonBuilder.registerTypeAdapter(Boolean.class, booleanSerializer);
		gsonBuilder.registerTypeAdapter(boolean.class, booleanSerializer);
		
		OffsetDateTimeSerializer localDateTimeSerializer = new OffsetDateTimeSerializer();
		gsonBuilder.registerTypeAdapter(OffsetDateTime.class, localDateTimeSerializer);
		
		return gsonBuilder.create();
	}
}