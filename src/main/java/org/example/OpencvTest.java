package org.example;

import org.example.utils.ResourceUtil;
import org.example.view.DisplayWindow;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

import java.io.IOException;

public class OpencvTest {
    private static final String rPath = ResourceUtil.getResourcePath("resources/");

    public static int FaceDetection(String imgFile) {
        // Read the image from the file storing it in to a matrix object
        Mat srcImg = Imgcodecs.imread(imgFile);
        // Make an instance of the CascadeClassifier
        String xmlFace = rPath + "haarcascade_frontalface_alt.xml";
        CascadeClassifier classifier = new CascadeClassifier(xmlFace);
        // Detect the face(s)
        MatOfRect facesDetected = new MatOfRect();
        classifier.detectMultiScale(srcImg, facesDetected);
        return facesDetected.toArray().length;
    }
    public static void main( String[] args ) throws IOException {
        nu.pattern.OpenCV.loadLocally();
        String imgFile = rPath +  "group1.jpg";
        int qtdFaces = FaceDetection(imgFile);
        String frameText = "Detected " + qtdFaces + " faces!";
        DisplayWindow.getInstance().DisplayImg(imgFile, frameText);
    }
}