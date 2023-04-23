package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.dao.QuestionDaoImpl;

@Configuration
@PropertySource("classpath:spring.properties")
public class DaoConfig {

    @Value("${questions.fileName}")
    private String questionsFileName;

    @Bean
    public QuestionDao questionDao() {
        return new QuestionDaoImpl(questionsFileName);
    }

}
