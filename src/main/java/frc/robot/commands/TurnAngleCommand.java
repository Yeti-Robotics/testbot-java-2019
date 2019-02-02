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

  private static final double THRESHOLD = 0;
  private static final double MIN_SPEED = 0.5;
  private static final double MAX_SPEED = 0.9;
  private static final double SLOW_RANGE = 35;
  private double angle;
  double targetAngle;
  double currentAngle;


  public TurnAngleCommand(double angle) {
    requires(Robot.drivetrainSubsystem);
    this.angle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrainSubsystem.resetGyro();
    // Robot.drivetrainSubsystem.enableGyroPID(angle);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double speed = MAX_SPEED;
    targetAngle = Math.abs(angle);
    currentAngle = Math.abs(Robot.drivetrainSubsystem.gyro.getAngle());
    if (targetAngle - currentAngle < SLOW_RANGE){
      speed = MIN_SPEED;
    }

    if (angle > 0){
      Robot.drivetrainSubsystem.tankDrive(speed, -speed);
    } else if (angle < 0){
      Robot.drivetrainSubsystem.tankDrive(-speed, speed);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    return currentAngle > targetAngle - THRESHOLD;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrainSubsystem.disableGyroPID();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
