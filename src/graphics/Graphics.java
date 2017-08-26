package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

/**
 * Created by andrei on 7/20/17.
 */
public class Graphics extends JFrame {

    public Graphics() throws HeadlessException {
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(new DrawArea());

        setVisible(true);
    }

    public static void main(String[] args) {
        new Graphics();
    }
}

class DrawArea extends JPanel {

    Point A;
    Point B;
    RenderedImage image;
    public DrawArea() {
        A = new Point(100, 200);
        B = new Point(150, 250);


    }
    @Override
    protected void paintComponent(java.awt.Graphics graphics) {
        graphics.setColor(new Color(0x167B20));
        //graphics.drawRect(A.x, A.y, 50, 50);
        //graphics.setColor(new Color(50, 15, 22, 50));
        graphics.fillRect(A.x, A.y, 50, 50);
        //graphics.drawLine(A.x, A.y, B.x, B.y);
    }
}
