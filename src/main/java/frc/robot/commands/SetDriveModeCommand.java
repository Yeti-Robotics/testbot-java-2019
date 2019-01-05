package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem.DriveMode;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class SetDriveModeCommand extends Command {
 
    private DriveMode driveMode;

    public SetDriveModeCommand(DriveMode driveMode) {
        this.driveMode = driveMode;
        requires(Robot.drivetrainSubsystem);
   
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrainSubsystem.setDriveMode(driveMode);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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