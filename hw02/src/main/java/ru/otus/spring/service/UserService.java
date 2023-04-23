package ru.otus.spring.service;

import ru.otus.spring.domain.User;

public interface UserService {

    void setCurrentUser(User user);

    User getCurrentUser();

    void setCorrectAnswersCount(int i);

    int getCorrectAnswersCount();

    void authoriseUser();

    void increaseCorrectAnswersCount();

}
