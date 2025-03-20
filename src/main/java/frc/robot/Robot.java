// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
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
  //private SparkMax elevator1 = new SparkMax(0, MotorType.kBrushless);
  //private SparkMax elevator2 = new SparkMax(0, MotorType.kBrushless);
  //private SparkMax intake = new SparkMax(0, MotorType.kBrushless);
 // private SparkMax intakePitch = new SparkMax(0, MotorType.kBrushless);

  private Joystick joy1 = new Joystick(0);

  private double startTime;

  public Robot() {
    // Configure left side of drive.
    leftLeader = new SparkMax(1, MotorType.kBrushed);
    leftFollower = new SparkMax(2, MotorType.kBrushed);
    var configLeftFollower = new SparkMaxConfig();
    configLeftFollower.follow(leftLeader);
    leftFollower.configure(configLeftFollower, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // Configure right side of drive.
    rightLeader = new SparkMax(3, MotorType.kBrushed);
    rightFollower = new SparkMax(4, MotorType.kBrushed);
    var configRightFollower = new SparkMaxConfig();
    configRightFollower.follow(rightLeader);
    rightFollower.configure(configLeftFollower, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


  }

 
  @Override
  public void robotPeriodic() {}

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    //Autonomous + Autonomous Stop
    double time = Timer.getFPGATimestamp();
    if (joy1.getRawButtonPressed(8)) {
      leftLeader.set(0);
      leftFollower.set(0);
      rightLeader.set(0);
      rightFollower.set(0);
    } else {
      if (time - startTime < 2.7) {
        leftLeader.set(0.6);
        leftFollower.set(0.6);
        rightLeader.set(-0.6);
        rightFollower.set(-0.6);
      } else if (time - startTime < 3.63) {
        leftLeader.set(0.3);
        leftFollower.set(0.3);
        rightLeader.set(0.3);
        rightFollower.set(0.3);
      } else if (time - startTime < 4.3) {
        leftLeader.set(0.6);
        leftFollower.set(0.6);
        rightLeader.set(-0.6);
        rightFollower.set(-0.6);
      } else if (time - startTime < 6.3) {
        leftLeader.set(0.6);
        leftFollower.set(0.6);
        rightLeader.set(-0.6);
        rightFollower.set(-0.6);
      } else {
        leftLeader.set(0);
        leftFollower.set(0);
        rightLeader.set(0);
        rightFollower.set(0);
      }
    }
  }

  @Override
  public void teleopInit() {
    
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //Emergancy Stop + Contorls
    if (joy1.getRawButtonPressed(7)) {
        leftLeader.set(0);
        leftFollower.set(0);
        rightLeader.set(0);
        rightFollower.set(0);
    } else {
      double speed = -joy1.getRawAxis(1) * 0.6;
      double turn = joy1.getRawAxis(4) * -0.3;

      double left = speed + turn;
      double right = speed - turn;

      leftLeader.set(left);
      leftFollower.set(left);
      rightLeader.set(-right);
      rightFollower.set(-right);
    }

  }

  @Override
  public void testInit() {
    
  }

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
