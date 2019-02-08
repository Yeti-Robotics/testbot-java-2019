/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.controls.CustomTalon;

/**
 * Add your docs here.
 */
public class ElevatorSubsystem extends Subsystem {
  private CustomTalon elevatorTalon;

  public ElevatorSubsystem() {
    elevatorTalon = new CustomTalon(RobotMap.ELEVATOR_TALON);
    elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }

  public void elevatorUp() {
    elevatorTalon.set(RobotMap.ELEVATOR_TAL_SPEED);
  }

  public void elevatorDown() {
    elevatorTalon.set(-RobotMap.ELEVATOR_TAL_SPEED);
  }

  public void elevatorStop() {
    elevatorTalon.set(0);
  }

  public void resetElevatorEncoder() {
    elevatorTalon.setSelectedSensorPosition(0);
  }

  public double getElevatorEncoder() {
    return elevatorTalon.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
