package com.javen.subway.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.javen.subway.control.Dijkstra;
import com.javen.subway.io.ReadLine;

public class BuildMap {

	private static Map<Integer,Station>map;
	
	private static Map<String,Integer>numMap;
	
	private static Set<String>set;
	
	private static Map<String,Line>lines;
	
	private static Set<List<String>>readSet;
	
	public static int count;
	
	public BuildMap() {
		
		init();
		
		statisticsStation();
		
		getHashMap();
		
		buildMap();

	}
	
	private void init() {
		
		map=new HashMap<Integer, Station>();
		numMap=new HashMap<String,Integer>();
		set=new HashSet<String>();
		lines=new HashMap<String,Line>();
		readSet=ReadLine.getSet();
		
	}
	
	private void statisticsStation() {
		
		for(List<String> lineList:readSet) {
			
			for(int i=1;i<lineList.size();i++) {
				
				set.add(lineList.get(i));
				
			}
		}
	}
	
	private void getHashMap() {
		
		count=0;
		
		for(String s:set) {
			
			Station station=new Station(s);
			
			map.put(count, station);
			numMap.put(s,count);
			count++;
			
		}
		
	}
	
	private void buildMap() {
		
		for(List<String> lineList:readSet) {
			
			Line line=new Line(lineList.get(0));
			
			for(int i=1;i<lineList.size();i++) {
				
				Station station=map.get(numMap.get(lineList.get(i)));
				
				station.addLine(line);
				if(i!=1) {
					Station laStation=map.get(numMap.get(lineList.get(i-1)));
					
					station.addConn(laStation);
					laStation.addConn(station);
				}
				
				line.addStation(station);
			}
			
			lines.put(lineList.get(0), line);
			
		}
		
	}
	
	public static Map<String,Line> getLines(){
		
		return lines;
	}
	
	public static Map<Integer,Station> getMap(){
		
		return map;
	}
	
	public static Map<String,Integer> getNumMap(){
		
		return numMap;
	}
	
	public static List<Line> getPath(List<Integer> pathIndex){

		List<Line>path=new ArrayList<Line>();
		
		int len=pathIndex.size();
		
		if(len==1) {
			
			Station station=map.get(pathIndex.get(0));
			Line line = null;
			
			for(Line l:station.getLine()) {
				
				line=new Line(l.getLineName());
				break;
			}
			
			line.addStation(station);
			path.add(line);
		}
		else if(len==2) {
			
			Station station1=map.get(pathIndex.get(0));
			Station station2=map.get(pathIndex.get(1));
			
			Line line=null;
			
			for(Line l:station1.getLine()) {
				
				if(station2.getLine().contains(l)) {
					
					line=new Line(l.getLineName());
					
					line.addStation(station1);
					line.addStation(station2);
					path.add(line);
					
					break;
				}
				
			}
			
		}
		else {
			
			Station laStation=map.get(pathIndex.get(0));
			Station noStation=map.get(pathIndex.get(1));
			
			Line laLine=null;
			
			for(int i=0;i<len-1;i++) {
			
				if(i==0||(!noStation.getLine().contains(lines.get(laLine.getLineName())))) {
					
					for(Line l:laStation.getLine()) {
						
						if(noStation.getLine().contains(l)) {
							
							laLine=new Line(l.getLineName());
							
							laLine.addStation(laStation);
							laLine.addStation(noStation);
							
							path.add(laLine);
						}
					}
				}
				else {
					
					laLine.addStation(noStation);
				}
				
				laStation=noStation;
				noStation=map.get(pathIndex.get(i+1));
				
				
			}
		}
		
		
		return path;
	}
}
