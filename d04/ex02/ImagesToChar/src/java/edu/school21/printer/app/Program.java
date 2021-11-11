package edu.school21.printer.app;

import edu.school21.printer.logic.Converter;
import com.beust.jcommander.*;
import com.beust.jcommander.Parameter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Program {


    @Parameters(separators = "=")
	public static class Arguments {
		@Parameter(names = "--white", required = true)
		public static String white;

		@Parameter(names = "--black", required = true)
		public static String black;
	  }

    public static void main(String[] args) throws IOException {

		if (args.length != 2) {
			System.err.println("expected: Program --white=<name> --black=<name>");
			System.exit(-1);
		}
		Arguments params = new Arguments();
		JCommander.newBuilder().addObject(params).build().parse(args);	
		BufferedImage image = ImageIO.read(new File("target/resources/image.bmp"));
        if(image == null){
			System.err.println("Error: bad path");
			System.exit(-1);
        }
        Converter convert = new Converter(image, params.white, params.black);
        convert.printConvertImage();
    }
}