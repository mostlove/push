package com.magicbeans.push.config;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Push - 基础配置
 * @author Lucifer
 */
public class Config {

	private static Logger logger = Logger.getLogger(Config.class);
	/** push.properties文件路径 */
	public static final String PROPERTIES_PATH = "driver_push.properties";

	private static Map<String, String> keys = new HashMap<String, String>();
	private static Map<String, String> secrets = new HashMap<String, String>();
	private static boolean isDev;

	static {
		init();
	}

	private Config() {
		// 私有化构造
	}

	static void init() {
		try {
			Properties properties = new Properties();

			// 获取config.properties配置文件
			InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH);
			properties.load(inputStream);
			Enumeration<Object> enumerations = properties.keys();

			while (enumerations.hasMoreElements()) {
				// 拿到配置文件中类型名称
				Object object = enumerations.nextElement();

				String key = object.toString();
				String value = properties.getProperty(key);
				String type = key.split("\\.")[0];

				// 是否开发模式
				if (key.equals("isDev")) {
					isDev = Boolean.valueOf(value);
				} else if (key.contains("key")) {
					keys.put(type, value);
				} else if (key.contains("secret")) {
					secrets.put(type, value);
				}
			}
		} catch (Exception e) {
			logger.error("初始化推送配置失败!", e);
		}
	}

	/**
	 * 获取AppKey
	 * @param appType App类型
	 * @return appKey
     */
	public static String getAppKey(String appType) {
		return keys.get(appType);
	}

	/**
	 * 获取App Secret
	 * @param appType App类型
	 * @return App Secret
     */
	public static String getSecret(String appType) {
		return secrets.get(appType);
	}

	public static boolean isDev() {
		return isDev;
	}
}
