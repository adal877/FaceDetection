package org.example.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplayWindow extends JFrame implements KeyListener {
//    private static final DisplayWindow instance = new DisplayWindow();
    private JLabel lbl;
    private ImageIcon icon;
    private JFrame frame;
    private String imgPath = "";
    private BufferedImage img;
    private int imgWidth;
    private int imgHeight;
    /*
     * Makes an instance of it self.
     * Cut off the need to make an object instance
     * just to use the constructor.
     */
    public static DisplayWindow getInstance() {
        return new DisplayWindow();
    }
    public static DisplayWindow getInstance(int width, int height) {
        return new DisplayWindow(width, height);
    }
    public DisplayWindow(int width, int height) {
        imgHeight = height;
        imgWidth  = width;
        drawWindow(width, height);
    }
    public DisplayWindow() {
        drawWindow();
    }
    private void drawWindow() {
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(600, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /*
     * Creates the default window.
     */
    private void drawWindow(int width, int height) {
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void DisplayImg(String imgPath) throws IOException {
        this.imgPath = imgPath;
        img = ImageIO.read(new File(imgPath));
        icon = new ImageIcon(img);
        imgHeight = img.getHeight();
        imgWidth  = img.getWidth();
        lbl = new JLabel();
        lbl.setIcon(icon);
        frame.setSize(imgWidth, imgHeight);
        frame.add(lbl);
    }
    @Override
    public void keyTyped(KeyEvent evt) {
        System.out.println("Key pressed ::::: ");
        System.out.println(evt.getKeyCode());
        System.out.println(evt.getKeyChar());
        System.out.println(":::::::::::::::::::");
        if(evt.getKeyCode() == KeyEvent.VK_SPACE ||
                evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    public int getImgWidth() {
        return imgWidth;
    }

    public int getImgHeight() {
        return imgHeight;
    }
}