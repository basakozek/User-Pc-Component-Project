package org.basak.controller;

import java.util.Scanner;

public abstract class BaseController implements AutoCloseable {
    private final Scanner scanner;

    public BaseController() {
        scanner = new Scanner(System.in);
    }

    protected int readInt(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }

    protected Long readLong(String message) {
        System.out.print(message);
        return Long.parseLong(scanner.nextLine());
    }

    protected String readString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    protected void pressEnterToContinue() {
        System.out.println("\nDevam etmek için ENTER tuşuna basınız...");
        scanner.nextLine();
    }
    @Override
    public void close() {
        if(scanner != null) {
            scanner.close();
        }
    }
}
