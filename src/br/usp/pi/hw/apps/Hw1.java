package br.usp.pi.hw.apps;

import br.usp.pi.core.PGMImage;

/**
 *
 * @author fm
 */
public class Hw1 {

    public static void createSineImage(PGMImage image) throws Exception {
        double angle = 0;
        image.fillWith(1);
        image.setColumnBorder((int) Math.round(image.getColumns() * 0.05));
        image.setRowBorder((int) Math.round(image.getRows() * 0.10));
        for (int i = image.getColumnBorder(); i < image.getColumns() - image.getColumnBorder(); i++) {
            int deltaY = (int) Math.round(Math.sin(angle) * (image.getRows()-1 - image.getRowBorder()))/2;
            int pos = (image.getRows()/ 2 + deltaY);
            image.getData()[pos][i] = 0;
            angle += Math.PI/(image.getRows());
        }
    }
    
    public static void main(String args[])throws Exception {
        PGMImage image = new PGMImage(800, 300, "# hw1.pgm", 1);
        createSineImage(image);
        image.saveImage("hw1.pgm");
    }
}
