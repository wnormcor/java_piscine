package edu.school21.printer.logic;

import java.io.IOException;
import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class PrintImage {
    File file;
    BufferedImage image;
    char [][] arrayStrings;

    public char[][] getArrayStrings() {
        return arrayStrings;
    }

    public PrintImage() throws IOException {
        file = new File("./target/resources/it.bmp");
        image = ImageIO.read(file);

        arrayStrings = new char[image.getHeight()][image.getWidth()];

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(y, x));
                int clr = image.getRGB(x, y);
                if (clr == color.WHITE.getRGB()) {
                    arrayStrings[y][x] = '.';
                } else if (clr == color.BLACK.getRGB()) {
                    arrayStrings[y][x] = '0';
                } else {
                    arrayStrings[y][x] = 'x';
                }
            }
        }
    }
}
