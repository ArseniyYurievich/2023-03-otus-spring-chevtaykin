package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.BadQuestionStringException;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.QuestionImpl;
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
                questions.add(new QuestionImpl(scanner.next()));
            } catch (BadQuestionStringException bqsq) {
                System.err.println("ERROR: Bad question string in file " + questionsFileName
                        + "; string № " + (questions.size() + 1) + "\n");
            }
        }
        return questions;
    }
}
