package objectRecognition;

import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Hashtable;

import javax.swing.*;

import it.unipi.ing.eim.opencv.Parameters;

public class MyFrame extends JFrame {
	
	private JPanel dx = new JPanel();
	private JPanel sx = new JPanel();
	
	private ButtonGroup group = new ButtonGroup();
	private JRadioButton videoRadio = new JRadioButton("Video");
	private JRadioButton camRadio = new JRadioButton("WebCam");
		
	private JButton startButton = new JButton("Start");
	
	public MyFrame() {
		
		// Filter .mov or .mp4 extension
		FilenameFilter fileNameFilter = new FilenameFilter() {
			  
	           @Override
	           public boolean accept(File dir, String name) {
	              if(name.lastIndexOf('.')>0)
	              {
	                 int lastIndex = name.lastIndexOf('.');
	                 
	                 String str = name.substring(lastIndex);
	                 
	                 if(str.equals(".mov") || str.equals(".MOV") || str.equals(".mp4") || str.equals(".MP4"))
	                 {
	                    return true;
	                 }
	              }
	              return false;
	           }
	        };
	        
		Container c = this.getContentPane();
		c.setLayout(new GridLayout(1, 2));
		//sx.setLayout(new GridLayout(2,1));
		
		c.add(sx);
		c.add(dx);
		
		camRadio.setAlignmentX(Component.LEFT_ALIGNMENT);
		videoRadio.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		// Group of radio button:
		// choose between webcam or video source
		group.add(camRadio);
		group.add(videoRadio);
		
		Box radioBox = Box.createVerticalBox();
		radioBox.add(camRadio);
		radioBox.add(videoRadio);
		
		sx.setLayout(new FlowLayout(FlowLayout.CENTER));
		sx.add(radioBox);
		
		Hashtable<Integer, JLabel> hash1 = new Hashtable<>();
		hash1.put(1, new JLabel("1"));
		hash1.put(15, new JLabel("15"));
		hash1.put(30, new JLabel("30"));
		
		Hashtable<Integer, JLabel> hash2 = new Hashtable<>();
		hash2.put(1, new JLabel("1"));
		hash2.put(40, new JLabel("40"));
		hash2.put(80, new JLabel("80"));

		// Sliders to set parameters
		JSlider sliderKeyframe = new JSlider(1, 30, 10);
		sliderKeyframe.setBorder(BorderFactory.createTitledBorder("Keyframe Frequency"));
		sliderKeyframe.setPaintLabels(true);
		sliderKeyframe.setLabelTable(hash1);
		JSlider sliderRansacInliers = new JSlider(1, 30, 12);
		sliderRansacInliers.setBorder(BorderFactory.createTitledBorder("Ransac Inliers Threshold"));
		sliderRansacInliers.setPaintLabels(true);
		sliderRansacInliers.setLabelTable(hash1);
		JSlider sliderGoodMatches = new JSlider(1, 30, 15);
		sliderGoodMatches.setBorder(BorderFactory.createTitledBorder("Good Matches Threshold"));
		sliderGoodMatches.setPaintLabels(true);
		sliderGoodMatches.setLabelTable(hash1);
		JSlider sliderDistance = new JSlider(1, 80, 40);
		sliderDistance.setBorder(BorderFactory.createTitledBorder("Distance Threshold"));
		sliderDistance.setPaintLabels(true);
		sliderDistance.setLabelTable(hash2);
		
		dx.add(sliderKeyframe);
		dx.add(sliderRansacInliers);
		dx.add(sliderGoodMatches);
		dx.add(sliderDistance);
		
		dx.add(startButton);
		
		// Load all the video files in the source directory
		File dir = new File(Parameters.VIDEO_DIR);
		File[] listOfFiles = dir.listFiles(fileNameFilter);
		String[] listOfNames = new String[listOfFiles.length];
		for(int i = 0; i < listOfFiles.length; i++)
			listOfNames[i] = listOfFiles[i].getName();
		
		// List of all the video files
		JList list = new JList(listOfNames);
		list.setFixedCellWidth(200);
		list.setFixedCellHeight(20);
		list.setEnabled(true);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));
		list.setAlignmentY(BOTTOM_ALIGNMENT);
		
		sx.add(list);
		
		setSize(500, 400);
		setVisible(true);
		setResizable(false);
		setTitle("Video Object Recognition");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
