package ru.otus.spring.dao;

import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.BadQuestionStringException;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionParser {

    public static Question parseQuestionString(String questionString) {
        checkQuestionString(questionString);
        String[] splited = questionString.split(";");

        List<Answer> answers = new ArrayList<>();
        if (splited.length > 0) {
            for (int i = 1; i < splited.length; i++) {
                // первый ответ после вопроса - правильный
                answers.add(new Answer(splited[i], splited[1].equals(splited[i])));
            }
        }
        return new Question(splited[0], answers);
    }

    public static boolean checkQuestionString(String questionString) {
        String[] splited = questionString.split(";");
        for (int i = 2; i < splited.length; i++) {
            if (splited[1].equals(splited[i])) {
                return true;
            }
        }
        throw new BadQuestionStringException();
    }
}
