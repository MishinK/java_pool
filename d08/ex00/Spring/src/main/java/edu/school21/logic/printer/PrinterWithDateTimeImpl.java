package edu.school21.logic.printer;

import java.time.LocalDateTime;

import edu.school21.logic.renderer.Renderer;

public class PrinterWithDateTimeImpl implements Printer {
    private Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String text) {
        renderer.printText(LocalDateTime.now() + " " + text);
    }
}
