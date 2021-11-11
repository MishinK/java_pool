package edu.school21.printer.app;

import edu.school21.printer.logic.Converter;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Program {
	public static void main(String[] args) throws IOException {

		if (args.length != 3) {
            System.err.println("error: 3 arg expected");
            System.exit(-1);
		}

		if (!args[0].contains("--white=") || !args[1].contains("--black=")) {
            System.err.println("expected: Program --white=<char> --black=<char> <path:img>");
            System.exit(-1);
        }
        String parseWhite = args[0].substring(args[0].indexOf('=') + 1);
        String parseBlack = args[1].substring(args[1].indexOf('=') + 1);
        if (parseWhite.length() < 1 || parseBlack.length() < 1) {
            System.err.println("Missing value");
            System.exit(-1);
        }
		char symbolForWhite = parseWhite.toCharArray()[0];
		char symbolForBlack = parseBlack.toCharArray()[0];
		String path = args[2];
		try {
			File file = new File(path);
			BufferedImage image = ImageIO.read(file);
			Converter convert = new Converter(image, symbolForWhite, symbolForBlack);
			convert.printConvertImage();
		} catch (IOException e) {
			System.err.println("Error: bad path");
			System.exit(-1);
		}
	}
}
