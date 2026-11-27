package org.firstinspires.ftc.teamcode.subsystems.io;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import Ori.Coval.Logging.AutoLog;

public interface MotorIO {
  @AutoLog
  public class MotorIOInputs {
    public double position;
    public double degrees;
    public double rpm;

    public double targetVelocity;
    public double targetDegrees;
  }

  public void updateInputs(MotorIOInputsAutoLogged inputs);

  public void setTargetAngle(double degrees);

  public default void setTargetVelocity(double rotationsPerMinute) {
    throw new UnsupportedOperationException("Cannot set velocity on this class.");
  }
}
