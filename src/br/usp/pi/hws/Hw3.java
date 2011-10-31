package br.usp.pi.hws;

import br.usp.pi.core.PGMImage;
import br.usp.pi.filters.GaussianFilter;
import br.usp.pi.filters.ImageFilter;
import br.usp.pi.filters.MeanFilter;
import br.usp.pi.filters.MedianFilter;

public class Hw3 {

	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
			printUsage();
			System.exit(0);
		}
		String filename = args[0];
		int dimension = Integer.parseInt(args[1]);
		String type = args[2];

		PGMImage image = new PGMImage();
		image.readImage(filename);

		ImageFilter filter;

		if (type.toUpperCase().equals("MEAN")) {
			filter = new MeanFilter(dimension);
		} else         
			if (type.toUpperCase().equals("MEDIAN")) {
				filter = new MedianFilter(dimension);
			} else
				if (type.toUpperCase().equals("MEDIAN")) {
					double sigma = Double.parseDouble(args[3]);
					filter = new GaussianFilter(dimension, sigma);
				} else {
					printUsage();
					System.exit(0);
					return;
				}

		filter.applyFilter(image);

		image.saveImage(filename + "-adj.pgm");
		System.out.println("Done.");
	}

	private static void printUsage() {
		System.out.println("usage:");
		System.out.println("\t [FILE] [FILTER_DIMENSION] [FILTER TYPE]");
		System.out.println("\t\t [FILTER TYPE]: MEAN, MEDIAN, GAUSS ");
	}
	
	@SuppressWarnings("unused")
	private static void test() throws Exception {
		String filename = "data/fce5noi3.pgm";
		PGMImage image = new PGMImage();
		image.readImage(filename);

		GaussianFilter filter = new GaussianFilter(7, 3.0);
		filter.applyFilter(image);

		image.saveImage(filename + "-adj");
	}
}
