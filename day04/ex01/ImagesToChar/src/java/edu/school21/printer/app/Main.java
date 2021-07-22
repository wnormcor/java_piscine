package edu.school21.printer.app;
import  edu.school21.printer.logic.PrintImage;

import java.lang.System.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            PrintImage printImage = new PrintImage();
            char [][] arrayBMP = printImage.getArrayStrings();
            for (int y = 0; y < arrayBMP.length; y++) {
                for (int x = 0; x < arrayBMP[y].length; x++) {
                    System.out.print(arrayBMP[y][x]);
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Unable to open the file");
        }
    }
}
