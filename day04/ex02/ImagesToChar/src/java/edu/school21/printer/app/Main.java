package edu.school21.printer.app;
import com.beust.jcommander.ParameterException;
import edu.school21.printer.logic.Args;

import java.io.IOException;
import com.beust.jcommander.JCommander;

public class Main {

    public static void main(String[] args) throws IOException {

        if (2 != args.length) {
            System.err.println("use: Program --white=color --black=color");
            System.exit(1);
        }

        Args arguments = new Args();

        try {
            JCommander.newBuilder().addObject(arguments).build().parse(args);
            arguments.run();
        } catch (IOException e) {
            System.err.println("Exception IO");
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid color in param");
        } catch (ParameterException e) {
            System.err.println("use: Program --white=color --black=color");
        }
    }
}