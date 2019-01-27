/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controls;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.hal.util.UncleanStatusException;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class JeVois {
    SerialPort jevois;
    Contour leftCon,rightCon;



    public JeVois() {
        try {
            jevois = new SerialPort(RobotMap.JEVOIS_BAUD_RATE, SerialPort.Port.kUSB);
        } catch (UncleanStatusException e) {
        }   
    }

    
    public double getLeftDistance() {
        double leftDistance = (RobotMap.TAPE_BOUND_WIDTH_INCH * RobotMap.FOCAL_LENGTH) / leftCon.w;
        return leftDistance;
    }

    public double getRightDistance() {
        double rightDistance = (RobotMap.TAPE_BOUND_WIDTH_INCH * RobotMap.FOCAL_LENGTH) / rightCon.w;
        return rightDistance;
    }

    public Contour[] parseStream() {
        String cameraOutput = jevois.readString();
        // System.out.println(cameraOutput);

        if (cameraOutput != null && !cameraOutput.trim().isEmpty()) {
            List<Contour> contours = new ArrayList<Contour>();
            String[] contourStrings = cameraOutput.split("\\|");

            // System.out.println("output: " + cameraOutput);
            // System.out.println("output 1.5: " + contourStrings.length);
            if (contourStrings.length == 2) {
                for (String contourString : contourStrings) {
                    String[] contourValues = contourString.split(",");
                    Contour contour = new Contour(contourValues[0].trim(), contourValues[1].trim(), contourValues[2].trim(),
                            contourValues[3].trim(), contourValues[4].trim());
                    // System.out.println("output2: " + contour.toString());
                    contours.add(contour);
                

                }
                Contour[] contoursArray = { contours.get(0), contours.get(1) };
                return contoursArray;
            }

        }
        return null;
    }
}
