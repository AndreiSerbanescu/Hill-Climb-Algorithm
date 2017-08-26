package GeneticAlgorithm;

import java.awt.*;
import java.util.Random;

/**
 * Created by andrei on 7/25/17.
 */


//TODO reformat mutation by making a rect consist only of points
public class Gene {

    private double addPointMutRate = 1d / 3000;
    private double deletePointMutRate = addPointMutRate / 2;

    private double xMutRate = 1d / 700;
    private double yMutRate = 1d / 700;
    private double transparencyMutRate = 1d / 1500;
    private double greyscaleMutRate = 1d / 1500;
    private double transparencyMaxMutPercent = 0.2;


    private GenePolygon polygon;
    private int transparency;
    private int greyscale;

    /*
    public Gene(int x, int y, int width, int height, int greyscale, int transparency) {
        rectangle = new Rectangle(x, y, width, height);
        this.transparency = transparency;
        this.greyscale = greyscale;
    } */

    public Gene(GenePolygon polygon, int greyscale, int transparency) {
        this.polygon = polygon;
        this.transparency = transparency;
        this.greyscale = greyscale;
    }
    public Gene(int greyscale, int transparency, int[] xs, int[] ys, int npoints) {
        this(new GenePolygon(xs, ys, npoints), greyscale, transparency);
    }

    Polygon getPolygon() {
        return polygon;
    }
    boolean mutate() {
        Random rand = new Random();
        boolean isMutated = false;
        double randNo;

        for (int i = 0; i < polygon.npoints; i++) {
            randNo = rand.nextDouble();
            if (randNo <= xMutRate) {
                mutateX(i, randNo);
                isMutated = true;
            }
            randNo = rand.nextDouble();
            if (randNo <= yMutRate) {
                mutateY(i, randNo);
                isMutated = true;
            }
        }
        if (rand.nextDouble() <= addPointMutRate) {
            mutateAddPoint();
            isMutated = true;
        }
        if (rand.nextDouble() <= deletePointMutRate) {
            mutateDeletePoint();
            isMutated = true;
        }

        randNo = rand.nextDouble();
        if (randNo <= transparencyMutRate) {
            mutateTransparency(randNo);
            isMutated = true;
        }
        randNo = rand.nextDouble();
        if (randNo <= greyscaleMutRate) {
            mutateGreyscale(randNo);
            isMutated = true;
        }

        return isMutated;
    }
    private void mutateAddPoint() {
        //System.out.println("added point");
        Random rand = new Random();
        int x = rand.nextInt(Utils.imageWidth * 2);
        x -= (Utils.imageWidth / 2);
        int y = rand.nextInt(Utils.imageHeight * 2);
        y -= (Utils.imageHeight / 2);

        polygon.addPoint(x, y);
    }
    private void mutateDeletePoint() {
        if (polygon.npoints == 3) {
            return;
        }
        //System.out.println("deleted point!");
        Random rand = new Random();
        int index = rand.nextInt(polygon.npoints);
        Polygon newPolygon = new Polygon();
        for (int i = 0; i < polygon.npoints; i++) {
            if (i != index) {
                newPolygon.addPoint(polygon.xpoints[i], polygon.ypoints[i]);
            }
        }
    }

    private void mutateX(int index, double randDouble) {
        if (randDouble <= xMutRate / 4) {
            mutateXHelper(index, 3);
        } else if (randDouble <= xMutRate / 2) {
            mutateXHelper(index, 2);
        } else if (randDouble <= xMutRate) {
            mutateXHelper(index, 1);
        }
    }
    private void mutateY(int index, double randDouble) {

        if (randDouble <= yMutRate / 4) {
            mutateYHelper(index, 3);
        } else if (randDouble <= yMutRate / 2) {
            mutateYHelper(index, 2);
        } else if (randDouble <= yMutRate) {
            mutateYHelper(index, 1);
        }
    }

    private void mutateXHelper(int index, int type) {
        int newX = mutateCoord(index, polygon.xpoints[index], type, Utils.imageWidth);
        polygon.setX(index, newX);
    }
    private void mutateYHelper(int index, int type) {
        int newY = mutateCoord(index, polygon.ypoints[index], type, Utils.imageHeight);
        polygon.setY(index, newY);
    }
    private int mutateCoord(int index, int coord, int type, int maxBound) {
        int initDistance = 0;
        switch (type) {
            case 1:
                initDistance = 0;
                break;
            case 2:
                initDistance = maxBound / 5;
                break;
            case 3:
                initDistance = (int) Math.floor(maxBound * 0.4);
        }

        Random rand = new Random();
        int radius = initDistance / 2 + maxBound / 5;
        int distance = rand.nextInt(radius * 2) - radius;
        int newC = coord + distance;

        if (newC < -maxBound  / 2) {
            newC = -maxBound / 2;
        } else if (newC >= maxBound * 3 / 2) {
            newC = maxBound * 3 / 2;
        }
        return newC;
    }

    private void mutateTransparency(double randDouble) {
        Random rand = new Random();
        int difference = rand.nextInt(30);

        if (randDouble <= transparencyMutRate / 2) {
            difference += 30;
        }
        if (randDouble <= transparencyMutRate / 4) {
            difference += 30;
        }
        if (randDouble <= transparencyMutRate / 8) {
            difference += 30;
        }

        if (rand.nextInt(2) == 0) {
            difference = -difference;
        }

        transparency = transparency + difference;

        if (transparency <= 0) {
            transparency = 1;
        } else if (transparency > Utils.maxTransparency) {
            transparency = Utils.maxTransparency;
        }
    }

    private void mutateGreyscale(double randDouble) {
        Random rand = new Random();
        int difference = rand.nextInt(30);

        if (randDouble <= greyscaleMutRate / 2) {
            difference += 30;
        }
        if (randDouble <= greyscaleMutRate / 4) {
            difference += 30;
        }
        if (randDouble <= greyscaleMutRate / 8) {
            difference += 30;
        }

        if (rand.nextInt(2) == 0) {
            difference = -difference;
        }

        greyscale = greyscale + difference;

        if (greyscale <= 0) {
            greyscale = 1;
        } else if (greyscale > Utils.maxTransparency) {
            greyscale = Utils.maxTransparency;
        }
    }

    @Override
    public String toString() {
        return "Gene{" +
                "polygon=" + polygon +
                ", transparency=" + transparency +
                ", greyscale=" + greyscale +
                '}';
    }

    public int getTransparency() {
        return transparency;
    }

    public int getGreyScale() {
        return greyscale;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Gene(new GenePolygon(polygon.xpoints, polygon.ypoints, polygon.npoints), greyscale, transparency);
    }
}
