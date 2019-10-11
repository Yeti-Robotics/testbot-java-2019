package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class MoveTurretCommand extends Command {
  
    private double angle;
  
    public MoveTurretCommand(double angle) {
    // Use requires() here to declare subsystem dependencies
        requires(Robot.turretBaseSubsystem);
        this.angle = angle;
        
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.turretBaseSubsystem.resetEncoder();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.turretBaseSubsystem.moveTurret(angle);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.turretBaseSubsystem.getTurretAngle() >= Math.abs(angle);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.turretBaseSubsystem.stopTurret();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}