import java.lang.System.*;
import java.io.IOException;

public class Main {
    public static void paramError() {
        System.err.println("use: java Program [path_to_image]");
        System.exit(-1);
    }

    public static void main(String[] args) {

        if (1 != args.length) {
            paramError();
        }

        try {
            PrintImage printImage = new PrintImage(args[0]);
            char [][] arrayBMP = printImage.getArrayStrings();
            for (int y = 0; y < arrayBMP.length; y++) {
                for (int x = 0; x < arrayBMP[y].length; x++) {
                    System.out.print(arrayBMP[y][x]);
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Unable to open the file: " + args[0]);
        }
    }
}
