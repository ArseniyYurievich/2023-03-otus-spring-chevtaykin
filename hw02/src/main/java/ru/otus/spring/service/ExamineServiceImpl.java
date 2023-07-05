package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@RequiredArgsConstructor
@PropertySource("classpath:spring.properties")
public class ExamineServiceImpl implements ExamineService {

    @NotNull
    private IOService ioService;

    @NotNull
    private QuestionService questionService;

    @NotNull
    private UserService userService;

    @Value("${answers.correctCountToPass}")
    private int correctAnswersCountToPass;

    @Override
    public void execute() {
        userService.authoriseUser();
        while (questionService.hasNext()) {
            boolean correctAnswer = questionService.ask(questionService.nextQuestion());
            if (correctAnswer) {
                userService.increaseCorrectAnswersCount();
            }
        }
        ioService.printString("Congratulations! (maybe)\n");
        boolean passed = userService.getCorrectAnswersCount() >= correctAnswersCountToPass;
        ioService.printString("User " + userService.getCurrentUser().toString() +
                (passed ? " successfully passed exam!" : " unfortunely failed exam :("));
    }

}
