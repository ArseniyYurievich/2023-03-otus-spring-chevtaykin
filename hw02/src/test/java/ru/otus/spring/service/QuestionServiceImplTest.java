package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.spring.Main;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {Main.class})
class QuestionServiceImplTest {

    @Mock
    private QuestionDao questionDao;

    @Mock
    private IOService ioService;

    @InjectMocks
    private QuestionServiceImpl questionService;

    private List<Question> questions = new ArrayList<>();

    private List<Answer> answers = new ArrayList<>(Arrays.asList(
            new Answer("correct answer", Boolean.TRUE),
            new Answer("test answer phrase true", Boolean.TRUE),
            new Answer("test answer phrase false", Boolean.FALSE))
    );

    @BeforeEach
    void setUp() {
        questions.clear();
        questions.add(new Question("test question phrase", answers));
        questions.add(new Question("second test question", answers));
        given(questionDao.getQuestions()).willReturn(questions);
    }

    @Test
    @DisplayName("должен возвращать список вопросов")
    void shouldReturnQuestionsList() {
        Optional<List<Question>> questionsOptional = Optional.ofNullable(questionService.getQuestions());
        assertThat(questionsOptional).isNotEmpty().hasValue(questions);
    }

    @Test
    @DisplayName("должен возвращать следующий вопрос из списка вопросов")
    void shouldReturnNextQuestionFromList() {
        questionService.getQuestions();
        Question question = questions.get(0);
        Question questionResult = questionService.nextQuestion();
        assertThat(questionResult).isNotNull().isEqualTo(question);
        question = questions.get(1);
        questionResult = questionService.nextQuestion();
        assertThat(questionResult).isNotNull().isEqualTo(question);
    }

    @Test
    @DisplayName("должен ответить, есть ли ещё незаданные вопросы")
    void shouldCheckIfThereAnyQuestionsLeft() {
        questionService.getQuestions();
        Boolean result = questionService.hasNext();
        assertThat(result).isEqualTo(Boolean.TRUE);

        questionService.ask(questionService.nextQuestion());
        result = questionService.hasNext();
        assertThat(result).isEqualTo(Boolean.TRUE);

        questionService.ask(questionService.nextQuestion());
        result = questionService.hasNext();
        assertThat(result).isEqualTo(Boolean.FALSE);
    }

    @Test
    @DisplayName("должен задать вопрос, вывести на экран")
    void shouldAskQuestionByShowingItOnTheScreen() {
        questionService.getQuestions();
        Question question = questions.get(0);
        questionService.ask(question);
        verify(ioService, times(1)).printString("Question:\n");
        verify(ioService, times(1)).printString(question.getQuestionPhrase() + "\n");
        verify(ioService, times(1)).printString("Answers:\n");
        verify(ioService, times(1)).printInt(1);
        verify(ioService, times(1)).printString(": " + question.getAnswers().get(1).getAnswerPhrase() + "\n");
    }

}