package com.github.pireba.followmeeapi.api;

import java.net.URI;
import java.util.List;

import com.github.pireba.followmeeapi.FollowMeeException;
import com.github.pireba.followmeeapi.UriBuilder;
import com.github.pireba.followmeeapi.model.Device;

public class DeviceApi extends AbstractApi {
	
	protected DeviceApi(FollowMeeApi followMeeApi) {
		super(followMeeApi);
	}
	
	public List<Device> getDeviceList() throws FollowMeeException {
		UriBuilder builder = super.createUriBuilder("info.aspx");
		builder.addParameter("function", "devicelist");
		URI uri = builder.build();
		
		return super.httpGetList(uri, Device.class);
	}
}