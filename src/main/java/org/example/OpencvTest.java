package org.example;

import org.example.utils.ImgFileUtil;
import org.example.utils.ResourceUtil;
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
import java.util.Random;

public class OpencvTest {
    private static final OpencvTest instance = new OpencvTest();
    public static OpencvTest getInstance() {
        return instance;
    }
    public static final String rPath = ResourceUtil.getResourcePath("resources/");
    public Rect[] mat;
    public int FaceDetection(String imgFile) {
        // Read the image from the file storing it in to a matrix object
        Mat srcImg = Imgcodecs.imread(imgFile);
        // Make an instance of the CascadeClassifier
        String xmlFace = rPath + "haarcascade_frontalface_alt.xml";
        CascadeClassifier classifier = new CascadeClassifier(xmlFace);
        // Detect the face(s)
        MatOfRect facesDetected = new MatOfRect();
        classifier.detectMultiScale(srcImg, facesDetected);
        mat = facesDetected.toArray();
        return facesDetected.toArray().length;
    }
    public static void main( String[] args ) throws IOException {
        nu.pattern.OpenCV.loadLocally();
        String imgFile = rPath +  "group1.jpg";
        // Rect[] matFaces = OpencvTest.getInstance().FaceDetection(imgFile);
        // matFaces.length();
        int qtdFaces = OpencvTest.getInstance().FaceDetection(imgFile);
        String frameTextOutput = "Detected " + qtdFaces + " faces!";
        String frameTextBase = "Resource/base image";

        DisplayWindow dw = new DisplayWindow();
        dw.DisplayImg(imgFile, frameTextBase);
        dw.DisplayImg(imgFile, frameTextBase + "Test");
//        ImgFileUtil.getInstance().DrawRectOnFaces(dw, frameTextOutput,
//                                          rPath + "faces-tagged.png",
//                                                  getInstance().mat);
    }
}