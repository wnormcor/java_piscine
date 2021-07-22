package edu.school21.printer.logic.printer;

import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import edu.school21.printer.logic.renderer.Renderer;

public class PrinterWithDateTimeImpl implements Printer {
    private Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String message) {

        renderer.outResult(message + LocalDateTime.now());

//        LocalDateTime currentDateTime = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
//        String formattedDateTime = currentDateTime.format(formatter);
//
//        formattedDateTime += " ";
//        formattedDateTime += message;
//
//        renderer.sendsMessages(formattedDateTime);
    }
}
