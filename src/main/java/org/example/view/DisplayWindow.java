package org.example.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplayWindow extends JFrame {
//    private static final DisplayWindow instance = new DisplayWindow();
    private JLabel lbl;
    private ImageIcon icon;
    private JFrame frame;
    private String imgPath = "";
    private BufferedImage img;
    private int imgWidth;
    private int imgHeight;
    private Boolean isDefaultValue = false;
    private String OnFrameText = "";
    private int positionX = 0;
    private int positionY = 0;
    /*
     * Makes an instance of it self.
     * Cut off the need to make an object instance
     * just to use the constructor.
     */
    public static DisplayWindow getInstance(String text) {
        return new DisplayWindow(text);
    }
    public static DisplayWindow getInstance() {
        return new DisplayWindow();
    }
    public static DisplayWindow getInstance(int width, int height, String text) {
        return new DisplayWindow(width, height, text);
    }
    public DisplayWindow(int width, int height, String text) {
        setImgWidth(width);
        setImgHeight(height);
        setOnFrameText(text);
        drawWindow(getImgWidth(), getImgHeight());
    }

    public DisplayWindow() {
        setIsDefaultValue(true);
        setImgWidth(0);
        setImgHeight(0);
        drawWindow(getImgWidth(), getImgHeight());
    }
    public DisplayWindow(String text) {
        setIsDefaultValue(true);
        setImgWidth(0);
        setImgHeight(0);
        setOnFrameText(text);
        drawWindow(getImgWidth(), getImgHeight());
    }
    /*
     * Creates the default window.
     */
    private void drawWindow(int width, int height) {
        setPositionX(2);
        setPositionY(2);
        frame = new JFrame();
        lbl = new JLabel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Color c = new Color(0xFFFFFF);
                g.setColor(c);
                g.fillRect(getPositionX(), getPositionY(), getOnFrameText().length()*8, 20);
                Color c2 = new Color(0);
                g.setColor(c2);
                g.drawString(getOnFrameText(), getPositionX()+8, getPositionY()+14); //these are x and y positions
                g.drawRect(getPositionX()-1, getPositionY()-1, (getOnFrameText().length()*8)+1, 21);
            }
        };
        frame.setLayout(new FlowLayout());
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                if(evt.getKeyChar() == 'q' ||
                        evt.getKeyChar() == ' ') {
                    System.exit(0);
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
    public void DisplayImg(String imgPath) throws IOException {
        this.imgPath = imgPath;
        img = ImageIO.read(new File(imgPath));
        icon = new ImageIcon(img);
        /*
         * Assumes the image dimensions as values for the window.
         */
        if(isDefaultValue) {
            setImgHeight(img.getHeight());
            setImgWidth(img.getWidth());
        }
        lbl.setIcon(icon);
        frame.setSize(getImgWidth(), getImgHeight());
        frame.repaint();
        frame.add(lbl);
    }
    public int getImgWidth() {
        return imgWidth;
    }
    private void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }
    public int getImgHeight() {
        return imgHeight;
    }
    private void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }
    private Boolean getIsDefaultValue() {
        return isDefaultValue;
    }
    private void setOnFrameText(String OnFrameText) {
        this.OnFrameText = OnFrameText;
    }
    private String getOnFrameText() {
        return OnFrameText;
    }
    private void setIsDefaultValue(Boolean defaultValue) {
        isDefaultValue = defaultValue;
    }

    private int getPositionX() {
        return positionX;
    }

    private void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    private int getPositionY() {
        return positionY;
    }

    private void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}