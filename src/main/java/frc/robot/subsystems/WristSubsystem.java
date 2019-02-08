/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.controls.CustomTalon;

/**
 * Add your docs here.
 */
public class WristSubsystem extends Subsystem {
  
  private CustomTalon wristTal;


  public WristSubsystem(){
    wristTal = new CustomTalon(RobotMap.WRIST_TALON);

    wristTal.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

  }

  public void useWrist(double power){
    wristTal.set(ControlMode.PercentOutput, power);
  }

  public void resetWristEncoder(){
    wristTal.setSelectedSensorPosition(0);
  }

  public double getWristEncoderValue(){
    return wristTal.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
