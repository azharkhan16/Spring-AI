package com.spring.ai.firstproject.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ChatController {

    private final ChatClient openAiChatModel;

    private final ChatClient ollamaChatModel;

    public ChatController(@Qualifier("openAiChatClient") ChatClient openAiChatModel,
                          @Qualifier("ollamaChatClient") ChatClient ollamaChatModel) {
        this.openAiChatModel = openAiChatModel;
        this.ollamaChatModel = ollamaChatModel;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "msg", required = true) String msg) {

        var chatResponse = openAiChatModel
                .prompt(msg)
                .call()
                .content();
        return ResponseEntity.ok(chatResponse);
    }
}
