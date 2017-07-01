package me.lqlu.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

@Component
public class PropertiesUtil {

	private Map<File, Properties> propCache = new HashMap<>();

	public Properties loadProperties(String location) {
		return loadProperties(new File(location));
	}

	public Properties loadProperties(File file) {
		final File key = file;
		Properties prop = propCache.get(key);
		if (prop != null) {
			return prop;
		}
		Resource resource = new FileSystemResource(file);
		try {
			prop = PropertiesLoaderUtils.loadProperties(resource);
			propCache.put(key, prop);
			return prop;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new Properties();
	}

	public String readProperty(String key, String location) {
		Properties prop = loadProperties(location);
		return prop.getProperty(key);
	}

	public String readProperty(String key, File location) {
		Properties prop = loadProperties(location);
		return prop.getProperty(key);
	}

}
