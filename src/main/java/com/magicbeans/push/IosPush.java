package com.magicbeans.push;

import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.magicbeans.push.bean.Message;
import com.magicbeans.push.config.Config;

/**
 * Push - 苹果推送
 * @author Lucifer
 */
public class IosPush extends AbstractPush {

	public IosPush(Message message) {
		super(message);
	}

	public PushPayload build(Message message) {
		PushPayload payload = PushPayload.newBuilder()
				.setPlatform(Platform.ios())
				.setAudience(Audience.registrationId(new String[]{message.getDeviceId()}))
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(IosNotification.newBuilder()
								.setAlert(message.getTitle() == null ? message.getContent() : message.getTitle())
								.setBadge(1)
								.setSound(message.getSound())
								.addExtras(message.getExtend())
								.build())
						.build())
				.setMessage(cn.jpush.api.push.model.Message.newBuilder().setMsgContent(message.getContent())
						.addExtras(message.getExtend())
						.build())
				.setOptions(Options.newBuilder()
						.setApnsProduction(Config.isDev())
						.build())
				.build();

		return payload;
	}
}
