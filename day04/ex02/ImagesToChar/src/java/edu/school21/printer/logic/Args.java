package edu.school21.printer.logic;

import java.io.IOException;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")

public class Args {

    @Parameter(names = {"--white"})
    private static String arg1;
    @Parameter(names = {"--black"})
    private static String arg2;

    public void run() throws IOException {
        try {
            PrintImage printImage = new PrintImage(arg1, arg2);
        } catch (IOException e) {
            System.err.println("Unable to open the file ./target/resources/it.bmp");
        }
    }
}
