package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.BadQuestionStringException;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionServiceImpl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:spring.properties")
public class QuestionDaoImpl implements QuestionDao {

    @Value("${questions.fileName}")
    private String questionsFileName;

    private final QuestionParserImpl questionParser;

    @Override
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        Scanner scanner = new Scanner(Objects.requireNonNull(
                QuestionServiceImpl.class.getResourceAsStream("/" + questionsFileName)), StandardCharsets.UTF_8)
                    .useDelimiter("\\n");
        while (scanner.hasNext()) {
            try {
                questions.add(questionParser.parseQuestionString(scanner.next()));
            } catch (BadQuestionStringException bqsq) {
                System.err.println("ERROR: Bad question string in file " + questionsFileName
                        + "; string № " + (questions.size() + 1) + "\n");
            }
        }
        return questions;
    }
}
