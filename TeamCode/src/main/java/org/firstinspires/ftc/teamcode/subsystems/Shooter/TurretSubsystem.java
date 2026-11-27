package org.firstinspires.ftc.teamcode.subsystems.Shooter;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Subsystem;
import com.seattlesolvers.solverslib.controller.PIDController;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.config.Constants.ShooterConstants.TurretConstants;
import org.firstinspires.ftc.teamcode.subsystems.vision.VisionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.io.MotorIO;
import org.firstinspires.ftc.teamcode.subsystems.io.MotorIOInputsAutoLogged;
import org.firstinspires.ftc.teamcode.subsystems.io.MotorIOServo;
import org.firstinspires.ftc.teamcode.subsystems.io.MotorIOServo.MotorIOServoConfig;

import java.util.function.DoubleSupplier;

public class TurretSubsystem implements Subsystem {
  public enum TurretState {
    TARGETING,
    IDLE
  }

  VisionSubsystem visionSubsystem;

  private final DcMotorEx turretAzimuthEncoder;

  private double targetDegrees = 0;

  PIDController controller =
      new PIDController(
          TurretConstants.azimuthPIDCoefficients.p,
          TurretConstants.azimuthPIDCoefficients.i,
          TurretConstants.azimuthPIDCoefficients.d);

  public TurretSubsystem(HardwareMap hardwareMap, DoubleSupplier voltageSupplier) {
    turretAzimuthEncoder = hardwareMap.get(DcMotorEx.class, TurretConstants.azimuthEncoder);
  }

  public void setVoltage(double volts) {}

  public void setAngle(double degrees) {
    if (!TurretConstants.isValidAngle(degrees)) {
      return;
    }

    double currentAngle =
        turretAzimuthEncoder.getCurrentPosition() / (double) TurretConstants.kEncoderTicksToDegrees;
    double desiredAngle =
        AngleUnit.normalizeDegrees(degrees) * TurretConstants.servoToAzimuthGearRatio;
  }

  @Override
  public void periodic() {}

  public void setState(TurretState state) {}
}
