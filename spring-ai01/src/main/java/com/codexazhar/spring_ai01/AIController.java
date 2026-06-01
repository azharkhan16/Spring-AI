package com.codexazhar.spring_ai01;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AIController {

    private final ChatClient chatClient;

    public AIController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/prompt")
    public String generation(@RequestParam String message) {

        String response = this.chatClient.prompt()
                .user(message)
                .call()
                .content();
        //        System.out.println(response);
        return response;
    }

    @GetMapping("/sports-personality")
    public String findPopularSportsPerson(@RequestParam String sports) {

        String message = """
                List of 5 most popular personalities in {sports}
                along with their career achievements.

                Show the details in proper readable format.
                """;
        PromptTemplate template = new PromptTemplate(message);
        String prompt = template.render(Map.of(
                "sports", sports
        ));

        String response = this.chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        return response;
    }
}