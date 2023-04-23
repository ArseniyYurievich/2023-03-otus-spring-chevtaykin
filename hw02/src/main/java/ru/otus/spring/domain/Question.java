package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Question {

    private String questionPhrase;

    private List<Answer> answers;

}
