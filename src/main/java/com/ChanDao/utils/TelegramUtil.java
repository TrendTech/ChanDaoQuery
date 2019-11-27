package com.ChanDao.utils;

import com.ChanDao.consts.Consts;
import com.ChanDao.enums.REQ_TYPE;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Author ajiang
 * Created 2019/11/24 15:37
 */
public class TelegramUtil {

    public static void sendMessageRequest(int chat_id, String message) throws IOException {
        final StringBuilder sendMessage = new StringBuilder(
                "https://api.telegram.org/bot" + Consts.BOT_API_KEY + "/" + REQ_TYPE.sendMessage.name());
        sendMessage.append("?chat_id=").append(chat_id);
        sendMessage.append("&text=").append(URLEncoder.encode(message,"UTF-8"));
        HttpGet sendMessageRequest = new HttpGet(sendMessage.toString());
        CloseableHttpClient client = HttpClientBuilder.create().build();
        client.execute(sendMessageRequest);
    }

    public static void main(String[] args) throws IOException {
        TelegramUtil.sendMessageRequest(-373527453,"here is my text.\n and this is a new line \n another new line");
    }
}
