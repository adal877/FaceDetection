package org.example.utils;

import org.example.OpencvTest;
import org.example.view.DisplayWindow;
import org.opencv.core.Rect;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImgFileUtil {
    private static final ImgFileUtil instance = new ImgFileUtil();
    public static ImgFileUtil getInstance() {
        return instance;
    }
    public static String imgName;
    public void DrawRectOnFaces(DisplayWindow dw, String frameText,
                                String fileName, Rect[] mat) throws IOException {
        imgName = fileName;
        final BufferedImage image = new BufferedImage ( dw.getImgWidth(), dw.getImgHeight(),
                BufferedImage.TYPE_INT_ARGB );
        final Graphics2D graphics2D = image.createGraphics ();
        Image background = ImageIO.read(new File(dw.getImgPath()));
        graphics2D.drawImage(background, 0, 0, dw);
        for(Rect matt : mat) {
            dw.DrawRectOnFace(matt.x, matt.y, matt.width, matt.height);
            graphics2D.setStroke(new BasicStroke(3));
            graphics2D.setColor(Color.GREEN);
            graphics2D.drawRect(matt.x, matt.y, matt.width, matt.height);
        }
        graphics2D.dispose ();
        ImageIO.write ( image, "png", new File( fileName) );
        DisplayWindow.getInstance().DisplayImg(fileName, frameText);
    }
    public void deleteFileOnExit(File file) {
        file.deleteOnExit();
    }
}