package com.javen.subway.io;

import com.javen.subway.model.BuildMap;
import com.javen.subway.model.Line;
import com.javen.subway.model.Station;
import com.javen.subway.write.impl.WriteImpl;


public class WriteLine extends WriteImpl {

	public WriteLine(String fileName,String lineName) throws Exception {
		
		String line=getLine(lineName);
		
		writeLine(fileName,line);
		
	}
	private String getLine(String lineName)throws Exception {
		
		Line line=null;
		line=BuildMap.getLines().get(lineName);
		String lines=lineName+"\r\n";
		if(line==null) {
			
			System.out.println("线路 "+lineName+" 不存在");
			
		}
		else {
			
			for(Station station:line.getStations()) {
				
				lines=lines+station.getStationName()+"\r\n";
			}
			
		}
		return lines;
		
	}

	
}
