// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;


/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
  private SparkMax leftLeader;
  private SparkMax leftFollower;
  private SparkMax rightLeader;
  private SparkMax rightFollower;
  private SparkMax coralIntake;
  private DifferentialDrive drive;
  private XboxController driverController = new XboxController(0);
  private XboxController operatorController = new XboxController(1);
  

  public Robot() {
    leftLeader = new SparkMax(1, MotorType.kBrushed);
    leftFollower = new SparkMax(2, MotorType.kBrushed);
    rightLeader = new SparkMax(3, MotorType.kBrushed);
    rightFollower = new SparkMax(4, MotorType.kBrushed);
    coralIntake = new SparkMax(5, MotorType.kBrushless);
    drive = new DifferentialDrive(rightLeader::set, leftLeader::set);

    SparkMaxConfig globalConfig = new SparkMaxConfig();
    SparkMaxConfig rightLeaderConfig = new SparkMaxConfig();
    SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();
    SparkMaxConfig rightFollowerConfig = new SparkMaxConfig();

    globalConfig
        .smartCurrentLimit(50)
        .idleMode(IdleMode.kBrake);

    rightLeaderConfig
      .apply(globalConfig)
      .inverted(true);

    leftFollowerConfig
      .apply(globalConfig)
      .follow(leftLeader);

    rightFollowerConfig 
      .apply(globalConfig)
      .follow(rightLeader);

    leftLeader.configure(globalConfig,
      ResetMode.kResetSafeParameters, 
      PersistMode.kPersistParameters);
    rightLeader.configure(rightLeaderConfig,
      ResetMode.kResetSafeParameters, 
      PersistMode.kPersistParameters);
    leftFollower.configure(leftFollowerConfig, 
      ResetMode.kResetSafeParameters, 
      PersistMode.kPersistParameters);
    rightFollower.configure(rightFollowerConfig, 
      ResetMode.kResetSafeParameters, 
      PersistMode.kPersistParameters);
  }

 
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Left Out", leftLeader.getAppliedOutput());
    SmartDashboard.putNumber("Right Out", rightLeader.getAppliedOutput());
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    double autoTime = Timer.getMatchTime();
    if (autoTime < 2) {
      drive.arcadeDrive(0.5, 0);
    } else if (autoTime < 5) {
      coralIntake.set(-0.5);
    } else {
      coralIntake.set(0);
    }
    
  }
    

  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    drive.arcadeDrive(-driverController.getLeftY(), -driverController.getRightX());

    if (operatorController.getLeftTriggerAxis() > 0.5) {
      coralIntake.set(-0.05);
    } else if(operatorController.getRightTriggerAxis() > 0.5) {
      coralIntake.set(-0.5);
    } else {
      coralIntake.set(0);

    }
    
    
    
}

  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
