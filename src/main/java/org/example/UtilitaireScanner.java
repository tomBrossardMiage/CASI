package org.example;

import java.util.Scanner;

public class UtilitaireScanner {
    private static final Scanner scanner = new Scanner(System.in);

    public static Scanner getScanner() {
        return scanner;
    }

    public static void fermerScanner() {
        scanner.close();
    }
}

