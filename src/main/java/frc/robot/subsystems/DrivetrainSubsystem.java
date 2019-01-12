package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.UserDriveCommand;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class DrivetrainSubsystem extends Subsystem {

    private Spark left1, left2, right1, right2;
    private TalonSRX leftTal, rightTal;
    private Encoder leftEnc, rightEnc;
    private DifferentialDrive differentialDrive;
    private DriveMode driveMode;


    public enum DriveMode {
	    TANK, ARCADE, CHEEZY;
	}

    public DrivetrainSubsystem() {

        left1 = new Spark(frc.robot.RobotMap.LEFT_1_SPARK);
		left2 = new Spark(frc.robot.RobotMap.LEFT_2_SPARK);
		right1 = new Spark(frc.robot.RobotMap.Right_1_SPARK);
        right2 = new Spark(frc.robot.RobotMap.RIGHT_2_SPARK);
        leftTal = new TalonSRX(frc.robot.RobotMap.LEFT_Drive_TALON);
        rightTal = new TalonSRX(frc.robot.RobotMap.RIGHT_Drive_TALON);

        
        SpeedControllerGroup leftSparks = new SpeedControllerGroup(left1, left2);
        SpeedControllerGroup rightSparks = new SpeedControllerGroup(right1, right2);
        
        
        
        differentialDrive = new DifferentialDrive(leftSparks, rightSparks);

        // Creates encoder objects connected to their respective DIO ports
		leftEnc = new Encoder(frc.robot.RobotMap.DRIVE_LEFT_ENCODER[0], frc.robot.RobotMap.DRIVE_LEFT_ENCODER[1], true, EncodingType.k4X);
		rightEnc = new Encoder(frc.robot.RobotMap.DRIVE_RIGHT_ENCODER[0], frc.robot.RobotMap.DRIVE_RIGHT_ENCODER[1], true,
                EncodingType.k4X);
                
        leftEnc.setDistancePerPulse(frc.robot.RobotMap.DISTANCE_PER_PULSE);
        leftEnc.setPIDSourceType(PIDSourceType.kDisplacement);
        rightEnc.setDistancePerPulse(frc.robot.RobotMap.DISTANCE_PER_PULSE);
        rightEnc.setPIDSourceType(PIDSourceType.kDisplacement);

        leftTal.setInverted(true);
        left1.setInverted(true);
        left2.setInverted(true);
        
        SmartDashboard.putNumber("Left drive distance", getLeftEncoderValue());
		SmartDashboard.putNumber("Right drive distance", getRightEncoderValue());
		
		driveMode = DriveMode.TANK;

    };

    public DriveMode getDriveMode() {
        return driveMode;
    }

    public void setDriveMode(DriveMode driveMode) {
        this.driveMode = driveMode;
    }

    // Resets the encoder values
	public void resetEncoders() {
		rightEnc.reset();
		leftEnc.reset();
    }
    
    // Prints drive encoder values to the console
	public void printEncoders() {
	    System.out.println(rightEnc.getDistance() + ", " + leftEnc.getDistance());
    }
    
    public double getRightEncoderValue() {
		return rightEnc.getDistance();
    }
    
    public double getLeftEncoderValue() {
		return leftEnc.getDistance();
    }
    
    public double getAvgEncoderDistance() {
		return (Math.abs(getRightEncoderValue()) + Math.abs(getLeftEncoderValue())) / 2;
    }
    
    // Controls the right side of the drive train
	public void moveDriveTrainRight(double power) {
		right1.set(power);
        right2.set(power);
        rightTal.set(ControlMode.PercentOutput, power);
        
    }
    
    // Controls the left side of the drive train
	public void moveDriveTrainLeft(double power) {
		left1.set(power);
        left2.set(power);
        leftTal.set(ControlMode.PercentOutput, power);
    }
    
    public void tankDrive(double left, double right) {
		differentialDrive.tankDrive(-left, right);
    }
    
    public void arcadeDrive(double forward, double rotation) {
	    differentialDrive.arcadeDrive(forward, rotation);
	}
	
	public void cheezyDrive(double forward, double rotation) {
	    differentialDrive.curvatureDrive(forward, rotation, true);
    }
    
    public void initDefaultCommand() {
		setDefaultCommand(new UserDriveCommand());
    }
    
    public int getRightPulsesPerRevolution() {
        return rightEnc.getRaw();
    }

    public int getLeftPulsesPerRevolution() {
        return leftEnc.getRaw();
    }

};