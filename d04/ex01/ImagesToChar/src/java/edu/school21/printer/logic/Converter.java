package edu.school21.printer.logic;

import java.awt.image.BufferedImage;

public class Converter {
    private BufferedImage image;
    private char Black;
    private char White;

    public Converter(BufferedImage image, char White, char Black) {
        this.image = image;
        this.White = White;
        this.Black = Black;
    }

    public void printConvertImage() {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (image.getRGB(x, y) == -1)
                    System.out.print(White);
                else
                    System.out.print(Black);
            }
            System.out.println();
        }
    }
}
