package GeneticAlgorithm;

import java.awt.*;

/**
 * Created by andrei on 7/27/17.
 */
public class GenePolygon extends Polygon {

    public GenePolygon(int[] ints, int[] ints1, int i) {
        super(ints, ints1, i);
    }

    public void setPoint(int index, int x, int y) {
        this.xpoints[index] = x;
        this.ypoints[index] = y;
    }

    public void setX(int index, int x) {
        this.xpoints[index] = x;
    }

    public void setY(int index, int y) {
        this.ypoints[index] = y;
    }
}
