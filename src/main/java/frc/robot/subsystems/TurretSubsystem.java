/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.controls.CustomTalon;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class TurretSubsystem extends Subsystem {

    private CustomTalon turretTal;

    public TurretSubsystem() {
        turretTal = new CustomTalon(6);
        turretTal.setInverted(true);
        turretTal.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
  }

  public void resetEncoder(){
    turretTal.setSelectedSensorPosition(0);
  }

  public double getTurretAngleX() {
    double encoderRatio = 1287.6828;
    double currentArcLength = turretTal.getSelectedSensorPosition() / encoderRatio;
    double currentAngleDegrees = (currentArcLength / 8) * (180 / Math.PI);
    return currentAngleDegrees;
  }

  public void moveTurretX(double angle) {
    if (angle > 0) {
      turretTal.set(ControlMode.PercentOutput, 1.0);
    } else {
      turretTal.set(ControlMode.PercentOutput, -1.0);
    }
  }

  public void stopTurret() {
    turretTal.set(ControlMode.PercentOutput, 0);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}