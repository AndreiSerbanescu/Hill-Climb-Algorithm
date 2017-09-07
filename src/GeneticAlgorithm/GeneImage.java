package GeneticAlgorithm;

import javax.rmi.CORBA.Util;
import java.awt.*;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by andrei on 7/25/17.
 */
public class GeneImage {

    private List<Gene> genes;
    private int maxGenes;
    private BufferedImage targetImage;
    private BufferedImage startImage;
    private BufferedImage rectangleImage;

    private double addMutRate = 1d / 700;
    private double removeMutRate = 1d / 1500;
    private double swapRate = 1d / 700;

    private boolean isComputed = false;

    public GeneImage(int maxGenes, BufferedImage target, BufferedImage startImage) {
        this(maxGenes, target);
        this.startImage = startImage;
    }

    public GeneImage(BufferedImage targetImage) {
        this(100, targetImage);
    }

    public GeneImage(int maxGenes, BufferedImage targetImage) {
        this.maxGenes = maxGenes;
        this.targetImage = targetImage;

        genes = new ArrayList<>(maxGenes);

        Utils.setImageParam(targetImage.getWidth(), targetImage.getHeight());
    }

    public double getFitness() {

        double fitness = 0d;
        BufferedImage rectImage = computeImage();

        for (int i = 0; i < rectImage.getHeight(); i+=3) {
            for (int j = 0; j < rectImage.getWidth(); j+=3) {

                int myImgGreyscale = graphics.Utils.getGreyscale(rectImage, j, i);
                int targetImgGreyscale = graphics.Utils.getGreyscale(targetImage, j, i);

                int difference = Math.abs(myImgGreyscale - targetImgGreyscale);

                fitness += (1d - 1d * difference / 256);
            }
        }
        return Math.pow(fitness / (rectImage.getHeight() * rectImage.getWidth() / 9), 2);
    }

    private BufferedImage computeImage() {

        if (isComputed) {
            return rectangleImage;
        }
        isComputed = true;
        BufferedImage image;

        if (startImage == null) {
            image = new BufferedImage
                    (targetImage.getWidth(), targetImage.getHeight(), targetImage.getType());
        } else {
            image = graphics.Utils.cloneImage(startImage);
        }

        //graphics.Utils.saveImage(image, "png", "compImg");

        Graphics g = image.getGraphics();
        if (startImage == null) {
            g.setColor(new Color(255, 255, 255));
            g.fillRect(0, 0, image.getWidth(), image.getHeight());
        }
        for (Gene gene : genes) {
            int geneGreyscale = gene.getGreyScale();
            g.setColor(
                    new Color(geneGreyscale, geneGreyscale, geneGreyscale, gene.getTransparency()));
            //graphics.fillRect(gene.getTopLeftX(), gene.getTopLeftY(), gene.getWidth(), gene.getHeight());
            g.fillPolygon(gene.getPolygon());
        }
        rectangleImage = image;

        //graphics.Utils.saveImage(rectangleImage, "png", "compImg2");


        return image;
    }
    private void addGene() {
        addGene(3);
    }

    private void addGene(int pointNo) {

        if (genes.size() == maxGenes) {
            return;
        }

        Random rand = new Random();
        int[] xs = new int[pointNo];
        int[] ys = new int[pointNo];
        for (int i = 0; i < pointNo; i++) {
            xs[i] = rand.nextInt(Utils.imageWidth);
            ys[i] = rand.nextInt(Utils.imageHeight);
        }
        int transparency = rand.nextInt(Utils.maxTransparency);
        int greyscale = rand.nextInt(Utils.maxTransparency);

        genes.add(new Gene(new GenePolygon(xs, ys, pointNo), greyscale, transparency));
    }

    public void mutate() {
        Random rand = new Random();

        if (rand.nextDouble() <= addMutRate) {
            addGene();
            isComputed = false;
        }

        for (int i = genes.size() - 1; i >= 0 ; i--) {
            if (rand.nextDouble() <= removeMutRate) {
                genes.remove(i);
                isComputed = false;
            }
        }
        for (int i = 0; i < genes.size(); i++) {
            if (rand.nextDouble() <= swapRate) {
                int newPos = rand.nextInt(genes.size());
                swapGenes(i, newPos);
                isComputed = false;
            }
        }

        for (Gene gene : genes) {
            boolean isMutated = gene.mutate();
            if (isMutated) {
                isComputed = false;
            }
        }
    }
    private void swapGenes(int index1, int index2) {
        Gene gene = genes.get(index1);
        genes.set(index1, genes.get(index2));
        genes.set(index2 ,gene);
    }

    @Override
    public String toString() {
        return "GeneImage{" +
                "genes=" + genes +
                '}';
    }
    public BufferedImage getImage() {
        return computeImage();
    }

    private void setGenes(List<Gene> genes) {
        this.genes = genes;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        GeneImage newImage = new GeneImage(maxGenes, targetImage, startImage);
        List<Gene> newGenes = new ArrayList<>();

        for (Gene gene : genes) {
            newGenes.add((Gene)gene.clone());
        }
        newImage.setGenes(newGenes);
        return newImage;
    }
}
