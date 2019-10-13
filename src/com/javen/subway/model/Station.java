package com.javen.subway.model;

import java.util.HashSet;
import java.util.Set;

public class Station {
	
	private String stationName;
	private Set<Line>line;
	private Set<Station>conn;
	
	
	public Station(String name) {
		this.stationName=name;
		line=new HashSet<Line>();
		conn=new HashSet<Station>();
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Set<Line> getLine() {
		return line;
	}
	public void setLine(Set<Line> line) {
		this.line = line;
	}
	public Set<Station> getConn() {
		return conn;
	}
	public void setConn(Set<Station> conn) {
		this.conn = conn;
	}
	
	public void addLine(Line lines) {
		this.line.add(lines);
	}
	public void addConn(Station station) {
		this.conn.add(station);
	}
}
