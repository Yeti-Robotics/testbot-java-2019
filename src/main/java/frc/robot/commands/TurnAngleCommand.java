/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class TurnAngleCommand extends Command {

  private static final double THRESHOLD = 0;
  private static final double MIN_SPEED = 0.6;
  private static final double MAX_SPEED = 0.9;
  private static final double SLOW_RANGE = 35;
  private static final double P = 0.015;
  private static final double I = 0.0002;
  private double angle;
  double targetAngle;
  double currentAngle;
  double totalError = 0;


  public TurnAngleCommand(double angle) {
    requires(Robot.drivetrainSubsystem);
    this.angle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrainSubsystem.resetGyro();
    

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  
    targetAngle = Math.abs(angle);
    currentAngle = Math.abs(Robot.drivetrainSubsystem.gyro.getAngle());
   
    double error = targetAngle - currentAngle;
    totalError += error;
    double output = (P * error) + I * totalError;
    SmartDashboard.putNumber("output", output);
    if (output < MIN_SPEED){
      output = MIN_SPEED;
    }
    if (angle > 0){
      Robot.drivetrainSubsystem.tankDrive(output, -output);
    } else if (angle < 0){
      Robot.drivetrainSubsystem.tankDrive(-output, output);
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
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
