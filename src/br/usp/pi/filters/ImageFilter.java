package br.usp.pi.filters;

import br.usp.pi.core.PGMImage;

public abstract class ImageFilter {

	protected double[] kernell;
	protected PGMImage image;
	protected int dimension;
	protected int min;
	protected int max;

	/**
	 * 
	 * @param dimension odd and more than 3
	 */
	public ImageFilter(int dimension) {
		this.dimension = dimension;
		this.kernell = new double[dimension * dimension];	
		this.min = 255;
		this.max = 0;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	protected boolean isOut(int i, int j) {
		if (i < 0 || i > image.getRows()-1 
				|| j < 0 || j > image.getColumns()-1) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param image
	 */
	public void applyFilter(PGMImage image) {
		this.image = image;
		setKernell();
		int div = (dimension - 1) / 2;
		for (int i = 0; i < image.getData().length; i++) {
			for (int j = 0; j < image.getData()[i].length; j++) {
				applyKernell(div, i, j);
			}
		}
		adjust();
	}
	
	public void printKernell() {
		for (int i = 0; i < kernell.length ; i++) {
			System.out.printf("%.5f ",kernell[i]);
			if ((i + 1) % dimension == 0) {
				System.out.println();
				System.out.println();
			}
		}
	}
	
	protected abstract void setKernell();
	protected abstract void applyKernell(int div,  int row, int col);
	protected abstract void adjust();
}









