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
        String path;
        int outputGeneration;

        if (args.length == 2) {
            path = args[0];
            outputGeneration = Integer.valueOf(args[1]);
        } else {
            path = "./start.png";
            outputGeneration = 50000;
        }


        BufferedImage imageSalam = graphics.Utils.loadImage(path);
        BufferedImage imageGrey = graphics.Utils.imageToGreyscale(imageSalam);

        graphics.Utils.saveImage(imageGrey, "png", "output");


        GeneImage geneImage = new GeneImage(200, imageGrey);
        GeneImage geneImage2;

        BufferedImage image = new BufferedImage(500, 500, imageGrey.getType());
        Graphics graphhics = image.getGraphics();
        graphhics.setColor(new Color(255, 255, 255));

        for (int i = 0; ; i++) {
            //graphics.Utils.saveImage(geneImage.getImage(), "png", "Salam" + i);
            geneImage2 = (GeneImage)geneImage.clone();


            if (i % outputGeneration == 0) {
                System.out.println(geneImage.getFitness());

                graphics.Utils.saveImage
                        (geneImage.getImage(), "png", "Output" + i);
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
