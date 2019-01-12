package frc.robot.autoRoutines;

import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.robot.commands.DriveForDistanceCommand;
import frc.robot.commands.DriveTrainLowShiftCommand;
import frc.robot.commands.DriveTrainHighShiftCommand;

public class DriveForwardAuto extends CommandGroup {

    public DriveForwardAuto() {
        addSequential(new DriveTrainLowShiftCommand());
        addSequential(new DriveForDistanceCommand(30, 0.5, .5));
        addSequential(new DriveTrainHighShiftCommand());
        addSequential(new DriveForDistanceCommand(30, 0.5, .5));

    }
}