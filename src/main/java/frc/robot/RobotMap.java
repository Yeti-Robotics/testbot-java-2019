/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
 
 // Physical Constants
 public static final double WHEEL_DIAMETER = 5.875; // inches
 public static final double PULSES_PER_REVOLUTION = 512;
 public static final double DISTANCE_PER_PULSE = Math.PI * WHEEL_DIAMETER / PULSES_PER_REVOLUTION;

  // Joysticks
  public static final int LEFT_JOYSTICK = 0;
  public static final int RIGHT_JOYSTICK = 1;
 
  // Drive train sparks
	public static final int LEFT_1_SPARK = 2;
	public static final int LEFT_2_SPARK = 1;
	public static final int Right_1_SPARK = 5;
  public static final int RIGHT_2_SPARK = 4;
  
  // Drive train talons
  public static final int LEFT_Drive_TALON = 5;
  public static final int RIGHT_Drive_TALON = 6;
  
  // Encoders
  public static final int[] DRIVE_LEFT_ENCODER = { 1, 0 };
  public static final int[] DRIVE_RIGHT_ENCODER = { 2, 3 };

  // Drive train solenoid
	public static final int[] DRIVE_TRAIN_SHIFT = { 1, 2 };
}
