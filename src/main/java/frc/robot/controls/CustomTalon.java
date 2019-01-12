/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controls;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Add your docs here.
 */
public class CustomTalon extends TalonSRX implements SpeedController {

    private double currentSpeed;

    public CustomTalon(int channel) {
        super(channel);
    }

    @Override
    public void pidWrite(double output) {
        
    }

    @Override
    public void set(double speed) {
        currentSpeed = speed;
        super.set(ControlMode.PercentOutput, currentSpeed);
    }

    @Override
    public double get() {
        return currentSpeed;
    }

    @Override
    public void disable() {
        set(0);
    }

    @Override
    public void stopMotor() {
        set(0);
    }
}
