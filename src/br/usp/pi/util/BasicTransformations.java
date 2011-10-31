
package br.usp.pi.util;

import br.usp.pi.core.PGMImage;

/**
 *
 * @author fm
 */
public class BasicTransformations {
/**
 *
 * @param image
 */
    public static void invertColors(PGMImage image) {
        for (int i = 0; i < image.getData().length; i++) {
            for (int j = 0; j < image.getData()[i].length; j++) {
                image.getData()[i][j] = image.getMaxGrayValue() - image.getData()[i][j];
            }
        }
    }
 /**
  *
  * @param image
  * @param amount
  */
    public static void increaseBrightness(PGMImage image, int amount) {
        for (int i = 0; i < image.getData().length; i++) {
            for (int j = 0; j < image.getData()[i].length; j++) {
                image.getData()[i][j] = (image.getData()[i][j] + amount);
                if (image.getData()[j][i] > image.getMaxGrayValue())
                    image.getData()[j][i] = image.getMaxGrayValue();
            }
        }
    }
/**
 *
 * @param image
 * @param amount
 */
    public static void decreaseBrightness(PGMImage image, int amount) {
        for (int i = 0; i < image.getData().length; i++) {
            for (int j = 0; j < image.getData()[i].length; j++) {
                image.getData()[i][j] = (image.getData()[i][j] - amount);
                if (image.getData()[i][j] < 0)
                    image.getData()[i][j] = 0;
            }
        }
    }
/**
 * Basic adjustment based on min and max values.
 * @param image
 */
    public static void adjustContrast(PGMImage image) {
        int current;
        int max = 0;
        int min = image.getMaxGrayValue();
        for (int i = 0; i < image.getData().length; i++) {
            for (int j = 0; j < image.getData()[i].length; j++) {
                int value = image.getData()[i][j];
                if (value > max) max = value;
                if (value < min) min = value;
            }
        }
        for (int i = 0; i < image.getData().length; i++) {
            for (int j = 0; j < image.getData()[i].length; j++) {
                image.getData()[i][j] = Math.round((image.getData()[i][j] - min) *
                        (float) (image.getMaxGrayValue()/(max - min)));
            }
        }
    }
/**
 * Logarithmic adjustment.
 * @param image
 * @param c a constant value between [0,L]
 */
    public static void applyLogScale(PGMImage image) {
        for (int i = 0; i < image.getData().length; i++) {
            for (int j = 0; j < image.getData()[i].length; j++) {
                image.getData()[i][j] =  (int) Math.round(Math.log(image.getData()[i][j] + 1));
            }
        }
        adjustContrast(image);
    }

/**
 * Power-Law adjustment.
 * @param image
 * @param c
 * @param exp
 */
    public static void applyExpScale(PGMImage image) {
        double c = Math.log(image.getMaxGrayValue() + 1.0)/ (double) image.getMaxGrayValue() ;
        for (int i = 0; i < image.getData().length; i++) {
            for (int j = 0; j < image.getData()[i].length; j++) {
                image.getData()[i][j] = (int) Math.round(Math.exp(c*image.getData()[i][j]));
            }
        }
        adjustContrast(image);
    }
/**
 * 
 * @param image
 */
    public static void equalize(PGMImage image) {

        //calculate the incidence of each gray level
        int incidence[] = new int[image.getMaxGrayValue()+1];
        for (int i = 0; i < image.getData().length; i++) {
            for (int j = 0; j < image.getData()[i].length; j++) {
                incidence[image.getData()[i][j]]++;
            }
        }

        //calculate for each gray level its cdf
        int cdf[] = new int[image.getMaxGrayValue()+1];
        for (int i = 0; i < cdf.length; i++) {
            for (int j = 0; j <= i; j++) {
                cdf[i] += incidence[j];
            }
        }

        //calculate the min value of cdf for all gray levels
        int min = 0;
        while (cdf[min++] == 0) {}
        min = cdf[min-1];

        //calculate the new value for each gray level
        int newHist[] = new int[image.getMaxGrayValue()+1];
        int total = image.getColumns() * image.getRows();
        for (int i = 0; i < newHist.length; i++) {
            newHist[i] = Math.round(((float) (cdf[i] - min)/ (total - min)) * image.getMaxGrayValue());
        }

        //update the image data
        for (int i = 0; i < image.getData().length; i++) {
            for (int j = 0; j < image.getData()[i].length; j++) {
                image.getData()[i][j] = newHist[image.getData()[i][j]];
            }
        }
    }
}
