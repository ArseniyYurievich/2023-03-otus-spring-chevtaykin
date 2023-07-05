package ru.otus.spring.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.spring.Main;

public class ApplicationRunner {

    public static void run() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        ExamineService examineService = context.getBean(ExamineService.class);
        examineService.execute();
    }

}
