package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.Main;
import ru.otus.spring.config.TestContextConfig;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.AnswerImpl;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Main.class, TestContextConfig.class})
class ExamineServiceImplTest {

    @Autowired
    private ExamineService examineService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private IOService ioService;

    private List<Question> questions = new ArrayList<>();

    private List<Answer> answers = new ArrayList<>(Arrays.asList(
            new AnswerImpl("correct answer", Boolean.TRUE),
            new AnswerImpl("test answer phrase true", Boolean.TRUE),
            new AnswerImpl("test answer phrase false", Boolean.FALSE))
    );

    @BeforeEach
    void setUp() {
        questions.clear();
        questions.add(new Question("test question phrase", answers));
        questions.add(new Question("second test question", answers));
        given(userService.getCurrentUser()).willReturn(new User());
        given(questionService.getQuestions()).willReturn(questions);
    }

    @Test
    @DisplayName("должен провести авторизацию пользователя, задать все вопросы, обработать ответы")
    void execute() {
        examineService.execute();
        verify(ioService, times(1)).printString("Congratulations! (maybe)\n");
    }
}