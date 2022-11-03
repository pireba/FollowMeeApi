package com.github.pireba.followmeeapi.model;

import java.time.OffsetDateTime;

import com.github.pireba.followmeeapi.gson.FollowMeeGson;
import com.google.gson.annotations.SerializedName;

public class Location {
	
	@SerializedName("Accuracy")
	private int accuracy;
	
	@SerializedName("Altitude(ft)")
	private int altitudeFt;
	
	@SerializedName("Altitude(m)")
	private int altitudeM;
	
	@SerializedName("Battery")
	private String battery;
	
	@SerializedName("Date")
	private OffsetDateTime date;
	
	@SerializedName("DeviceID")
	private int deviceId;
	
	@SerializedName("DeviceName")
	private String deviceName;
	
	@SerializedName("Direction")
	private int direction;
	
	@SerializedName("Latitude")
	private double latitude;
	
	@SerializedName("Longitude")
	private double longitude;
	
	@SerializedName("Speed(km/h)")
	private int speedKmh;
	
	@SerializedName("Speed(mph)")
	private int speedMph;
	
	@SerializedName("Type")
	private String type;
	
	public Location() {
		
	}

	public int getAccuracy() {
		return this.accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public int getAltitudeFt() {
		return this.altitudeFt;
	}

	public void setAltitudeFt(int altitudeFt) {
		this.altitudeFt = altitudeFt;
	}

	public int getAltitudeM() {
		return this.altitudeM;
	}

	public void setAltitudeM(int altitudeM) {
		this.altitudeM = altitudeM;
	}

	public String getBattery() {
		return this.battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public OffsetDateTime getDate() {
		return this.date;
	}

	public void setDate(OffsetDateTime date) {
		this.date = date;
	}

	public int getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getSpeedKmh() {
		return this.speedKmh;
	}

	public void setSpeedKmh(int speedKmh) {
		this.speedKmh = speedKmh;
	}

	public int getSpeedMph() {
		return this.speedMph;
	}

	public void setSpeedMph(int speedMph) {
		this.speedMph = speedMph;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return FollowMeeGson.create().toJson(this);
	}
}