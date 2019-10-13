package com.javen.subway;

import com.javen.subway.io.ReadLine;
import com.javen.subway.io.WriteLine;
import com.javen.subway.io.WritePath;
import com.javen.subway.model.BuildMap;

public class subway {

	public static void main(String[] args) throws Exception {
		
		String line="-a \\S+ -map \\S+ -o \\S+ ";
		String path="-b \\S+ \\S+ -map \\S+ -o \\S+ ";
		
		String arge="";
		for(String i:args) {
			
			arge+=i+" ";
		}
		
		if(arge.matches(line)) {
			
			String lineName=args[1];
			String readFile=args[3];
			String writeFile=args[5];
			
			@SuppressWarnings("unused")
			ReadLine readLine=new ReadLine(readFile);
			@SuppressWarnings("unused")
			BuildMap buildMap=new BuildMap();
			
			@SuppressWarnings("unused")
			WriteLine writeLine=new WriteLine(writeFile, lineName);
		}else if(arge.matches(path)){
			
			String start=args[1];
			String end=args[2];
			String readFile=args[4];
			String writeFile=args[6];
			
			@SuppressWarnings("unused")
			ReadLine readLine=new ReadLine(readFile);
			@SuppressWarnings("unused")
			BuildMap buildMap=new BuildMap();
			
			@SuppressWarnings("unused")
			WritePath writePath=new WritePath(writeFile,start,end);
		}else {
			System.out.println("��������ȷ�Ĳ���!!!");
		}
		
		
	}

	/* ���Բ����ο�
	 * java subway -a 1���� -map subway.xml -o station.txt
	 * java subway -b ������ ������ -map subway.xml -o routine.txt
	*/
}
