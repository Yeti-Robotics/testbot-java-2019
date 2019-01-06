package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveForDistanceCommand extends Command {
  
    private double distance, powerL, powerR;
  
    public DriveForDistanceCommand(double distance, double powerL, double powerR) {
    // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrainSubsystem);
        this.distance = distance;
        this.powerL = powerL;
        this.powerR = powerR;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrainSubsystem.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drivetrainSubsystem.tankDrive(powerL, powerR);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.drivetrainSubsystem.getAvgEncoderDistance() >= distance;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrainSubsystem.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}