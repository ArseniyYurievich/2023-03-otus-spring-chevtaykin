package ru.otus.spring.service;

public class ExamineServiceImpl implements ExamineService {

    private IOService ioService;

    private QuestionService questionService;

    private UserService userService;

    private int correctAnswersCountToPass;

    public ExamineServiceImpl() {
    }

    public ExamineServiceImpl(IOService ioService, QuestionService questionService, UserService userService,
                              int correctAnswersCountToPass) {
        this.ioService = ioService;
        this.questionService = questionService;
        this.userService = userService;
        this.correctAnswersCountToPass = correctAnswersCountToPass;
    }

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
