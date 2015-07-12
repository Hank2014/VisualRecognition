package objectRecognition;

import java.io.File;

import it.unipi.ing.eim.opencv.VideoObjectRecognition;

import org.opencv.core.Core;

public class VideoObjectRecognitionMain {
	
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//		System.load(new File("lib/bin/opencv_ffmpeg2411_64.dll").getAbsolutePath());
	}
	
	private static final String VIDEO_SRC = "data/img/videoTest.mp4";
	private static final int CAMERA_SRC = 0;
	
	private static final String OBJECT_IMG = "data/img/iphone.jpg";

	public static void main(String[] args) throws Exception {
		VideoObjectRecognition objectRecognition = new VideoObjectRecognition(VIDEO_SRC, OBJECT_IMG);
//		VideoObjectRecognition objectRecognition = new VideoObjectRecognition(CAMERA_SRC, OBJECT_IMG);

		objectRecognition.start();
	}
	
}