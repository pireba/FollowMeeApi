[![Maven Central](https://img.shields.io/maven-central/v/com.github.pireba/followmeeapi?color=success&logo=apachemaven&style=for-the-badge)](https://mvnrepository.com/artifact/com.github.pireba/followmeeapi)
[![Javadoc](https://javadoc.io/badge2/com.github.pireba/followmeeapi/javadoc.svg?color=success&logo=readthedocs&style=for-the-badge)](https://javadoc.io/doc/com.github.pireba/followmeeapi)

# FollowMeeApi

A Java API for the FollowMee Web Service.

[FollowMee Web Service Documentation](https://www.followmee.com/apidoc.aspx)

In addition to JSON, the FollowMee web service also supports KML as an output format. However, this Java API currently does not.

To use the web service, the "Web Service API" service must be booked for currently $2.99 per device.

# API Overview

## DeviceApi

### Retrieve every Device

```
FollowMeeApi followMeeApi = new FollowMeeApi(username, apiKey);
DeviceApi deviceApi = followMeeApi.getDeviceApi();
List<Device> devices = deviceApi.getDeviceList();
for ( Device device : devices ) {
	System.out.println(device.getDeviceName());
	System.out.println(device.getPlatform());
	...
}
```

## LocationApi

### Get current location for all devices

```
FollowMeeApi followMeeApi = new FollowMeeApi(username, apiKey);
LocationApi locationApi = followMeeApi.getLocationApi();
List<Location> locations = locationApi.getAllCurrentLocations();
for ( Location location : locations ) {
	System.out.println(location.getLatitude());
	System.out.println(location.getLongitude());
	...
}
```

### Get current location for all devices of group

```
FollowMeeApi followMeeApi = new FollowMeeApi(username, apiKey);
LocationApi locationApi = followMeeApi.getLocationApi();
List<Location> locations = locationApi.getAllCurrentLocations(1111, 2222);
for ( Location location : locations ) {
	System.out.println(location.getLatitude());
	System.out.println(location.getLongitude());
	...
}
```

### Get current location for one device

```
FollowMeeApi followMeeApi = new FollowMeeApi(username, apiKey);
LocationApi locationApi = followMeeApi.getLocationApi();
Location location = locationApi.getCurrentLocation(1234);
System.out.println(location.getLatitude());
System.out.println(location.getLongitude());
...
```

### Get current location for multiple devices

```
FollowMeeApi followMeeApi = new FollowMeeApi(username, apiKey);
LocationApi locationApi = followMeeApi.getLocationApi();
List<Location> locations = locationApi.getCurrentLocation(1234, 5678);
for ( Location location : locations ) {
	System.out.println(location.getLatitude());
	System.out.println(location.getLongitude());
	...
}
```

### Get device location history for the last n hours

```
FollowMeeApi followMeeApi = new FollowMeeApi(username, apiKey);
LocationApi locationApi = followMeeApi.getLocationApi();
List<Location> locations = locationApi.getLocationHistory(deviceId, 48);
for ( Location location : locations ) {
	System.out.println(location.getLatitude()+","+location.getLongitude());
	...
}
```

### Get device location history between two dates

```
FollowMeeApi followMeeApi = new FollowMeeApi(username, apiKey);
LocationApi locationApi = followMeeApi.getLocationApi();
LocalDate dateFrom = LocalDate.of(2022, 10, 31);
LocalDate dateTo = LocalDate.of(2022, 11, 2);
List<Location> locations = locationApi.getLocationHistory(deviceId, dateFrom, dateTo);
for ( Location location : locations ) {
	System.out.println(location.getLatitude()+","+location.getLongitude());
	...
}
```
