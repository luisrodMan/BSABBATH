package com.eternalsys.bsabbath;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MultilineProperties {
	
	private String multilineSeparator;
	private String separator;
	private String commentSeparator;
	
	private Map<String, String> values = new HashMap<>();
	private String lineSeparator = System.lineSeparator();
	
	public MultilineProperties() {
		this("=", ":=", "#");
	}
	
	public MultilineProperties(String separator, String mutilineSeparator, String commentSeparator) {
		setPropertySeparator(separator);
		setPropertySeparatorMultiline(mutilineSeparator);
		setCommentSeparator(commentSeparator);
	}

	public void setCommentSeparator(String commentSeparator2) {
		commentSeparator = commentSeparator2;
	}

	public void setPropertySeparatorMultiline(String multilineSeparator2) {
		multilineSeparator = multilineSeparator2;
	}

	public void setPropertySeparator(String separator) {
		this.separator = separator;
	}
	
	public void setLineSeparator(String lineSeparator) {
		this.lineSeparator = lineSeparator;
	}
	
	public String getLineSeparator() {
		return lineSeparator;
	}
	
	public void addProperty(String name, String value) {
		values.put(name, value);
	}
	
	public String getValue(String name) {
		return values.get(name);
	}
	
	public Set<String> keySet() {
		return values.keySet();
	}
	
	public void load(String filepath) throws FileNotFoundException, IOException {
		String data = Files.read(filepath);
		String[] lines = data.split(lineSeparator);
		String name = null;
		String value = null;
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
//			System.out.println("line["+line+"]");
			if (line.length() > 0 && !line.startsWith(commentSeparator)) {
				if (name==null && Character.isWhitespace(line.charAt(0))) {
					if (!line.trim().isEmpty())
						throw new RuntimeException("Error at line: " + (i+1) + " value: " + line);
				} else {
					int div = line.indexOf(separator);
					int div2 = line.indexOf(multilineSeparator);
					int d = div==-1||div2==-1? Math.max(div, div2) : Math.min(div, div2);
					if (d != -1) {
						if (name != null)
							values.put(name, value);
						name = line.substring(0, d).trim();
						value = line.substring(d + (d==div?separator.length():multilineSeparator.length()));
						if (d == div) {
							values.put(name, value);
							name = value = null;
						}
					} else if (name == null)
						throw new RuntimeException("Error at line: " + (i+1) + " value: " + line);
					else
						value += line + lineSeparator;
				}
			} 
		}
		if (name != null)
			values.put(name, value);
	}

}
