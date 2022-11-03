package com.github.pireba.followmeeapi.model;

public enum OutputFormat {
	
	JSON;
//	KML;
	
	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}