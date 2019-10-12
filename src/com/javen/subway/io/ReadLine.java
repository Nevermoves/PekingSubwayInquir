package com.javen.subway.io;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadLine {
	
	private static Set<List<String>>set;
	

	public ReadLine(String fileName) throws Exception {
		
//		File file=new File("subway.xml");
		File file=new File(fileName);
		
		if(!file.exists()) {
			System.out.println("文件"+fileName+"不存在.");
			System.exit(0);
		}
		set=new HashSet<List<String>>();
		
		DocumentBuilder documentBuilder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
		
		Document document=documentBuilder.parse(file);
		
		Element root=document.getDocumentElement();
		
		NodeList lineList=root.getElementsByTagName("Line");
		
		for(int i=0;i<lineList.getLength();i++) {
			
			Node liNode=lineList.item(i);
			Element element=(Element) liNode;
			NodeList stationList=element.getElementsByTagName("Station");
			List<String>stations=new ArrayList<String>();
			
			NamedNodeMap nameNodeMap=liNode.getAttributes();
			String id=nameNodeMap.getNamedItem("id").getTextContent();
			stations.add(id);
			
			for(int j=0;j<stationList.getLength();j++) {
				
				stations.add(stationList.item(j).getTextContent());
			}
			
			set.add(stations);
		}
		
	}
	public static Set<List<String>> getSet(){
		return set;
	}
}
