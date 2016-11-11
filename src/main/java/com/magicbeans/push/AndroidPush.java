package com.magicbeans.push;

import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.magicbeans.push.bean.Message;
import com.magicbeans.push.bean.NotificationType;

/**
 * Push - 安卓推送
 * @author Lucifer
 */
public class AndroidPush extends AbstractPush implements Runnable {

	public AndroidPush(Message message) {
		super(message);
	}
	
	/**
	 * 推送
	 * @param message 消息体
	 */
	public PushPayload build(Message message) {

		PushPayload.Builder builder = PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.registrationId(message.getDeviceId()));

		if (message.getNotificationType() == NotificationType.notification || message.getNotificationType() == NotificationType.notification_passthrough) {
			// 通知
			builder.setNotification(Notification.android(message.getContent(), message.getTitle(), message.getExtend()));
		}
		if (message.getNotificationType() == NotificationType.passthrough || message.getNotificationType() == NotificationType.notification_passthrough) {
			// 透传
			builder.setMessage(cn.jpush.api.push.model.Message.newBuilder()
					.setMsgContent(message.getContent())
					.setTitle(message.getTitle())
					.addExtras(message.getExtend())
					.build());
		}

		PushPayload payload = builder.build();
		

		return payload;
	}
}
