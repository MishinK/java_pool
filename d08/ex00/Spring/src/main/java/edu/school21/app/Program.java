package edu.school21.app;


import edu.school21.logic.preprocessor.*;
import edu.school21.logic.printer.*;
import edu.school21.logic.renderer.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		{
        Printer printer = context.getBean("printerWithPrefix", Printer.class);
        Printer printerTime = context.getBean("printerWithDateTimeImpl", Printer.class);
        printer.print("Hello!");
        printerTime.print("Hello!");
		}
		{
        PrinterWithPrefixImpl printerWithPrefix = context.getBean("printerWithPrefix", PrinterWithPrefixImpl.class);
        printerWithPrefix.setPrefix("NEW PREFIX TEST ");
        printerWithPrefix.print("Hello!");
		}
		{
		PreProcessor preProcessor = new PreProcessorToUpperImpl();
		Renderer renderer = new RendererErrImpl(preProcessor);
		PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
		printer.setPrefix("Prefix ");
		printer.print("Hello!");
		}
    }
}
