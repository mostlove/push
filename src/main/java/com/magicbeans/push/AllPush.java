package com.magicbeans.push;

import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.magicbeans.push.bean.DeviceType;
import com.magicbeans.push.bean.Message;
import com.magicbeans.push.bean.NotificationType;
import com.magicbeans.push.config.Config;

/**
 * Created by Administrator on 2016/10/9 0009.
 *  广播推送所有
 */
public class AllPush extends AbstractPush implements IPush {


    public AllPush(Message message) {
        super(message);
    }

    public PushPayload build(Message message) {


        PushPayload.Builder builder = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all());

        if (message.getNotificationType() == NotificationType.notification || message.getNotificationType() == NotificationType.notification_passthrough) {
            // 通知
            if(message.getDeviceType() == DeviceType.all){
                builder.setOptions(Options.newBuilder()
                        .setApnsProduction(Config.isDev())
                        .build()).setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder().setAlert(message.getContent()).setTitle(message.getTitle()).addExtras(message.getExtend()).build())
                        .addPlatformNotification(IosNotification.newBuilder().setAlert(message.getContent()).addExtras(message.getExtend()).build())
                        .build()).build();
//                builder.setNotification(Notification.alert(message.getTitle()));
            }else{
                builder.setNotification(Notification.android(message.getContent(), message.getTitle(), message.getExtend()));
            }


//            builder.setNotification(Notification.android(message.getContent(), message.getTitle(), message.getExtend()));
//            builder.setNotification(Notification.ios(message.getContent(), message.getExtend()));
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
