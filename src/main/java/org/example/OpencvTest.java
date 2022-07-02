package org.example;

import org.example.utils.OpencvUtil;
import org.example.utils.ResourceUtil;

import org.example.view.DisplayWindow;
import org.opencv.core.Rect;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OpencvTest {
    public static final String rPath = ResourceUtil.getResourcePath("resources/Assets/");
    public static void main( String[] args ) throws IOException {
        nu.pattern.OpenCV.loadLocally();
        String imgFile = rPath +  "group7.jpg";
        BufferedImage img = ImageIO.read(new File(imgFile));

        Rect[] matFaces = OpencvUtil.getInstance().FaceDetection(imgFile);
        Rect[] matEyes = OpencvUtil.getInstance().EyeDetection(imgFile);
        String frameTextOutput = "Detected " + matFaces .length + " faces!";
        // https://github.com/opencv/opencv/tree/master/data/haarcascades

        DisplayWindow dwFaces = new DisplayWindow();
        dwFaces.setFrameHeight(img.getHeight());
        dwFaces.setFrameWidth(img.getWidth());
        dwFaces.setOnFrameText(frameTextOutput);
        BufferedImage imgFaces = OpencvUtil.getInstance().DrawRectOnDetections(matFaces, img, dwFaces);
        System.out.println(dwFaces.getFrameHeight());
        dwFaces.setBackgroundImage(imgFaces, 10, dwFaces.getFrameHeight()-75);

        DisplayWindow dwEyes = new DisplayWindow();
        dwEyes.setFrameHeight(img.getHeight());
        dwEyes.setFrameWidth(img.getWidth());
        dwEyes.setOnFrameText(matEyes.length + " eye" + "(s) detected!");
        BufferedImage imgEyes = OpencvUtil.getInstance().DrawRectOnDetections(matEyes, img, dwEyes);
//        dwEyes.setBackgroundImage(imgEyes);
    }
}