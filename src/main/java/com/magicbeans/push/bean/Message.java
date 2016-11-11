package com.magicbeans.push.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Push - 消息封装
 * @author Lucifer
 */
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8394762523749955377L;

	/** 默认声音. */
	private static final String DEFAULT_SOUND = "default";

	/** 推送ID,一般为用户设备ID */
	private String deviceId;

	/** 消息标题 */
	private String title;
	
	/** 消息内容 */
	private String content;
	
	/** 发送时间 */
	private String time;

	/** 音频(iOS使用) . */
	private String sound = DEFAULT_SOUND;
	/** 设备类型. */
	private DeviceType deviceType;

	/** App类型. */
	private String appType;

	/** 通知类型. */
	private NotificationType notificationType = NotificationType.notification;
	
	/** 扩展参数 */
	private Map<String, String> extend = new HashMap<String, String>();

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Map<String, String> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, String> extend) {
		this.extend = extend;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}
}