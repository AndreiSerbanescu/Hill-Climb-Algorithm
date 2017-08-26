package GeneticAlgorithm;

/**
 * Created by andrei on 7/25/17.
 */
public class Rectangle {

    private int topLeftX;
    private int topLeftY;
    private int width;
    private int height;


    public Rectangle(int topLeftX, int topLeftY, int width, int height) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.width = width;
        this.height = height;
    }

    public int getTopLeftX() {
        return topLeftX;
    }

    public int getTopLeftY() {
        return topLeftY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setTopLeftX(int topLeftX) {
        this.topLeftX = topLeftX;
    }

    public void setTopLeftY(int topLeftY) {
        this.topLeftY = topLeftY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Rectangle(topLeftX, topLeftY, width, height);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "topLeftX=" + topLeftX +
                ", topLeftY=" + topLeftY +
                ", width=" + width +
                ", height=" + height +
                '}';
    }


}
