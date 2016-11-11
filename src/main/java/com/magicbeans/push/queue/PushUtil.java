package com.magicbeans.push.queue;

import com.magicbeans.push.AllPush;
import com.magicbeans.push.AndroidPush;
import com.magicbeans.push.IPush;
import com.magicbeans.push.IosPush;
import com.magicbeans.push.bean.DeviceType;
import com.magicbeans.push.bean.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by flyong86 on 2016/5/2.
 */
public class PushUtil {
    private final static ExecutorService threadPool = Executors.newScheduledThreadPool(5);

    private PushUtil() {
        // 私有化构造, 无法创建实例
    }

    public final static void pushMessage(Message message) {
        if (null == message) {
            throw new RuntimeException("Message can't be null!");
        }

        IPush push = null;
        // 判断推送类型
        if(message.getDeviceType() == DeviceType.android) {
            push = new AndroidPush(message);
        }else if(message.getDeviceType() == DeviceType.ios){
            push = new IosPush(message);
        } else {
            push = new AllPush(message);
        }

        threadPool.execute(push);
    }
}
