package br.usp.pi.hws;

import br.usp.pi.core.PGMImage;
import br.usp.pi.filters.FrequencyFilter;

public class Hw5 {

	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			printUsage();
			System.exit(0);
		}
		String filename = args[0];
		PGMImage image = new PGMImage();
		image.readImage(filename);

		FrequencyFilter ff = new FrequencyFilter();
		ff.applyFilter(image);

		image.saveImage(filename + "-adj");

		System.out.println("Done.");
	}

	private static void printUsage() {
		System.out.println("usage:");
		System.out.println("\t [FILE]");
	}
	
	@SuppressWarnings("unused")
	private static void test() throws Exception {
		String filename = "data/wdg4.pgm";
		PGMImage image = new PGMImage();
		image.readImage(filename);

		FrequencyFilter ff = new FrequencyFilter();
		ff.applyFilter(image);

		image.saveImage(filename + "-adj");
		
		System.exit(0);
	}
}
