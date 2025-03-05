package com.example.demo.controller;

public interface DeepSeekService {
    /**
     * 文本模型
     *
     * @param message 消息内容
     * @return AI答案
     */
    String completion(String message);

    String completionImage(String message);
}
