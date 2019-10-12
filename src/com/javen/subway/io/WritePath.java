package com.javen.subway.io;

import java.util.ArrayList;
import java.util.List;

import com.javen.subway.control.Dijkstra;
import com.javen.subway.model.Line;
import com.javen.subway.model.Station;
import com.javen.subway.write.impl.WriteImpl;

public class WritePath extends WriteImpl{

	public WritePath(String fileName,String start,String end) throws Exception {
		
		String path=getPath(start,end);
		
		writeLine(fileName,path);
		
	}
	private String getPath(String start,String end) {
		
		int stationNum=0;
		
		String path="";
		
		List<Line> lines=(new Dijkstra(start, end)).getPath();
		
		
		for(Line line:lines) {
			path=path+line.getLineName()+"\r\n";
			
			stationNum+=line.getLen()-1;
			for(Station station:line.getStations()) {
				
				path=path+station.getStationName()+"\r\n";
			}
			path=path+"\r\n";
		}
		path=stationNum+"\r\n"+path;
		
		return path;
	}
}
