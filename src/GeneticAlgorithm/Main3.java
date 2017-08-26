package GeneticAlgorithm;

import java.awt.image.BufferedImage;

/**
 * Created by andrei on 7/29/17.
 */
public class Main3 {
    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("alive2!");

        String path = "./data/input-images/coupleSmall.jpg";
        BufferedImage imageSalam = graphics.Utils.loadImage(path);
        BufferedImage imageGrey = graphics.Utils.imageToGreyscale(imageSalam);

        //String startPath = "./data/input-images/start.png";
        //BufferedImage startImage = graphics.Utils.loadImage(startPath);
        //graphics.Utils.saveImage(startImage, "png", "testStarttt");

        graphics.Utils.saveImage(imageGrey, "png", "salamsalam");

        GeneImage geneImage = new GeneImage(150, imageGrey);
        GeneImage geneImage2;

        for (int i = 0; ; i++) {
            //graphics.Utils.saveImage(geneImage.getImage(), "png", "Salam" + i);
            geneImage2 = (GeneImage)geneImage.clone();


            if (i % 10000 == 0) {
                double imgFitness = geneImage.getFitness();
                System.out.println(imgFitness);
                graphics.Utils.saveImage(geneImage.getImage(), "png", "Xalam" + i);
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
