package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExamineServiceImpl implements ExamineService {

    private final IOService ioService;

    private final QuestionService questionService;

    private final UserService userService;

    private final int correctAnswersCountToPass;

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
