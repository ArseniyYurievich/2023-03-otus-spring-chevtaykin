package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerImpl implements Answer {

    private boolean isCorrect;

    private String answerPhrase;

}
