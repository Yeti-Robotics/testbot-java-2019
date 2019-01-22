/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controls;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;

import edu.wpi.first.vision.VisionPipeline;

/**
 * Add your docs here.
 */
public class JevoisPipeline implements VisionPipeline {

    @Override
    public void process(Mat image) {
        // String cameraOutput = jevois.readString();
        // // System.out.println(cameraOutput);
        // if(cameraOutput != null && !cameraOutput.isEmpty()) {
        //   List<Contour> contours = new ArrayList<Contour>();
        //   String[] contourStrings = cameraOutput.split("\\|");
    
        //   System.out.println("output: " + cameraOutput);
          
        //   for (String contourString : contourStrings) {
        //     contourString = contourString.replace("\n", "");
        //     System.out.println(contourString);
        //     String[] contourValues = contourString.split(",");
        //     // if (contourValues.length == 5) {
        //       Contour contour = new Contour(contourValues[0], contourValues[1], contourValues[2],
        //        contourValues[3], contourValues[4]);
        //       contours.add(contour);
        //       System.out.println("output2: " + contour.toString());
    
        //     // }
        //   }
          
        //     VisionProcessor visionProcessor = new VisionProcessor(contours.get(0), contours.get(1));
    
        //   System.out.println(visionProcessor.getLeftDistance() + "," + visionProcessor.getRightDistance());
        //   System.out.println(contours.toString());
    
        // }
    
		
	}
}
