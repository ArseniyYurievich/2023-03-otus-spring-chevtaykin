package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDao dao;

    private List<Question> questions;

    private int currentId;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
        questions = dao.getQuestions();
    }

    @Override
    public List<Question> getQuestions() {
        if (questions == null || questions.isEmpty()) {
            questions = dao.getQuestions();
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
    public void askAllQuestions() {
        currentId = 0;
        Question question = nextQuestion();
        while (question != null) {
            ask(question);
            question = nextQuestion();
        }
    }

    @Override
    public void ask(Question question) {
        System.out.println(question.getQuestionPhrase());
        for (int i = 1; i < question.getAnswers().size(); i++) {
            System.out.println( i + ": " + question.getAnswers().get(i).getAnswerPhrase());
        }
        System.out.println();
    }

}
