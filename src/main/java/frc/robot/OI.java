/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import frc.robot.commands.DriveTrainHighShiftCommand;
import frc.robot.commands.DriveTrainLowShiftCommand;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private Joystick leftJoy, rightJoy;
  
  public OI(){
  //Creates joystick objects for use
  leftJoy = new Joystick(RobotMap.LEFT_JOYSTICK);
  rightJoy = new Joystick(RobotMap.RIGHT_JOYSTICK);

    //Left joystick buttons
		setJoystickButtonWhenPressedCommand(leftJoy, 1, new DriveTrainHighShiftCommand());
		
		//Right joystick buttons
		setJoystickButtonWhenPressedCommand(rightJoy, 1, new DriveTrainLowShiftCommand());

  }

  //Gets the Y direction of the left drive joystick
	public double getLeftY() {
    return leftJoy.getY();
}

//Gets the Y direction of the left drive joystick
public double getLeftX() {
  return leftJoy.getX();
}

//Gets the Y direction of the right drive joystick
public double getRightY() {
    return rightJoy.getY();
}

//Gets the X direction of the right drive joystick
public double getRightX() {
  return rightJoy.getX();
}

private void setJoystickButtonWhenPressedCommand(GenericHID joystick, int button, Command command) {
  new JoystickButton(joystick, button).whenPressed(command);
}

};
