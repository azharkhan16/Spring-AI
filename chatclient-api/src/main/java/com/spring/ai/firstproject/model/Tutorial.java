package com.spring.ai.firstproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tutorial {

    private String title;
    private String content;
    private String createdYear;
}
