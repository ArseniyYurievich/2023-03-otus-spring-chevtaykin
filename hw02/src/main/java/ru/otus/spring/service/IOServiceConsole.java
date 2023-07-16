package ru.otus.spring.service;

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

    @Override
    public String inputString() {
        return scanner.nextLine();
    }

    @Override
    public int inputInt() {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            printString("Bad integer was entered. Try again\n");
            scanner.nextLine();
            return inputInt();
        }
    }
}
