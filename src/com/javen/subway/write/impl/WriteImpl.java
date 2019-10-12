package com.javen.subway.write.impl;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class WriteImpl {

	protected void writeLine(String fileName,String line) throws Exception {
		
		
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        
        out.write(line);
        
        out.flush();
        
        out.close();
		
	}
}
