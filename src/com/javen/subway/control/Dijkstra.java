package com.javen.subway.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.javen.subway.model.BuildMap;
import com.javen.subway.model.Line;
import com.javen.subway.model.Station;

import sun.net.www.content.audio.x_aiff;

public class Dijkstra {
	
	private int inf;
	private int len;
	private int startId;
	private int endId;
	
	private int[] dis;
	private int[] vis;
	private Map<Integer,Set<Integer>>from;
	
	private List<Line>bestPath;
	private List<Integer>path;
	
	public Dijkstra(String start,String end) {
		
		init(start,end);
		
		dijkstra();
		
		if(dis[endId]==inf) {
			
			System.out.println("线路  "+start+" 与 "+end+" 之间不存在线路");
			System.exit(0);
		}
		
		buildPath(endId);
	}
	
	private void init(String start,String end) {
		if(start.equals(end)) {
			System.out.println("别浪费钱啊！");
			System.exit(0);
		}
		
		inf=Integer.MAX_VALUE;
		
		bestPath=null;
		path=new ArrayList<Integer>();
		
		len=BuildMap.getMap().size();
		
		if(BuildMap.getNumMap().containsKey(start)) {
			startId=BuildMap.getNumMap().get(start);
		}
		else {
			System.out.println("站点 "+start+" 不存在");
			System.exit(0);
		}
		if(BuildMap.getNumMap().containsKey(end)) {
			endId=BuildMap.getNumMap().get(end);
		}
		else {
			System.out.println("站点 "+end+" 不存在");
			System.exit(0);
		}
		
		from=new HashMap<Integer, Set<Integer>>();
		
		dis=new int[len];
		vis=new int[len];
		
		for(int i=0;i<len;i++) {
			
			Set set=new HashSet<Integer>();
			from.put(i,set);
			vis[i]=0;
			dis[i]=inf;
			
		}
		
		dis[startId]=0;
		vis[startId]=1;
		
		Station station=BuildMap.getMap().get(startId);
		
		for(Station s:station.getConn()) {
			
			int conn=BuildMap.getNumMap().get(s.getStationName());
			dis[conn]=1;
			from.get(conn).add(startId);
			
		}

	}
	
	private void dijkstra() {
	
		while(true) {
			
			int x=-1;
			int minx=inf;
			
			for(int i=0;i<len;i++) {
				
				if(vis[i]==1||dis[i]==inf)continue;
				
				if(dis[i]<minx) {
					
					minx=dis[i];
					x=i;
				}
			}
			
			if(x==-1)break;
			
			vis[x]=1;
			for(Station stationy : BuildMap.getMap().get(x).getConn()) {
				
				int y=BuildMap.getNumMap().get(stationy.getStationName());
				
				if(vis[y]==1)continue;
				
				if(dis[x]+1==dis[y]) {
					
					from.get(y).clear();
					from.get(y).add(x);
					
				}
				else if(dis[x]+1<dis[y]) {
					
					dis[y]=dis[x]+1;
					from.get(y).add(x);
				}
			}
			
			
		}
		
	}
	
	public void buildPath(int now) {
		
		path.add(0,now);
		
		if(now==startId) {
			
			List<Line>nowPath=BuildMap.getPath(path);
			
			if(bestPath==null) {
				bestPath=nowPath;
			}
			else {
				
				if(nowPath.size()<bestPath.size()) {
					
					bestPath=nowPath;
				}
			}
		}
		else {
			
			for(int last:from.get(now)) {
				
				buildPath(last);
			}
		}
		path.remove(0);
		
	}
	
	public List<Line> getPath(){
		
		return this.bestPath;
	}
}

