package com.github.pireba.followmeeapi.gson;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class OffsetDateTimeSerializer implements JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {
	
	private DateTimeFormatter dateFormat;
	
	public OffsetDateTimeSerializer() {
		this.setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
	}
	
	public OffsetDateTimeSerializer(DateTimeFormatter dateFormat) {
		this.setDateFormat(dateFormat);
	}
	
	public OffsetDateTimeSerializer(String dateFormat) {
		this.setDateFormat(dateFormat);
	}
	
	public DateTimeFormatter getDateFormat() {
		return this.dateFormat;
	}
	
	public void setDateFormat(DateTimeFormatter dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	public void setDateFormat(String dateFormat) {
		this.setDateFormat(DateTimeFormatter.ofPattern(dateFormat));
	}
	
	@Override
	public OffsetDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		return OffsetDateTime.parse(json.getAsString());
	}

	@Override
	public JsonElement serialize(OffsetDateTime value, Type typeOfValue, JsonSerializationContext context) {
		return new JsonPrimitive(value.toString());
	}
	
}