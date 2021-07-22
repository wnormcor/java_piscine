package edu.school21.printer.app;

import edu.school21.printer.logic.printer.Printer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import edu.school21.printer.logic.printer.*;
//import edu.school21.printer.logic.preprocessor.*;
//import edu.school21.printer.logic.renderer.*;

public class Program {
        public static void main(String[] args) {
//        PreProcessor preProcessor = new PreProcessorToUpperImpl();
//        Renderer renderer = new RendererErrImpl(preProcessor);
//        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
//        printer.setPrefix("Prefix");
//        printer.print("Hello!");
//
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerWithPrefix", Printer.class);
        printer.print("Hello!");
    }
}
