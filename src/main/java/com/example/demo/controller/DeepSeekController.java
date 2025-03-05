package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@CrossOrigin
@RequiredArgsConstructor
public class DeepSeekController {
    @Autowired
    DeepSeekService deepSeekService;

    /**
     * 对接文本模型
     *
     * @param message 消息内容
     * @return AI答案
     */
    @GetMapping("/chat")
    public String completion(@RequestParam(value = "message", defaultValue = "hello word") String message) {
        return deepSeekService.completion(message);
    }
    @GetMapping("/chatImage")
    public String completionImage(@RequestParam(value = "message", defaultValue = "hello word") String message) {
        return deepSeekService.completionImage(message);
    }
}
