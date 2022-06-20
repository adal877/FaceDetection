package org.example;

import org.example.utils.ClassLoaderUtil;
import org.example.view.DisplayWindow;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

import java.io.IOException;
import java.net.URISyntaxException;

public class OpencvTest {
    public static String rPath;

    static {
        try {
            rPath = ClassLoaderUtil.getResource("resources/");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static int FaceDetection(String imgFile) {
        // Read the image from the file storing it in to a matrix object
        Mat srcImg = Imgcodecs.imread(imgFile);
        // Make an instance of the CascadeClassifier
        String xmlFace = rPath + "haarcascade_frontalface_alt.xml";
        CascadeClassifier classifier = new CascadeClassifier(xmlFace);
        // Detect the face(s)
        MatOfRect facesDetected = new MatOfRect();
        classifier.detectMultiScale(srcImg, facesDetected);
        System.out.println("::::: Faces :::::");
        System.out.println(facesDetected);
        System.out.println(":::::::::::::::::");
        return facesDetected.toArray().length;
    }
    public static void main( String[] args ) throws IOException, URISyntaxException {
        nu.pattern.OpenCV.loadLocally();
        String imgFile = rPath +  "group1.jpg";
        int qtdFaces = FaceDetection(imgFile);
        System.out.printf("Detected %s face(s)!", qtdFaces);
        String frameText = "Detected " + qtdFaces + " faces!";

        DisplayWindow.getInstance(frameText).DisplayImg(imgFile);
    }
}