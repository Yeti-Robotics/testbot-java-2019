/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class WristRollerBarSubsystem extends Subsystem {
  private VictorSPX wristRollerVictor;

  public WristRollerBarSubsystem() {
    wristRollerVictor = new VictorSPX(RobotMap.ROLLER_VICTORSPX);
  }

  public void rollIn() {
    wristRollerVictor.set(ControlMode.PercentOutput, RobotMap.ROLLER_VICTOR_SPEED);
  }

  public void rollOut() {
    wristRollerVictor.set(ControlMode.PercentOutput, RobotMap.ROLLER_VICTOR_SPEED);
  }

  public void rollStop() {
    wristRollerVictor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
