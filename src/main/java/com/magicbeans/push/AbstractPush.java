package com.magicbeans.push;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import com.magicbeans.push.bean.Message;
import com.magicbeans.push.config.Config;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Created by flyong86 on 2016/5/2.
 */
public abstract class AbstractPush implements IPush {

    private Logger logger = Logger.getLogger(getClass());

    private Message message;

    /** 最大尝试发送次数 */
    public static final Integer maxRetryTimes = 3;

    public AbstractPush(Message message) {
        this.message = message;
    }

    public void run() {
        PushPayload payload = build(message);

        String key = Config.getAppKey(message.getAppType());
        String secret = Config.getSecret(message.getAppType());

        if(StringUtils.isEmpty(key) || StringUtils.isEmpty(secret)){
            logger.error("无法获取key或secret");
            return;
        }

        @SuppressWarnings("deprecation")
        JPushClient jpushClient = new JPushClient(secret, key, maxRetryTimes);

        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got push result - " + result);
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            logger.info("Error response from JPush server. Should review and fix it. ");
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
        }
    }
}
