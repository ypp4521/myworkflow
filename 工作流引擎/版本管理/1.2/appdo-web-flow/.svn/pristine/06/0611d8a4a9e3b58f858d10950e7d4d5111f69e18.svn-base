package org.zywx.appdo.utils;

import com.typesafe.config.ConfigFactory;
import com.typesafe.config.Config;

/**
 * 
 * @author zorro
 *
 */
public class PropertyTools {
	private static Config conf = ConfigFactory.load();

	public static String getPropertyByKey(String key) {
		return conf.getString(key).trim();
	}
}
