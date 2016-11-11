package com.magicbeans.push;

import cn.jpush.api.push.model.PushPayload;
import com.magicbeans.push.bean.Message;

/**
 * Created by flyong86 on 2016/5/2.
 */
public interface IPush extends Runnable {

    PushPayload build(Message message);
}
