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
 public static final double PULSES_PER_REVOLUTION = 128;
 public static final double DISTANCE_PER_PULSE = Math.PI * WHEEL_DIAMETER / PULSES_PER_REVOLUTION;

  // Joysticks
  public static final int LEFT_JOYSTICK = 0;
  public static final int RIGHT_JOYSTICK = 1;
  public static final int SECONDARY_JOYSTICK = 2;

  // Climber Constants
  public static final double CLIMBER_SPEED = 0.75;
 
  // Drive train sparks
	public static final int LEFT_1_SPARK = 2;
	public static final int LEFT_2_SPARK = 1;
	public static final int Right_1_SPARK = 5;
  public static final int RIGHT_2_SPARK = 4;
  
  // Drive train talons
  public static final int LEFT_Drive_TALON = 5;
  public static final int RIGHT_Drive_TALON = 6;

  public static final int ELEVATOR_TALON = 7;
  public static final double ELEVATOR_TAL_SPEED = 0.7;

  public static final int ROLLER_VICTORSPX = 8;
  public static final double ROLLER_VICTOR_SPEED = 0.7; 
  // Othern Talons
  public static final int Rack_TALON = 2;
  public static final int CLIMBER_TALON = 3;
  public static final int WRIST_TALON = 4;
 
  // Victors
  public static final int CLIMBER_VICTOR = 2;
  
  // Encoders
  public static final int[] DRIVE_LEFT_ENCODER = { 7, 8 };
  public static final int[] DRIVE_RIGHT_ENCODER = { 5, 6 };
  public static final double ROBOT_RADIUS = 12.0;

  // Drive train solenoid
  public static final int[] DRIVE_TRAIN_SHIFT = { 1, 2 };
  
  // Panel solenoids
  public static final int[] INTAKE_SOLENOID = {6, 7};
  public static final int[] DEPLOY_SOLENOID = {5, 4};

  // Hatch Panel Limit Switches
  public static final int LEFT_HATCH_PANEL_LIMIT = 1;
  public static final int RIGHT_HATCH_PANEL_LIMIT = 0;

  // Climber Limit Switches
  public static final int UPPER_LIMIT = 2;
  public static final int LOWER_LIMIT = 3;

  // Vision Constants
  public static final int IMAGE_WIDTH = 320;
  // public static final double FOCAL_LENGTH = IMAGE_WIDTH / (2 * Math.tan(32.5));
  public static final double TAPE_BOUND_WIDTH_INCH = 3.3;
  public static final double FOCAL_LENGTH = (21 * 49.5) / TAPE_BOUND_WIDTH_INCH;
  public static final int JEVOIS_BAUD_RATE = 115200;
  public static final int FOV = 65;
  

  // Line Following Constants
  public static final int LEFT_LINE_FOLLOW = 4;
  public static final int CENTER_LINE_FOLLOW = 3;
  public static final int RIGHT_LINE_FOLLOW = 2;
}
