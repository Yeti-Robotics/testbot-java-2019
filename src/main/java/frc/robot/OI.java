/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.DeployHatchPanelCommandGroup;
import frc.robot.commands.DriveForDistanceCommand;
import frc.robot.commands.DriveForDistancePIDCommand;
import frc.robot.commands.DriveToHatchPanelCommandGroup;
import frc.robot.commands.DriveTrainHighShiftCommand;
import frc.robot.commands.DriveTrainLowShiftCommand;
import frc.robot.commands.LineFollowCommand;
import frc.robot.commands.MoveTurretCommand;
import frc.robot.commands.ResetEncodersCommand;
import frc.robot.commands.RunVisionThreadCommand;
import frc.robot.commands.TurnAngleCommand;
import frc.robot.commands.TurnToTargetCommandGroup;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private Joystick leftJoy, rightJoy, driverstationJoy, secondaryJoy;

  public OI() {
    // Creates joystick objects for use
    leftJoy = new Joystick(RobotMap.LEFT_JOYSTICK);
    rightJoy = new Joystick(RobotMap.RIGHT_JOYSTICK);
    driverstationJoy = new Joystick(RobotMap.LEFT_JOYSTICK);
    secondaryJoy = new Joystick(RobotMap.SECONDARY_JOYSTICK);

    // Left joystick buttons
    setJoystickButtonWhenPressedCommand(driverstationJoy, 11, new DriveTrainHighShiftCommand());

    // Right joystick buttons
    setJoystickButtonWhenPressedCommand(driverstationJoy, 12, new DriveTrainLowShiftCommand());

    // Secondary Joystick Buttons
    setJoystickButtonWhenPressedCommand(driverstationJoy, 1, new MoveTurretCommand(45));   
    setJoystickButtonWhenPressedCommand(driverstationJoy, 2, new MoveTurretCommand(-45));
    // setJoystickButtonWhenPressedCommand(secondaryJoy, 1, new LineFollowCommand());
    // setJoystickButtonWhenPressedCommand(secondaryJoy, 5, new ResetEncodersCommand());
    // // setJoystickButtonWhenPressedCommand(secondaryJoy, 4, new CorrectAzimuthCommand());
    // setJoystickButtonWhenPressedCommand(secondaryJoy, 6, new TurnAngleCommand(-90));
    // setJoystickButtonWhenPressedCommand(secondaryJoy, 7, new DriveForDistanceCommand(50, 0.6, 0.6));
    // setJoystickButtonWhenPressedCommand(secondaryJoy, 8, new TurnToTargetCommandGroup());
    // setJoystickButtonWhenPressedCommand(secondaryJoy, 11, new DriveTrainLowShiftCommand());
    // setJoystickButtonWhenPressedCommand(secondaryJoy, 12, new DriveTrainHighShiftCommand());
    // setJoystickButtonWhenPressedCommand(driverstationJoy, 3, new DriveToHatchPanelCommandGroup());
  }

  // Gets the Y direction of the left drive joystick
  public double getLeftY() {
    return leftJoy.getY();
  }

  // Gets the Y direction of the left drive joystick
  public double getLeftX() {
    return leftJoy.getX();
  }

  // Gets the Y direction of the right drive joystick
  public double getRightY() {
    return rightJoy.getY();
  }

  // Gets the X direction of the right drive joystick
  public double getRightX() {
    return rightJoy.getX();
  }

  public double getDriverstationLeftY() {
    return driverstationJoy.getRawAxis(1);
  }

  public double getDriverstationRightY() {
    return driverstationJoy.getRawAxis(3);
  }



  private void setJoystickButtonWhenPressedCommand(GenericHID joystick, int button, Command command) {
    new JoystickButton(joystick, button).whenPressed(command);
  }

  private void setJoystickButtonWhileHeldCommand(GenericHID joystick, int button, Command command) {
    new JoystickButton(joystick, button).whileHeld(command);
  }

};
