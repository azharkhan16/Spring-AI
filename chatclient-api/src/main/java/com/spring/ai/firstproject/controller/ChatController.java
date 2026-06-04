package com.spring.ai.firstproject.controller;

import com.spring.ai.firstproject.model.Tutorial;
import com.spring.ai.firstproject.service.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ChatController {

    private final ChatServiceImpl chatServiceImpl;

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q") String q) {

        return ResponseEntity.ok(chatServiceImpl.chat(q));
    }

    @GetMapping("/metadata")
    public ResponseEntity<String> metaData(@RequestParam(value = "q") String q) {

        return ResponseEntity.ok(chatServiceImpl.metaData(q));
    }

    @GetMapping("/content")
    public ResponseEntity<String> content(@RequestParam(value = "q") String q) {

        return ResponseEntity.ok(chatServiceImpl.content(q));
    }

    @GetMapping("/tutorial")
    public ResponseEntity<Tutorial> tutorial(@RequestParam(value = "q") String q) {

        return ResponseEntity.ok(chatServiceImpl.tutorial(q));
    }


    @GetMapping("/list-of-tutorial")
    public ResponseEntity<List<Tutorial>> liftOfTutorial(@RequestParam(value = "q") String q) {

        return ResponseEntity.ok(chatServiceImpl.listOfTutorial(q));
    }
}
