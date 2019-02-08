/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.controls.CustomTalon;

/**
 * Add your docs here.
 */
public class RackPinionSubsystem extends Subsystem {
 
  private CustomTalon RackTal;


  public RackPinionSubsystem(){
    RackTal = new CustomTalon(RobotMap.Rack_TALON);

    RackTal.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

  }

  public void useWrist(double power){
    RackTal.set(ControlMode.PercentOutput, power);
  }

  public void resetWristEncoder(){
    RackTal.setSelectedSensorPosition(0);
  }

  public double getWristEncoderValue(){
    return RackTal.getSelectedSensorPosition();
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
