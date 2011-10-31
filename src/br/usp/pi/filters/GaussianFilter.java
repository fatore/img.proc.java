package br.usp.pi.filters;

public class GaussianFilter extends ImageFilter {
	
	private double sigma; 
	
	public GaussianFilter(int dimension, double sigma) {
		super(dimension);
		this.sigma = sigma;
	}
	
	protected void setKernell() {
		int lim = (dimension - 1) / 2;
		double sigma2 = sigma * sigma;
		double g1 = (1 / (2 * Math.PI * sigma2));
		double min = g1 * g2(-lim, -lim, sigma2);

		for (int y = -lim; y <= lim; y++) {
			for (int x = -lim; x <= lim; x++) {
				double value = g1 * g2(x, y, sigma2);
				value = Math.round(value / min);
				kernell[(x + lim)  + dimension * (y + lim)] = value;
			}
		}
	}
	
	private double g2(int x, int y, double sigma2) {
		return (1 / (Math.exp((x*x + y*y) / (2 * sigma2))));
	}
	
	protected void applyKernell(int div,  int row, int col) {
		int kernellSum = 0;
		int dataSum = 0;
		int k = 0;
		for (int i = row - div; i <= row + div; i++) {
			for (int j = col - div; j <= col + div; j++) {
				if (!isOut(i, j)) {
					kernellSum += kernell[k];
					dataSum += (image.getData()[i][j] * kernell[k]); 
				}
				k++;
			}
		}
		image.getData()[row][col] = dataSum / kernellSum;
	}
	
	@Override
	protected void adjust() {
		// TODO Auto-generated method stub
		
	}
	
}
