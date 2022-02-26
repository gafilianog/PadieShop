package com.gafilianog;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilities {

    public static Scanner scan = new Scanner(System.in);

    public static void narStyleCLS() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void banner() {
        System.out.print(
                " , __                            _               \n" +
                        "/|/  \\         |  o          () | |              \n" +
                        " |___/ __,   __|      _      /\\ | |     __    _  \n" +
                        " |    /  |  /  |  |  |/     /  \\|/ \\   /  \\_|/ \\_\n" +
                        " |    \\_/|_/\\_/|_/|_/|__/  /(__/|   |_/\\__/ |__/ \n" +
                        "                                           /|    \n" +
                        "                                           \\|    \n");
    }

    public static int checkIntInput() {
        int choice;

        try {
            choice = Utilities.scan.nextInt();
            Utilities.scan.nextLine();
        } catch (InputMismatchException e) {
            choice = -1;
            Utilities.scan.nextLine();
        }

        return choice;
    }

    public static void exit() {
        System.out.print("Terima kasih telah menggunakan Padie Shop!");
        System.exit(0);
    }
}
