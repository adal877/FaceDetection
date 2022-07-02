package org.example.utils;

import org.example.view.DisplayWindow;
import org.opencv.core.Rect;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WindowUtil {
    public static void getScreenContent() throws AWTException, IOException {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        final BufferedImage image = new BufferedImage ((int)screenRect.getWidth(), (int)screenRect.getHeight(),
                BufferedImage.TYPE_INT_ARGB );
        final Graphics2D graphics2D = image.createGraphics();
            graphics2D.setStroke(new BasicStroke(3));
            graphics2D.setColor(Color.GREEN);
            graphics2D.drawRect(screenRect.x, screenRect.y, (int) screenRect.getWidth(),
                    (int)screenRect.getHeight());
//        BufferedImage capture = new Robot().createScreenCapture(screenRect);
//        ImageIO.write(capture, "bmp", new File("s"));
    }
}
