package org.example.utils;

import org.example.view.DisplayWindow;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OpencvUtil {
    public static final String rPath = ResourceUtil.getResourcePath("resources/Assets/xml");
    private static final OpencvUtil instance = new OpencvUtil();
    public static OpencvUtil getInstance() {
        return instance;
    }
    public Rect[] FaceDetection(String imgFile) {
        // Read the image from the file storing it in to a matrix object
        Mat srcImg = Imgcodecs.imread(imgFile);
        // Make an instance of the CascadeClassifier
        String xmlFace = rPath + "haarcascade_frontalface_alt.xml";
        CascadeClassifier classifier = new CascadeClassifier(xmlFace);
        // Detect the face(s)
        MatOfRect facesDetected = new MatOfRect();
        classifier.detectMultiScale(srcImg, facesDetected);
        return facesDetected.toArray();
    }
    public Rect[] EyeDetection(String imgFile) {
        // Read the image from the file storing it in to a matrix object
        Mat srcImg = Imgcodecs.imread(imgFile);
        // Make an instance of the CascadeClassifier
        String xmlEye = rPath + "haarcascade_eye.xml";
        CascadeClassifier classifier = new CascadeClassifier(xmlEye);
        // Detect the face(s)
        MatOfRect eyesDetected = new MatOfRect();
        classifier.detectMultiScale(srcImg, eyesDetected);
        return eyesDetected.toArray();
    }
    public BufferedImage DrawRectOnDetections(Rect[] mat, BufferedImage imageFile, DisplayWindow dw) throws IOException {
        return DrawDetection(mat, imageFile, dw);
    }

    private BufferedImage DrawDetection(Rect[] mat, BufferedImage img, DisplayWindow dw) throws IOException {
        final BufferedImage image = new BufferedImage ( dw.getFrameWidth(), dw.getFrameHeight(),
                BufferedImage.TYPE_INT_ARGB );
        final Graphics2D graphics2D = image.createGraphics();
        graphics2D.drawImage(img, 0, 0, dw);
        int i = 1;
        for(final Rect matt : mat) {
            graphics2D.setStroke(new BasicStroke(3));
            graphics2D.setColor(Color.GREEN);
            graphics2D.drawRect(matt.x, matt.y, matt.width, matt.height);
            graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            graphics2D.drawString(String.valueOf(i), matt.x, matt.y);
            i++;
        }
        graphics2D.dispose ();
        ImageIO.write(image, "png", new File("tagged-faces.png"));
        File f = new File(ResourceUtil.getResourcePath("tagged-faces.png"));
        BufferedImage savedImg = ImageIO.read(f);

        f.deleteOnExit();
        return savedImg;
    }
}
