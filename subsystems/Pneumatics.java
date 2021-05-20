// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Pneumatics extends SubsystemBase {
  private final static Compressor compressor = new Compressor();
  private final static DoubleSolenoid solenoidLift = new DoubleSolenoid(Constants.UP, Constants.DOWN);

  /** Creates a new pneumatics. */
  public Pneumatics() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

public static void Compressor(XboxController controller) {
  if(controller.getStartButton())
    {
      compressor.start();
    } 
    else if(controller.getBackButton())
    {
      compressor.stop();
    }  
    
    }

public static void Lift(XboxController controller) {
  int direction = controller.getPOV(0);

      if(direction == Constants.DPAD_UP)
      {
        solenoidLift.set(DoubleSolenoid.Value.kForward);
      } 
      else if(direction == Constants.DPAD_DOWN)
      {
        solenoidLift.set(DoubleSolenoid.Value.kReverse);
      }
    }
}