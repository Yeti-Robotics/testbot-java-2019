/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controls;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * Add your docs here.
 */
public class CustomTalon extends TalonSRX implements SpeedController, Sendable {

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

    @Override
    public String getName() {
        return CustomTalon.class.getSimpleName();
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getSubsystem() {
        return null;
    }

    @Override
    public void setSubsystem(String subsystem) {

    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("speed", () -> get(), d -> set(d));
	}
}
