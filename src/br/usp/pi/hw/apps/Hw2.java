package br.usp.pi.hw.apps;


import br.usp.pi.util.BasicTransformations;
import br.usp.pi.core.PGMImage;

/**
 *
 * @author fm
 */
public class Hw2 {

    public static void main(String args[]) throws Exception {
        String filename = "data/fofa.pgm";
        PGMImage image = new PGMImage();
        image.readImage(filename);
//        BasicTransformations.invertColors(image);
//        BasicTransformations.increaseBrightness(image, 100);
//        BasicTransformations.decreaseBrightness(image, 100);
        BasicTransformations.adjustContrast(image);
//        BasicTransformations.applyLogScale(image);
//        BasicTransformations.applyExpScale(image);
//        BasicTransformations.equalize(image);
        image.saveImage(filename + "2");
    }
}
