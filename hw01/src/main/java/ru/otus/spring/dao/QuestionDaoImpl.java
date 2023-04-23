package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.AnswerImpl;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionServiceImpl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@RequiredArgsConstructor
public class QuestionDaoImpl implements QuestionDao {

    private final String questionsFileName;

    @Override
    public List<Question> getQuestions() {

        List<Question> questions = new ArrayList<>();
        Scanner scanner = new Scanner(Objects.requireNonNull(
                QuestionServiceImpl.class.getResourceAsStream("/" + questionsFileName)), StandardCharsets.UTF_8)
                    .useDelimiter("\\n");
        while (scanner.hasNext()) {
            try {
                questions.add(parseQuestionString(scanner.next()));
            } catch (BadQuestionStringException bqsq) {
                System.err.println("ERROR: Bad question string in file " + questionsFileName
                        + "; string № " + (questions.size() + 1) + "\n");
            }
        }
        return questions;
    }


    private Question parseQuestionString(String questionString) {
        checkQuestionString(questionString);
        String[] splited = questionString.split(";");

        List<Answer> answers = new ArrayList<>();
        if (splited.length > 0) {
            for (int i = 1; i < splited.length; i++) {
                // первый ответ после вопроса - правильный
                answers.add(new AnswerImpl(i == 1, splited[i]));
            }
        }
        return new Question(splited[0], answers);
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
}
