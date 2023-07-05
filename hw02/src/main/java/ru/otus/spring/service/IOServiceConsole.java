package ru.otus.spring.service;

import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Scanner;

public class IOServiceConsole implements IOService {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void printString(String s) {
        System.out.print(s);
    }

    @Override
    public void printInt(int i) {
        System.out.print(i);
    }

    @SneakyThrows
    @Override
    public String inputString() {
        if (!scanner.hasNext()) {
            throw new IOException("There's no input string to read.");
        }
        return scanner.nextLine();
    }

    @SneakyThrows
    @Override
    public int inputInt() {
        if (!scanner.hasNextInt()) {
            throw new IOException("There's no input integer to read.");
        }
        return scanner.nextInt();
    }
}
