/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class UserDriveCommand extends Command {
  public UserDriveCommand() {
      requires(Robot.drivetrainSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    switch (Robot.drivetrainSubsystem.getDriveMode()) {
      case TANK: 
          Robot.drivetrainSubsystem.tankDrive(-Robot.oi.getDriverstationLeftY(), -Robot.oi.getDriverstationRightY());
       break;  
      case ARCADE: 
//    	        Robot.drivetrainSubsystem.arcadeDrive(-Robot.oi.getRightX(), Robot.oi.getLeftY());
            Robot.drivetrainSubsystem.arcadeDrive(-Robot.oi.getLeftX(), Robot.oi.getLeftY());
         break;
      case CHEEZY:
//    	        Robot.drivetrainSubsystem.cheezyDrive(-Robot.oi.getRightX(), Robot.oi.getLeftY());
            Robot.drivetrainSubsystem.cheezyDrive(-Robot.oi.getLeftX(), Robot.oi.getLeftY());
         break;
  }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
