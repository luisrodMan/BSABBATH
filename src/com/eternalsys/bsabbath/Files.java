package com.eternalsys.bsabbath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Files {
	
	public static String read(String path) throws FileNotFoundException, IOException {
		try (InputStream stream = new FileInputStream(path)) {
			return read(stream);
		}
	}
	
	public static String read(File file) throws FileNotFoundException, IOException {
		try (InputStream stream = new FileInputStream(file)) {
			return read(stream);
		}
	}
	
	public static String read(InputStream stream) throws IOException {
		StringBuilder builder = new StringBuilder();
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		while ((line = reader.readLine()) != null)
			builder.append(line).append(System.lineSeparator());
		return builder.toString();
	}
	

}
