package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.TankDrive;

public class DriveTrain extends SubsystemBase {
    SparkMax leftMotor1 = new SparkMax(1,MotorType.kBrushed); 
    SparkMax leftMotor2 = new SparkMax(2, MotorType.kBrushed); 
    SparkMax rightMotor1 = new SparkMax(3, MotorType.kBrushed); 
    SparkMax rightMotor2 = new SparkMax(4, MotorType.kBrushed); 

    public void initDefaultCommand() { 
        // setDefaultCommand(new TankDrive());
    }

    public void setMotors(double left, double right) { 
        leftMotor1.set(left);
        leftMotor2.set(left);
        rightMotor1.set(right);
        rightMotor2.set(right); 
    }

    public void lMotors(double speed){ 
        leftMotor1.set(speed);
        leftMotor2.set(speed);
    }
     
    public void rMotors(double speed) { 
        rightMotor1.set(speed);
        rightMotor2.set(speed);
    }

}
