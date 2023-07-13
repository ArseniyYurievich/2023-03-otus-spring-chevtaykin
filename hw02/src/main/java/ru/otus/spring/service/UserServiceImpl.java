package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.User;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private User user;

    private int correctAnswersCount;

    private final IOService ioService;

    @Override
    public void setCurrentUser(User user) {
        this.user = user;
    }

    @Override
    public User getCurrentUser() {
        return user;
    }

    @Override
    public void setCorrectAnswersCount(int i) {
        correctAnswersCount = i;
    }

    @Override
    public int getCorrectAnswersCount() {
        return correctAnswersCount;
    }

    @Override
    public void authoriseUser() {
        User user = new User();
        ioService.printString("Enter first name:\n");
        String firstName = ioService.inputString();
        user.setFirstName(firstName);
        ioService.printString("Enter last name:\n");
        String lastName = ioService.inputString();
        user.setLastName(lastName);
        this.user = user;
    }

    @Override
    public void increaseCorrectAnswersCount() {
        correctAnswersCount++;
    }

}
