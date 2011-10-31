package br.usp.pi.filters;

import java.util.ArrayList;
import java.util.Collections;

public class MedianFilter extends ImageFilter {
	
	public MedianFilter(int dimension) {
		super(dimension);
	}

	protected void setKernell() {
	}
	
	protected void applyKernell(int div,  int row, int col) {
		ArrayList<Integer> neighborhood = new ArrayList<Integer>();
		for (int i = row - div; i <= row + div; i++) {
			for (int j = col - div; j <= col + div; j++) {
				if (!isOut(i, j)) {
					neighborhood.add(image.getData()[i][j]);
				}
			}
		}
		Collections.sort(neighborhood);
		image.getData()[row][col] = neighborhood.get(neighborhood.size() / 2);
	}
	@Override
	protected void adjust() {
		// TODO Auto-generated method stub
		
	}
}
