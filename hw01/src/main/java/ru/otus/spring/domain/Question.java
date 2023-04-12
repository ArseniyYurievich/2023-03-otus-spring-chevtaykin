package ru.otus.spring.domain;

public interface Question {

    void ask();

    boolean parseAnswer(Answer answer);

}
