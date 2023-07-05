package ru.otus.spring.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.UserService;

@Configuration
public class TestContextConfig {

    @Bean
    @Primary
    QuestionDao questionDao() {
        return Mockito.mock(QuestionDao.class);
    }

    @Bean
    @Primary
    IOService ioService() {
        return Mockito.mock(IOService.class);
    }

    @Bean
    @Primary
    UserService userService() {
        return Mockito.mock(UserService.class);
    }
}
