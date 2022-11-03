package com.github.pireba.followmeeapi.model;

import java.time.OffsetDateTime;

import com.github.pireba.followmeeapi.gson.FollowMeeGson;
import com.google.gson.annotations.SerializedName;

public class Device {
	
	@SerializedName("Active")
	private boolean active;
	
	@SerializedName("Battery")
	private String battery;
	
	@SerializedName("BatteryTime")
	private OffsetDateTime batteryTime;
	
	@SerializedName("DeviceID")
	private int deviceId;
	
	@SerializedName("DeviceName")
	private String deviceName;
	
	@SerializedName("Edition")
	private String edition;
	
	@SerializedName("Error")
	private String error;
	
	@SerializedName("Group")
	private String group;
	
	@SerializedName("Platform")
	private String platform;
	
	@SerializedName("TrackerState")
	private int trackerState;
	
	@SerializedName("TrackerStateTime")
	private OffsetDateTime trackerStateTime;
	
	public Device() {
		
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getBattery() {
		return this.battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public OffsetDateTime getBatteryTime() {
		return this.batteryTime;
	}

	public void setBatteryTime(OffsetDateTime batteryTime) {
		this.batteryTime = batteryTime;
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

	public String getEdition() {
		return this.edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getGroup() {
		return this.group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public int getTrackerState() {
		return this.trackerState;
	}

	public void setTrackerState(int trackerState) {
		this.trackerState = trackerState;
	}

	public OffsetDateTime getTrackerStateTime() {
		return this.trackerStateTime;
	}

	public void setTrackerStateTime(OffsetDateTime trackerStateTime) {
		this.trackerStateTime = trackerStateTime;
	}
	
	@Override
	public String toString() {
		return FollowMeeGson.create().toJson(this);
	}
}