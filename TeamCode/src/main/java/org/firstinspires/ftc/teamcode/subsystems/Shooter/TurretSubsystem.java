package org.firstinspires.ftc.teamcode.subsystems.Shooter;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Subsystem;
import com.seattlesolvers.solverslib.controller.PIDController;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.teamcode.config.Constants.ShooterConstants.TurretConstants;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.io.MotorIOGoBuilda;
import org.firstinspires.ftc.teamcode.subsystems.vision.VisionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.io.MotorIO;
import org.firstinspires.ftc.teamcode.subsystems.io.MotorIOInputsAutoLogged;
import org.firstinspires.ftc.teamcode.subsystems.io.MotorIOServo;
import org.firstinspires.ftc.teamcode.subsystems.io.MotorIOServo.MotorIOServoConfig;
import org.firstinspires.ftc.teamcode.util.PoseMath;

import java.util.function.DoubleSupplier;

public class TurretSubsystem implements Subsystem {
  public enum TurretState {
    TARGETING,
    IDLE
  }

  private TurretState state;

  private final MotorIOGoBuilda turretAzimuth;
  private final MotorIOInputsAutoLogged inputs = new MotorIOInputsAutoLogged();
  private double targetDegrees = 0;

  PIDController controller =
      new PIDController(
          TurretConstants.azimuthPIDCoefficients.p,
          TurretConstants.azimuthPIDCoefficients.i,
          TurretConstants.azimuthPIDCoefficients.d);

  public TurretSubsystem(HardwareMap hardwareMap, DoubleSupplier voltageSupplier) {

    turretAzimuth =
        new MotorIOGoBuilda(
            hardwareMap,
            TurretConstants.azimuthMotorKey,
            voltageSupplier,
            TurretConstants.kAzimuthTicksToDegrees);
    this.state = TurretState.TARGETING;
  }

  public void setAngle(double degrees) {
    if (!TurretConstants.isValidAngle(degrees)) {
      return;
    }

    double desiredAngle =
        AngleUnit.normalizeDegrees(targetDegrees) * TurretConstants.motorToAzimuthGearRatio;

    this.targetDegrees = desiredAngle;
    controller.setSetPoint(desiredAngle);
  }

  @Override
  public void periodic() {
    turretAzimuth.updateInputs(inputs);

    switch (state) {
      case IDLE:
        break;
      case TARGETING:
        targetAngle();
        break;
    }
  }

  private void targetAngle() {
    double currentAngle = inputs.position / (double) TurretConstants.kAzimuthTicksToDegrees;
    double error = controller.calculate(currentAngle);

    // May have to account for battery sag
    turretAzimuth.setPower(error);
  }

  public void setState(TurretState state) {
    this.state = state;
  }
}
