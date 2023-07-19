package ru.otus.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.ApplicationRunner;

@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args) {
        ApplicationRunner.run();
    }
}
