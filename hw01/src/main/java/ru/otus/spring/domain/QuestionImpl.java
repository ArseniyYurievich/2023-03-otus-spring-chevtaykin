package ru.otus.spring.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionImpl implements Question {

    private String questionPhrase;
    
    private List<Answer> answers = new ArrayList<>();

    public QuestionImpl(String questionString) {
        checkQuestionString(questionString);
        String[] splited = questionString.split(";");

        if (splited.length > 0) {
            questionPhrase = splited[0];
            for (int i = 1; i < splited.length; i++) {
                // первый ответ после вопроса - правильный
                answers.add(new AnswerImpl(i == 1, splited[i]));
            }
        }
    }

    // проверяем, что правильный ответ содержится в вариантах
    private boolean checkQuestionString(String questionString) {
        String[] splited = questionString.split(";");
        for (int i = 2; i < splited.length; i++) {
            if (splited[1].equals(splited[i])) {
                return true;
            }
        }
        throw new BadQuestionStringException();
    }

    @Override
    public void ask() {
        System.out.println(questionPhrase);
        for (int i = 1; i < answers.size(); i++) {
            System.out.println( i + ": " + answers.get(i).getAnswerPhrase());
        }
        System.out.println();
    }

    @Override
    public boolean parseAnswer(Answer answer) {
        return false;
    }
}
