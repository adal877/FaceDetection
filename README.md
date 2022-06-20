# FaceDetection
My first try with facedetection using opencv + java.


## Features
- Detect the number of faces in an image;
- Draw a JFrame with the given resource image;
- Support for close window (and finish the program) <br/>
  when the keys 'q' or ' ' (space) are typed.


## To do
1. Draw a rectangle/square around the faces it has detected;
2. export the final image with the faces detected locally;
3. possibly use java spring boot to develop a web application.


## How to run
Use the maven on Intellij to download the opencv, compile and run.
in case something goes wrong, try to run the code below and then try<br/>
to run the maven plugin again:<br/>
`
mvn compile exec:java -Dexec.mainClass="org.OpencvTest.OpencvTest.java"
`

## Example output
<img width=900 height=500 src="https://github.com/adal877/FaceDetection/blob/master/image.png"/>
