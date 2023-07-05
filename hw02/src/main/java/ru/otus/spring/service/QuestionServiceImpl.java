package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDao dao;

    private IOService ioService;

    private List<Question> questions;

    private int currentId;

    public QuestionServiceImpl(QuestionDao dao, IOService ioService) {
        this.dao = dao;
        this.ioService = ioService;
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
    public boolean hasNext() {
        return currentId < questions.size();
    }

    @Override
    public boolean ask(Question question) {
        ioService.printString("Question:\n");
        ioService.printString(question.getQuestionPhrase() + "\n");
        ioService.printString("Answers:\n");
        List<Answer> answers = question.getAnswers();
        for (int i = 1; i < answers.size() - 1; i++) {
            ioService.printInt(i);
            ioService.printString(": " + answers.get(i).getAnswerPhrase() + "\n");
        }
        ioService.printString("\nEnter answer number:\n");
        int inputAnswer = ioService.inputInt();
        return question.getAnswers().get(inputAnswer).isCorrect();
    }

}
