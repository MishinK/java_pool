package edu.school21.printer.logic;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Converter {
    private BufferedImage image;
    private String White;
    private String Black;

    public Converter(BufferedImage image, String White, String Black) {
        this.image = image;
        this.White = White;
        this.Black = Black;
    }

    public void printConvertImage() {
		HashMap<String, Integer> colors = new HashMap<>();
		colors.put("BLACK", 0);
		colors.put("RED", 1);
		colors.put("GREEN", 2);
		colors.put("YELLOW", 3);
		colors.put("BLUE", 4);
		colors.put("MAGENTA", 5);
		colors.put("CYAN", 6);
		colors.put("WHITE", 7);

        for(int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                if(image.getRGB(x, y) == -1)
                    System.out.print(Ansi.colorize(" ", Attribute.BACK_COLOR(colors.get(Black))));
                else
                    System.out.print(Ansi.colorize(" ", Attribute.BACK_COLOR(colors.get(White))));
            }
            System.out.println();
        }
    }
}
