package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class TankDrive extends Command {

    private final double deadzone = .1; 
    DifferentialDrive d;
    Supplier<Double> leftPower, rightPower; 
    private DriveTrain driveTrain; 

    public TankDrive(DriveTrain driveTrain, Supplier<Double> left, Supplier<Double> right) { 
        leftPower = left; 
        rightPower = right; 
        this.driveTrain = driveTrain; 
        addRequirements();
    }

    @Override
    public void initialize() {
        d = new DifferentialDrive(value -> driveTrain.lMotors(value) , value2 -> driveTrain.rMotors(value2)); 
        
        // Robot.driveTrain.setMotors(0, 0);

    }

    @Override
    public void execute() {
        // Scaling joysticks to limit max speed
    	// double leftPower = Robot.JOYSTICK.getLeftX;
        // double rightPower = Robot.JOYSTICK.   
        double l = leftPower.get(); 
        double r = rightPower.get(); 
        d.arcadeDrive(l, r);
    }

    @Override
    public boolean isFinished() {
        return false; 
    }

    
}
