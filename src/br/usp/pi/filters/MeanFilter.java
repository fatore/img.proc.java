package br.usp.pi.filters;

public class MeanFilter extends ImageFilter {
	
	public MeanFilter(int dimension) {
		super(dimension);
	}

	protected void setKernell() {
	}
	
	protected void applyKernell(int div, int row, int col) {
		int count = 0;
		int sum = 0;
		for (int i = row - div; i <= row + div; i++) {
			for (int j = col - div; j <= col + div; j++) {
				if (!isOut(i, j)) {
					sum += image.getData()[i][j];
					count++;
				}
			}
		}
		int mean = sum / count;
		image.getData()[row][col] = mean;
	}
	
	@Override
	protected void adjust() {
		// TODO Auto-generated method stub
		
	}
}
