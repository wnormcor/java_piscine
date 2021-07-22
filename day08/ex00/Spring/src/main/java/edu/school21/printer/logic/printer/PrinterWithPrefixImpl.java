package edu.school21.printer.logic.printer;

import edu.school21.printer.logic.renderer.Renderer;


public class PrinterWithPrefixImpl implements Printer {
    Renderer renderer;
    String   prefix = "";

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String message) {

        if (prefix != "") {
            prefix += " ";
            renderer.outResult(prefix += message);
        }
        else {
            renderer.outResult(message);
        }
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
