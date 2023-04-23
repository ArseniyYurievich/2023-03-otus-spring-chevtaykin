package ru.otus.spring.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @Mock
    private QuestionDao questionDao;

    @Test
    void getQuestions() {
        List<Question> questions = new ArrayList<>();
        given(questionDao.getQuestions()).willReturn(questions);
        QuestionService questionService = new QuestionServiceImpl(questionDao);
        assertThat(questionService.getQuestions()).isEqualTo(questions);
    }

}