package com.javen.subway.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.javen.subway.model.BuildMap;
import com.javen.subway.model.Line;
import com.javen.subway.model.Station;
import com.javen.subway.write.impl.WriteImpl;
import com.sun.org.apache.bcel.internal.generic.StackInstruction;
import com.sun.org.apache.xerces.internal.util.EntityResolver2Wrapper;

import sun.awt.windows.WLightweightFramePeer;
import sun.print.resources.serviceui;

public class WriteLine extends WriteImpl {

	private List<String>wLine;
	
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
