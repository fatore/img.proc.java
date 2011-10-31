package br.usp.pi.filters;

public class FrequencyFilter extends ImageFilter {
	
	private double[][] temp;
	private double sigma;
	
	public FrequencyFilter() {
		super(9);
		this.sigma = 1.4;
	}
	
	protected void setKernell() {
		temp = new double[image.getRows()][image.getColumns()];
		int lim = (dimension - 1) / 2;
		for (int j = 0; j < dimension; ++j) {
			for (int i = 0; i < dimension; ++i) {
				int x = (-dimension / 2) + i;
				int y = (-dimension / 2) + j;
				kernell[(x + lim)  + dimension * (y + lim)] = logElement(x, y);
			}
		}
		printKernell();
	}

	private double logElement(int x, int y) {
		double g = 0;
		for (double ySubPixel = y - 0.5; ySubPixel < y + 0.55; ySubPixel += 0.1) {
			for (double xSubPixel = x - 0.5; xSubPixel < x + 0.55; xSubPixel += 0.1) {
				double s = -((xSubPixel * xSubPixel) + (ySubPixel * ySubPixel))
						/ (2 * sigma * sigma);
				g = g + (1 / (Math.PI * Math.pow(sigma, 4))) * (1 + s)
						* Math.pow(Math.E, s);
			}
		}
		g = -g / 121;
		// System.out.println(g);
		return g;
	}
	
	protected void applyKernell(int div, int row, int col) {
		float dataSum = 0;
		int k = 0;
		for (int i = row - div; i <= row + div; i++) {
			for (int j = col - div; j <= col + div; j++) {
				if (!isOut(i, j)) {
					dataSum += (image.getData()[i][j] * kernell[k]); 
				}
				k++;
			}
		}
		temp[row][col] = dataSum;
	}
	
	protected void adjust() {
		boolean[][] output = new boolean[image.getData().length][image.getData()[0].length];
		for (int i = 0; i < image.getData().length - 1; i++) {
			for (int j = 0; j < image.getData()[i].length - 1; j++) {
				if (temp[i][j] * image.getData()[i][j + 1] < 0) {
					output[i][j] = true;
				}
				if (temp[i][j] * image.getData()[i + 1][j] < 0) {
					output[i][j] = true;
				}
				if (temp[i][j] * image.getData()[i + 1][j + 1] < 0) {
					output[i][j] = true;
				}
			}
		}
		for (int i = 0; i < image.getData().length; i++) {
			for (int j = 0; j < image.getData()[i].length; j++) {
				if (output[i][j]) {
					image.getData()[i][j] = 0;
				} else {
					image.getData()[i][j] = 255;
				}
			}
		}
	}
}
