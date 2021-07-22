package edu.school21.printer.logic;

import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

public class PrintImage {

    File file;
    BufferedImage source;
    String  white, black;

    public PrintImage(String white, String black) throws IOException {
		
        this.white = white;
        this.black = black;
        file = new File("./target/resources/it.bmp");
        source = ImageIO.read(file);

		Ansi.BColor.valueOf(white);
        Ansi.BColor.valueOf(black);
        for (int x = 0; x < source.getWidth(); x++) {
            for (int y = 0; y < source.getHeight(); y++) {
                ColoredPrinter cp = new ColoredPrinter();
                if (source.getRGB(y, x) == -1) {
                    cp.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(white));
                } else {
                    cp.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(black));
                }
            }
            System.out.println();
        }
    }
}