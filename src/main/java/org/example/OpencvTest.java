package org.example;

import org.example.view.DisplayWindow;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

import java.io.IOException;

public class OpencvTest {
    public static String rootPath = "/Users/adal/git-sources/opencv/samples/winrt/FaceDetection/FaceDetection/Assets/";
    public static int FaceDetection(String imgFile) {
        // Read the image from the file storing it in to a matrix object
        Mat srcImg = Imgcodecs.imread(imgFile);
        // Make an instance of the CascadeClassifier
        String xmlFace = rootPath + "haarcascade_frontalface_alt.xml";
        CascadeClassifier classifier = new CascadeClassifier(xmlFace);
        // Detect the face(s)
        MatOfRect facesDetected = new MatOfRect();
        classifier.detectMultiScale(srcImg, facesDetected);
        System.out.println("::::: Faces :::::");
        System.out.println(facesDetected);
        System.out.println(":::::::::::::::::");
        return facesDetected.toArray().length;
    }
    public static void main( String[] args ) throws IOException {
        nu.pattern.OpenCV.loadLocally();
        String imgFile = rootPath + "group1.jpg";
        int qtdFaces = FaceDetection(imgFile);
        System.out.printf("Detected %s face(s)!", qtdFaces);
        String frameText = "Detected " + String.valueOf(qtdFaces) + " faces!";

        DisplayWindow.getInstance(frameText).DisplayImg(imgFile);
    }
}