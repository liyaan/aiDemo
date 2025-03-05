package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;

@Slf4j
@Service
public class DeepSeekServiceImpl implements DeepSeekService{

    private final ChatClient chatClient;
    private final String imageUrl = "请求地址";
    private final String apiKey = "您自己kay";
    private final RestTemplate restTemplate = new RestTemplate();
    public DeepSeekServiceImpl(ChatClient.Builder chatClientBuilder) {
        // 构造方法注入 ChatClient.Builder，用于构建 ChatClient 实例
        this.chatClient = chatClientBuilder.build();
    }

    /**
     * 文本模型
     *
     * @param message 消息内容
     * @return AI答案
     */
    @Override
    public String completion(String message) {
        // 调用 ChatClient 的 prompt 方法生成响应
        // 1. new Prompt(message): 创建一个包含用户输入消息的 Prompt 对象
        // 2. call(): 调用ChatClient与AI模型交互以获取响应
        // 3. content(): 获取响应的内容部分
        return chatClient.prompt(new Prompt(message))
                .call()
                .content();
    }

    @Override
    public String completionImage(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        String requestJson = "{\"prompt\":\"" + message + "\",\"model\": \"对应的模型\",\"image_size\":\"720x1280\",\"image_size\":\"720x1280\",\"batch_size\":1,\"seed\":4999999999,\"num_inference_steps\":20,\"guidance_scale\":7.5}";
        HttpEntity <String> request = new HttpEntity< >(requestJson, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(imageUrl, request, String.class);
        return response.getBody();
    }
}
