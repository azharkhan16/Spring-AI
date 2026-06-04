package com.spring.ai.firstproject.service;

import com.spring.ai.firstproject.model.Tutorial;

import java.util.List;

public interface ChatService {

    String chat(String query);

    String metaData(String query);

    String content(String query);

    Tutorial tutorial(String query);

    List<Tutorial> listOfTutorial(String query);
}
