package com.gnahz.ai.server.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnahz.SparkClient;
import com.gnahz.ai.dao.ChatGpt;
import com.gnahz.ai.server.ChatGptServer;
import com.gnahz.model.SparkMessage;
import com.gnahz.model.SparkSyncChatResponse;
import com.gnahz.model.request.SparkRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 张伟洁
 * Date:2024-05-24-21:25
 * @create 忆项目(小白)
 */
@Service
public class ChatGptServerImpl implements ChatGptServer {



    /**
     * 客户端实例，线程安全
     */
    SparkClient sparkClient = new SparkClient();
   // ChatGpt chatGpt = new ChatGpt();

    // 设置认证信息
    {
        sparkClient.appid = "6a4cdab8";
        sparkClient.apiKey = "ed22d4203115073c57c9aad373b5fff0";
        sparkClient.apiSecret = "OWNjNTA3NDYzMTY4YTRkY2IzNDRlNzkz";
    }

    @Override
    public String QueryAi(String message) {
        // 消息列表，可以在此列表添加历史对话记录
        List<SparkMessage> messages = new ArrayList<>();
        messages.add(SparkMessage.userContent(message));


        // 构造请求
        SparkRequest sparkRequest = SparkRequest.builder()
                // 消息列表
                .messages(messages)
                // 模型回答的tokens的最大长度,非必传，默认为2048。
                // V1.5取值为[1,4096]
                // V2.0取值为[1,8192]
                // V3.0取值为[1,8192]
                .maxTokens(2048)
                // 核采样阈值。用于决定结果随机性,取值越高随机性越强即相同的问题得到的不同答案的可能性越高 非必传,取值为[0,1],默认为0.5
                .temperature(0.2)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
       /* try {
            System.out.println("提问：" + objectMapper.writeValueAsString(messages));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/

        // 同步调用
        SparkSyncChatResponse chatResponse = sparkClient.chatSync(sparkRequest);
        /*SparkTextUsage textUsage = chatResponse.getTextUsage();
        try {


            System.out.println("\n21回答：" + chatResponse.getContent());
            System.out.println("\n提问tokens：" + textUsage.getPromptTokens()
                    + "，回答tokens：" + textUsage.getCompletionTokens()
                    + "，总消耗tokens：" + textUsage.getTotalTokens());
        } catch (SparkException e) {
            System.out.println("发生异常了：" + e.getMessage());
        }*/
        return chatResponse.getContent();
    }
}
