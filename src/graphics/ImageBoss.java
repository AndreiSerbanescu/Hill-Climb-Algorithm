package graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.BufferOverflowException;

/**
 * Created by andrei on 7/20/17.
 */
public class ImageBoss extends JFrame {

    public static void main(String[] args) throws IOException {

        String path = "./data/input-images/fs.jpg";


        BufferedImage image = Utils.loadImage(path);

        BufferedImage imageGrey = Utils.imageToGreyscale(image);

        Graphics graphics = image.getGraphics();
        graphics.setColor(new Color(20, 30, 100, 100));
        graphics.fillRect(100, 100, 50, 50);

        graphics.setColor(new Color(100, 30, 40, 150));
        graphics.fillRect(120, 100, 50, 50);

        JFrame frame = new JFrame();

        //Utils.initSwingFrame(frame);
        //Utils.addImage(frame, image);

        //frame.getContentPane().add(label);
        //frame.repaint();



        Utils.saveImage(imageGrey, "png", "bossdeboss");
    }
}
