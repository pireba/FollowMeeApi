package com.github.pireba.followmeeapi.api;

import java.net.URI;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.List;

import com.github.pireba.followmeeapi.FollowMeeException;
import com.github.pireba.followmeeapi.UriBuilder;
import com.github.pireba.followmeeapi.model.Device;
import com.github.pireba.followmeeapi.model.Location;
import com.github.pireba.followmeeapi.model.OutputFormat;

public class LocationApi extends AbstractApi {
	
	private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private boolean address = false;
	private OutputFormat outputFormat = OutputFormat.JSON;
	private boolean visit = false;
	
	protected LocationApi(FollowMeeApi followMeeApi) {
		super(followMeeApi);
	}
	
	public boolean isAddress() {
		return this.address;
	}
	
	public void disableAddress() {
		this.address = false;
	}
	
	public void enableAddress() {
		this.address = true;
	}
	
	public OutputFormat getOutputFormat() {
		return this.outputFormat;
	}
	
	public void setOutputFormatJSON(OutputFormat outputFormat) {
		this.outputFormat = outputFormat;
	}
	
	public boolean isVisit() {
		return this.visit;
	}
	
	public void disableVisit() {
		this.visit = false;
	}
	
	public void enableVisit() {
		this.visit = true;
	}
	
	// --------------------------------------------------
	// 
	// --------------------------------------------------
		
	public List<Location> getAllCurrentLocations(int... groupIds) throws FollowMeeException {
		UriBuilder builder = super.createUriBuilder("tracks.aspx");
		builder.addParameter("function", "currentforalldevices");
		builder.addParameter("groupid", this.join(groupIds));
		builder.addParameter("output", this.getOutputFormat());
		builder.addParameter("address", this.isAddress());
		URI uri = builder.build();
		
		return super.httpGetList(uri, Location.class);
	}
	
	// --------------------------------------------------
	// 
	// --------------------------------------------------
	
	public Location getCurrentLocation(int deviceId) throws FollowMeeException {
		List<Location> list = this.getCurrentLocation(new int[] {deviceId});
		
		if ( list == null || list.isEmpty() ) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	public List<Location> getCurrentLocation(int... deviceIds) throws FollowMeeException {
		if ( deviceIds == null || deviceIds.length == 0 ) {
			throw new IllegalArgumentException("At least one device id must be given.");
		}
		
		UriBuilder builder = super.createUriBuilder("tracks.aspx");
		builder.addParameter("function", "currentfordevice");
		builder.addParameter("deviceid", this.join(deviceIds));
		builder.addParameter("output", this.getOutputFormat());
		builder.addParameter("address", this.isAddress());
		URI uri = builder.build();
		
		return super.httpGetList(uri, Location.class);
	}
	
	public Location getCurrentLocation(Device device) throws FollowMeeException {
		return this.getCurrentLocation(device.getDeviceId());
	}
	
	public List<Location> getCurrentLocation(Collection<Device> devices) throws FollowMeeException {
		int[] array = devices.stream().mapToInt(Device::getDeviceId).toArray();
		return this.getCurrentLocation(array);
	}
	
	// --------------------------------------------------
	// 
	// --------------------------------------------------
	
	public List<Location> getLocationHistory(int deviceId, int hours) throws FollowMeeException {
		if ( hours < 1 || hours > 168 ) {
			throw new IllegalArgumentException("The number of hours can only be between 1 and 168: "+hours);
		}
		
		UriBuilder builder = super.createUriBuilder("tracks.aspx");
		builder.addParameter("function", "historyfordevice");
		builder.addParameter("deviceid", deviceId);
		builder.addParameter("history", hours);
		builder.addParameter("output", this.getOutputFormat());
		builder.addParameter("address", this.isAddress());
		builder.addParameter("visit", this.isVisit());
		URI uri = builder.build();
		
		return super.httpGetList(uri, Location.class);
	}
	
	public List<Location> getLocationHistory(int deviceId, LocalDate dateFrom, LocalDate dateTo) throws FollowMeeException {
		if ( Period.between(dateFrom, dateTo).getDays() > 7 ) {
			throw new IllegalArgumentException("The difference between the two dates cannot be greater than 7 days.");
		}
		
		UriBuilder builder = super.createUriBuilder("tracks.aspx");
		builder.addParameter("function", "daterangefordevice");
		builder.addParameter("deviceid", deviceId);
		builder.addParameter("from", dateFrom);
		builder.addParameter("to", dateTo);
		builder.addParameter("output", this.getOutputFormat());
		builder.addParameter("address", this.isAddress());
		builder.addParameter("visit", this.isVisit());
		URI uri = builder.build();
		
		return super.httpGetList(uri, Location.class);
	}
	
	public List<Location> getLocationHistory(int deviceId, String dateFrom, String dateTo) throws FollowMeeException {
		try {
			LocalDate localDateFrom = LocalDate.parse(dateFrom, dateFormat);
			LocalDate localDateTo = LocalDate.parse(dateTo, dateFormat);
			return this.getLocationHistory(deviceId, localDateFrom, localDateTo);
		} catch ( DateTimeParseException e ) {
			throw new IllegalArgumentException("Incorrect date format. Expected is yyyy-MM-dd.");
		}
	}
	
	// --------------------------------------------------
	// 
	// --------------------------------------------------
	
	private String join(int[] array) {
		if ( array == null || array.length == 0 ) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for ( int i=0; i<array.length; i++ ) {
			sb.append(array[i]);
			sb.append(",");
		}
		
		return sb.substring(0, sb.length()-1);
	}
}