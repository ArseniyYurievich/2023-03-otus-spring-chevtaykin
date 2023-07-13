package ru.otus.spring.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.service.ExamineService;
import ru.otus.spring.service.ExamineServiceImpl;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.IOServiceConsole;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.QuestionServiceImpl;
import ru.otus.spring.service.UserService;
import ru.otus.spring.service.UserServiceImpl;

@Configuration
@PropertySource("classpath:spring.properties")
public class ServiceConfig {

    @Value("${answers.correctCountToPass}")
    private int correctAnswersCountToPass;

    @Bean
    public IOService ioService() {
        return new IOServiceConsole();
    }

    @Bean
    public UserService userService(IOService ioService) {
        return new UserServiceImpl(ioService);
    }

    @Bean
    public QuestionService questionService(QuestionDao questionDao, IOService ioService) {
        return new QuestionServiceImpl(questionDao, ioService);
    }

    @Bean
    public ExamineService examineService(IOService ioService, QuestionService questionService,
                                         UserService userService) {
        return new ExamineServiceImpl(ioService, questionService, userService, correctAnswersCountToPass);
    }

}
