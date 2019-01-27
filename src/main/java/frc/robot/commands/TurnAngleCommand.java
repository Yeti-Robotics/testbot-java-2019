/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class TurnAngleCommand extends Command {

  private double angle;
  private double arcLength;

  public TurnAngleCommand(double angle) {
    requires(Robot.drivetrainSubsystem);
    this.angle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrainSubsystem.resetEncoders();
    arcLength = (2.0 * Math.PI * RobotMap.ROBOT_RADIUS) * (angle / 360.0);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (angle > 0) {
      Robot.drivetrainSubsystem.tankDrive(0.5, -0.5);
    } else if (angle < 0) {
      Robot.drivetrainSubsystem.tankDrive(-0.5, 0.5);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    return (Math.abs(Robot.drivetrainSubsystem.getLeftEncoderValue()) == Math.abs(arcLength));
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
