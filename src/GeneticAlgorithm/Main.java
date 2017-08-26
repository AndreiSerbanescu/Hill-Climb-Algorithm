package GeneticAlgorithm;

import graphics.*;

import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Created by andrei on 7/25/17.
 */
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException{
        System.out.println("alive!");

        String path = "./data/input-images/coupleSmall.jpg";
        BufferedImage imageSalam = graphics.Utils.loadImage(path);
        BufferedImage imageGrey = graphics.Utils.imageToGreyscale(imageSalam);

        graphics.Utils.saveImage(imageGrey, "png", "salamsalam");

        GeneImage geneImage = new GeneImage(500, imageGrey);
        GeneImage geneImage2;

        BufferedImage image = new BufferedImage(500, 500, imageGrey.getType());
        Graphics graphhics = image.getGraphics();
        graphhics.setColor(new Color(255, 255, 255));

        for (int i = 0; ; i++) {
            //graphics.Utils.saveImage(geneImage.getImage(), "png", "Salam" + i);
            geneImage2 = (GeneImage)geneImage.clone();


            if (i % 50000 == 0) {
                System.out.println(geneImage.getFitness());

                graphics.Utils.saveImage
                        (geneImage.getImage(), "png", "Salam" + i);
            }


            geneImage.mutate();

            if (geneImage2.getFitness() > geneImage.getFitness()) {
                geneImage = geneImage2;
            }
        }


        /*
        for (int i = 0; i < 100; i++) {
            System.out.println(geneImage);
            System.out.println();
            geneImage.mutate();
        }
        */
    }
}
