package dev.sgp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Outils {
	
	
	public static String getApplicationPropertiesParam(String param, String filePath){
		Properties prop = new Properties();
		InputStream input = null;
		String value = null;

		try {
			input = new FileInputStream(filePath);
			prop.load(input);
		
			value = prop.getProperty(param);
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return value;
	}
}
