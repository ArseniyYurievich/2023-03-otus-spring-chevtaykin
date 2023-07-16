package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

public interface QuestionParser {

    Question parseQuestionString(String questionString);

    boolean checkQuestionString(String questionString);
}
