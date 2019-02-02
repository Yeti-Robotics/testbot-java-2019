/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.controls.Contour;
import frc.robot.controls.VisionProcessor;

public class TurnToTargetCommandGroup extends CommandGroup {
  

  Contour[] contours;
  double azimuth;
  TurnAngleCommand turnAngleCommand;
  /**
   * Add your docs here.
   */
  public TurnToTargetCommandGroup() {
    setTimeout(0.5);
  }

  @Override
  protected void initialize() {
    contours = Robot.jevois.parseStream();
    if(contours != null){
      contours = Robot.contourList.get(Robot.contourList.size()-1);
      double azimuth = VisionProcessor.getAzimuth(contours[0], contours[1]);
      System.out.println(azimuth);
      turnAngleCommand = new TurnAngleCommand(azimuth);
      turnAngleCommand.start();
    } else {
      this.cancel();
    }

  }

  @Override
  protected boolean isFinished() {
    return turnAngleCommand.isFinished() || isTimedOut();
  }
}
