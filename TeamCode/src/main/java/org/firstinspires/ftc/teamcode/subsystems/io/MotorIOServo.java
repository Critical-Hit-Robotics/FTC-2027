package org.firstinspires.ftc.teamcode.subsystems.io;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.config.Constants.ServoIOConstants;

public class MotorIOServo implements MotorIO {

  public static class MotorIOServoConfig {
    public final double kMaximumDegrees;
    public final String kServoIdentifier;

    public MotorIOServoConfig(String kServoIdentifier) {
      this(kServoIdentifier, ServoIOConstants.kMaximumDegrees);
    }

    public MotorIOServoConfig(String kServoIdentifier, double kMaximumDegrees) {
      this.kServoIdentifier = kServoIdentifier;
      this.kMaximumDegrees = kMaximumDegrees;
    }
  }

  public final MotorIOServoConfig config;
  private final ServoEx servo;

  private double targetDegrees = -1;

  public MotorIOServo(HardwareMap hardwareMap, MotorIOServoConfig config) {
    servo = hardwareMap.get(ServoEx.class, config.kServoIdentifier);

    this.config = config;
  }

  @Override
  public void updateInputs(MotorIOInputsAutoLogged inputs) {
    inputs.targetVelocity = -1;
    inputs.rpm = -1;
    inputs.targetDegrees = targetDegrees;
    inputs.position = servo.get();
    inputs.degrees = servo.get() * config.kMaximumDegrees;
  }

  @Override
  public void setTargetAngle(double degrees) {
    double clampedDegrees = Math.max(0, Math.min(degrees, config.kMaximumDegrees));
    targetDegrees = clampedDegrees;

    // Scale our designated degrees to our maximum.
    double position = (clampedDegrees / config.kMaximumDegrees);
    servo.set(position);
  }
}
