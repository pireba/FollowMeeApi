package com.github.pireba.followmeeapi.gson;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class BooleanSerializer implements JsonSerializer<Boolean>, JsonDeserializer<Boolean> {

	@Override
	public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return json.getAsInt() == 1;
	}

	@Override
	public JsonElement serialize(Boolean value, Type typeOfValue, JsonSerializationContext context) {
		return new JsonPrimitive(Boolean.TRUE.equals(value));
	}
	
}