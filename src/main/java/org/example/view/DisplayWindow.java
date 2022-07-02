package org.example.view;

import org.example.utils.OpencvUtil;
import org.example.utils.ResourceUtil;
import org.example.utils.WindowUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DisplayWindow extends JFrame {
    private static final DisplayWindow instance = new DisplayWindow();
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
    private final Boolean IsVisible;
    public DisplayWindow() {
        IsVisible = false;
        setupNewWindow();
    }
    /*
     * Makes an instance of it self.
     * Cut off the need to make an object instance
     * just to use the constructor.
     */
    public static DisplayWindow getInstance() {
        return instance;
    }
    /*
     * Creates the default window.
     */
    public void setupNewWindow() {
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setVisible(IsVisible);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                System.out.println(evt.getKeyChar());
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
                }
                if (evt.getKeyChar() == 'j') {
                    setPositionY(-2);
                    frame.repaint();
                }
                if(evt.getKeyChar() == 'k') {
                    setPositionY(2);
                    frame.repaint();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        frame.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent event) {
                if (!event.isShiftDown()) {
                    setPositionY(5);
                } else {
                    setPositionY(-5);
                }
            }
        });
    }
    public void setBackgroundImage(BufferedImage img, int textX, int textY) {
        // Position for the rectangle label on the frame.
        setPositionX(textX);
        setPositionY(textY);
        BackgroundImage(img);
    }
    public void setBackgroundImage(BufferedImage img) {
        // Position for the rectangle label on the frame.
        setPositionX(10);
        setPositionY(10);
        BackgroundImage(img);
    }
    public void BackgroundImage(BufferedImage img) {
        setIsVisible(true);
        if(getOnFrameText() != "") {
        lbl = new JLabel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Color c = new Color(0xFFFFFF);
                g.setColor(c);
                g.fillRect(getPositionX(), getPositionY(), (getOnFrameText().length()*7)+4, 20);
                Color c2 = new Color(0);
                g.setColor(c2);
                g.drawString(getOnFrameText(), getPositionX()+8, getPositionY()+14);
                // positions
                g.drawRect(getPositionX(), getPositionY(), (getOnFrameText().length()*7)+4, 21);
            }
        };
            System.out.println(getOnFrameText());
    } else {
        lbl = new JLabel();
    }
        icon = new ImageIcon(img);
        lbl.setIcon(icon);
        frame.setSize(img.getWidth(), img.getHeight());
        frame.add(lbl);
        frame.setLocationRelativeTo(null);
    }
    public int getFrameWidth() {
        return imgWidth;
    }
    public void setFrameWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }
    public int getFrameHeight() {
        return imgHeight;
    }
    public void setFrameHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }
    private Boolean getIsDefaultValue() {
        return isDefaultValue;
    }
    public void setOnFrameText(String OnFrameText) {
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
    public void setImgPath(String path) { imgPath = path;}
    public void setIsVisible(Boolean IsVisible) {
        frame.setVisible(IsVisible);
    }
}