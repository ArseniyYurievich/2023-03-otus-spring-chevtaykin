package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerImpl implements Answer {

    private String answerPhrase;

    private boolean isCorrect;
}
