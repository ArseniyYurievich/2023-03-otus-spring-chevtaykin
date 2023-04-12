package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDao dao;

    private List<Question> questions;

    private int currentId;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
        questions = new ArrayList<>(dao.getQuestions());
    }

    @Override
    public List<Question> getQuestions() {
        if (questions == null || questions.isEmpty()) {
            questions = new ArrayList<>(dao.getQuestions());
        }
        return questions;
    }

    @Override
    public Question nextQuestion() {
        if (currentId < questions.size()) {
            return questions.get(currentId++);
        } else {
            return null;
        }
    }

    @Override
    public Question previousQuestion() {
        if (currentId > 0 && currentId < questions.size()) {
            return questions.get(currentId--);
        } else {
            return null;
        }
    }

    @Override
    public void askAllQuestions() {
        currentId = 0;
        Question question = nextQuestion();
        while (question != null) {
            question.ask();
            question = nextQuestion();
        }
    }


}
