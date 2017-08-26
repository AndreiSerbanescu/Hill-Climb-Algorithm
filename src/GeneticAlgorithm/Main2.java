package GeneticAlgorithm;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by andrei on 7/29/17.
 */
public class Main2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("alive2!");

        String path = "./data/input-images/coupleSmall.jpg";
        BufferedImage imageSalam = graphics.Utils.loadImage(path);
        BufferedImage imageGrey = graphics.Utils.imageToGreyscale(imageSalam);

        String startPath = "./data/input-images/start.png";
        BufferedImage startImage = graphics.Utils.loadImage(startPath);
        graphics.Utils.saveImage(startImage, "png", "testStarttt");

        graphics.Utils.saveImage(imageGrey, "png", "salamsalam");

        GeneImage geneImage = new GeneImage(500, imageGrey, startImage);
        GeneImage geneImage2;
        double currentFitness = 0;

        for (int i = 0; ; i++) {
            //graphics.Utils.saveImage(geneImage.getImage(), "png", "Salam" + i);
            geneImage2 = (GeneImage)geneImage.clone();


            if (i % 1000 == 0) {
                double imgFitness = geneImage.getFitness();
                System.out.println(imgFitness);

                if (imgFitness > currentFitness) {
                    graphics.Utils.saveImage
                            (geneImage.getImage(), "png", "Salam" + i);
                    currentFitness = imgFitness;
                }

            }


            geneImage.mutate();
            double fitness2 = geneImage2.getFitness();
            double fitness1 = geneImage.getFitness();

            //System.out.println(fitness1 + " " + fitness2);

            if (fitness2 > fitness1) {
                geneImage = geneImage2;
            }
        }
    }
}
