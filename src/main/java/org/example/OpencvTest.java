package org.example;

import org.example.utils.OpencvUtil;
import org.example.utils.ResourceUtil;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

import java.io.IOException;

public class OpencvTest {
    private static final OpencvTest instance = new OpencvTest();
    public static OpencvTest getInstance() {
        return instance;
    }
    public static final String rPath = ResourceUtil.getResourcePath("resources/Assets/");
    public static void main( String[] args ) throws IOException {
        nu.pattern.OpenCV.loadLocally();
        String imgFile = rPath +  "group1.jpg";

        Rect[] matFaces = OpencvUtil.getInstance().FaceDetection(imgFile);
        String frameTextOutput = "Detected " + matFaces.length + " faces!";

        OpencvUtil.getInstance().DrawRectOnFaces(matFaces, imgFile, frameTextOutput);
    }
}