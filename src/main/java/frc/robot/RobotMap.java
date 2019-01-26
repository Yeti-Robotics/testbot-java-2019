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
  public static final int SECONDARY_JOYSTICK = 2;
 
  // Drive train sparks
	public static final int LEFT_1_SPARK = 2;
	public static final int LEFT_2_SPARK = 1;
	public static final int Right_1_SPARK = 5;
  public static final int RIGHT_2_SPARK = 4;
  
  // Drive train talons
  public static final int LEFT_Drive_TALON = 5;
  public static final int RIGHT_Drive_TALON = 6;
  
  // Encoders
  public static final int[] DRIVE_LEFT_ENCODER = { 4, 5 };
  public static final int[] DRIVE_RIGHT_ENCODER = { 2, 3 };

  // Drive train solenoid
  public static final int[] DRIVE_TRAIN_SHIFT = { 1, 2 };
  
  // Panel solenoids
  public static final int[] INTAKE_SOLENOID = {6, 7};
  public static final int[] DEPLOY_SOLENOID = {5, 4};

  // Hatch Panel Limit Switches
  public static final int LEFT_HATCH_PANEL_LIMIT = 1;
  public static final int RIGHT_HATCH_PANEL_LIMIT = 0;

  // Vision Constants
  public static final double FOCAL_LENGTH = 320 / (2 * Math.tan(32.5));
  public static final double TAPE_BOUND_WIDTH_INCH = 3.3;
  public static final int JEVOIS_BAUD_RATE = 115200;
}
