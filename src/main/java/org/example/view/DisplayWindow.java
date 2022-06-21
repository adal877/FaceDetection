package org.example.view;

import org.example.OpencvTest;
import org.example.utils.ImgFileUtil;

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
    public static DisplayWindow getInstance() {
        return new DisplayWindow();
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
        setPositionX(3);
        setPositionY(3);
        frame = new JFrame();
        if(getOnFrameText() == "") {
            lbl = new JLabel() {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Color c = new Color(0xFFFFFF);
                    g.setColor(c);
                    g.fillRect(getPositionX(), getPositionY(), (getOnFrameText().length()*7)+4, 20);
                    Color c2 = new Color(0);
                    g.setColor(c2);
                    g.drawString(getOnFrameText(), getPositionX() + 8, getPositionY() + 14);
                    // positions
                    g.drawRect(getPositionX() - 1, getPositionY() - 1, (getOnFrameText().length()*7)+4, 21);
                }
            };
        } else {
            lbl = new JLabel();
        }
        frame.setLayout(new FlowLayout());
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                if(evt.getKeyChar() == 'q' ||
                        evt.getKeyChar() == ' ') {
                    int opt = JOptionPane.showConfirmDialog(null, "Would you like to exit?", "Exit",
                            JOptionPane.YES_NO_OPTION);
                    if(opt != 1) {
                        System.exit(0);
                    }
                }
                if(evt.getKeyChar() == 'd') {
                    JOptionPane.showMessageDialog(null, "The image will be deleted on program exit...");
                    ImgFileUtil.getInstance().deleteFileOnExit(new File(OpencvTest.rPath + ImgFileUtil.imgName));
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
    public void DrawRectOnFace(final int x, final int y, final int sizeX, final int sizeY) {
        JLabel rect = new JLabel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Color c = new Color(0xFF09FF00, true);
                g.setColor(c);
                g.drawRect(x, y, sizeX, sizeY);
            }
        };
        frame.add(rect);
    }
    public void DisplayImg(String imgPath, String text) throws IOException {
        this.imgPath = imgPath;
        setOnFrameText(text);
        img = ImageIO.read(new File(imgPath));
        icon = new ImageIcon(img);
        /*
         * Assumes the image dimensions as values for the window.
         */
        if(isDefaultValue) {
            setImgHeight(img.getHeight());
            setImgWidth(img.getWidth());
        }
        frame.setSize(getImgWidth(), getImgHeight());
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setLocationRelativeTo(null);
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
        this.OnFrameText = " " + OnFrameText + " ";
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
    public String getImgPath() {
        return imgPath;
    }
}