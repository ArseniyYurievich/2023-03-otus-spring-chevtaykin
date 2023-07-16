package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.spring.Main;
import ru.otus.spring.domain.User;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {Main.class})
class ExamineServiceImplTest {

    @Mock
    private IOService ioService;

    @Mock
    private QuestionService questionService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ExamineServiceImpl examineService;

    @Test
    @DisplayName("должен провести авторизацию пользователя, задать все вопросы, обработать ответы")
    void execute() {
        given(userService.getCurrentUser()).willReturn(new User());
        examineService.execute();
        verify(ioService, times(1)).printString("Congratulations! (maybe)\n");
    }
}