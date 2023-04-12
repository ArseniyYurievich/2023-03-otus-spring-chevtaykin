package ru.otus.spring.service;


import org.junit.jupiter.api.Test;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.QuestionImpl;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;


class QuestionServiceImplTest {

    private List<Question> questions;
    
    @Test
    void getQuestions() {
        questions = Arrays.asList(
                new QuestionImpl("The ultimate answer of life, the universe and everything?;42;2;12;85;06;42;"),
                new QuestionImpl("Who killed Lora Palmer?;Leland Palmer;Leo Johnson;BOB;Leland Palmer;Jerry Horne;"),
                new QuestionImpl("How much is the fish?;100;0;50;69;100;"),
                new QuestionImpl("How many paws does the cat have?;4;0;1;2;3;4;"),
                new QuestionImpl("How many lives does the cat have?;9;1;2;9;"));

        QuestionDao questionDao = mock(QuestionDao.class);
        given(questionDao.getQuestions()).willReturn(questions);
        QuestionService questionService = new QuestionServiceImpl(questionDao);
        assertThat(questionService.getQuestions()).isEqualTo(questions);
    }

}