package com.eternalsys.bsabbath;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		MultilineProperties properties = new MultilineProperties();
		properties.load("multiproperties_test.xd");
		for (String key : properties.keySet()) 
			System.out.println("prop -> [" + key + "] { " + properties.getValue(key) + " }");
	}

}
