/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controls;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class JeVois {
    SerialPort jevois;
    public JeVois() {
        jevois = new SerialPort(RobotMap.JEVOIS_BAUD_RATE, SerialPort.Port.kUSB);
    }
    public Contour[] parseStream() {
        String cameraOutput = jevois.readString();
            System.out.println(cameraOutput);
            if (cameraOutput != null && !cameraOutput.isEmpty()) {
              List<Contour> contours = new ArrayList<Contour>();
              String[] contourStrings = cameraOutput.split("\\|");

            //   System.out.println("output: " + cameraOutput);

              for (String contourString : contourStrings) {
                contourString = contourString.replace("\n", "");
                System.out.println(contourString);
                String[] contourValues = contourString.split(",");
                Contour contour = new Contour(contourValues[0], contourValues[1], contourValues[2], contourValues[3],
                    contourValues[4]);
                System.out.println("output2: " + contour.toString());
                contours.add(contour);
              
              }
              Contour[] contoursArray = {
                  contours.get(0), contours.get(1)
              };
              return contoursArray;
            }
            return null;
    }
}
