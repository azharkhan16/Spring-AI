package com.spring.ai.firstproject.service;

import com.spring.ai.firstproject.model.Tutorial;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatClient chatClient;

    public ChatServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient= chatClientBuilder.build();
    }


    @Override
    public String chat(String query) {

        String prompt = "Tell me about %s".formatted(query);

        return chatClient.prompt()
                .user(prompt)
                .system(" You are an expert assistant, Answer any question accurately and clearly.")
                .call()
                .content();
    }


    @Override
    public String metaData(String query) {

        Prompt prompt1 = new Prompt(query);
        var metaData = chatClient
                .prompt(prompt1)
                .call()
                .chatResponse()
                .getMetadata();

        System.out.println(metaData);
        return "";
    }


    @Override
    public String content(String query) {

        Prompt prompt1 = new Prompt(query);
        var content = chatClient
                .prompt(prompt1)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();

        return content;
    }


    @Override
    public Tutorial tutorial(String query) {

        Prompt prompt1 = new Prompt(query);
        Tutorial tut = chatClient
                    .prompt(prompt1)
                    .call()
                    .entity(Tutorial.class);

        return tut;
    }

    @Override
    public List<Tutorial> listOfTutorial(String query) {

        Prompt prompt1 = new Prompt(query);
        List<Tutorial> tut = chatClient
                .prompt(prompt1)
                .call()
                .entity(new ParameterizedTypeReference<List<Tutorial>>() {
                });

        return tut;
    }
}
