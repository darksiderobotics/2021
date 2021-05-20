// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  CANSparkMax LeftFront;
  CANSparkMax RightFront;
  CANSparkMax LeftBack;
  CANSparkMax RightBack;
  SpeedControllerGroup leftMotors;
  SpeedControllerGroup rightMotors;
  DifferentialDrive drive;
  private final AnalogInput rangeFinder;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    LeftFront = new CANSparkMax(Constants.LEFT_FRONT, MotorType.kBrushless);
    LeftFront.setInverted(false);
    RightFront = new CANSparkMax(Constants.RIGHT_FRONT, MotorType.kBrushless);
    RightFront.setInverted(false);
    LeftBack = new CANSparkMax(Constants.LEFT_BACK, MotorType.kBrushless);
    LeftBack.setInverted(false);
    RightBack = new CANSparkMax(Constants.RIGHT_BACK, MotorType.kBrushless);
    RightBack.setInverted(false);

    leftMotors = new SpeedControllerGroup(LeftFront, LeftBack);
    rightMotors = new SpeedControllerGroup(RightFront, RightBack);
    drive = new DifferentialDrive(leftMotors, rightMotors);

    rangeFinder = new AnalogInput(Constants.RANGE_FINDER);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  
  }
  public void driveWithJoysticks(XboxController controller, double speed) {
    drive.arcadeDrive(controller.getRawAxis(Constants.XBOX_LEFT_Y_AXIS)*speed, controller.getRawAxis((int) Constants.XBOX_LEFT_X_AXIS)*speed);  
  }
  public void driveForward(double speed) {
    drive.tankDrive(speed, speed);
  }

  public boolean driveToDistance(double setPointDistance, double speed) {
    while(rangeFinder.getAverageVoltage() > setPointDistance)
    {
    driveForward(speed);
    }
    return true;
  }
  public void Stop() {
    drive.stopMotor();
  }
}
