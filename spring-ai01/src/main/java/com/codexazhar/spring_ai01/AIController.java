package com.codexazhar.spring_ai01;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
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

    @GetMapping("/sports-v1")
    public String findPopularSportsPersonOne(@RequestParam String sports) {

        String message = """
                List of 5 most popular personalities in {sports}
                along with their career achievements.
                Show the details in proper readable format.""";

        PromptTemplate template = new PromptTemplate(message);
        String prompt = template.render(Map.of("sports", sports));

        String response = this.chatClient.prompt()
                .user(prompt)
                .call()
                .content();
        return response;
    }


    @GetMapping("/sports-v2")
    public String findPopularSportsPersonTwo(@RequestParam String sports) {

        var userMessage = new UserMessage(
                String.format("""
                        List of 5 most popular personalities in %s
                        along with their Career Achievements.
                        Show the details in proper Readable format.""", sports)
        );
        Prompt prompt = new Prompt(userMessage);

        return this.chatClient.prompt(prompt)
                .call()
                .content();
    }
}