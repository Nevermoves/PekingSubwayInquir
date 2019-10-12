package com.javen.subway.model;

import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.NameList;

public class Line {
	
	private String lineName;
	private List<Station>stations;
	
	public Line(String name) {
		this.lineName=name;
		stations=new ArrayList<Station>();
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public List<Station> getStations() {
		return stations;
	}
	public int getLen() {
		return this.stations.size();
	}
	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
	
	public void addStation(Station station) {
		this.stations.add(station);
	}
	
}
