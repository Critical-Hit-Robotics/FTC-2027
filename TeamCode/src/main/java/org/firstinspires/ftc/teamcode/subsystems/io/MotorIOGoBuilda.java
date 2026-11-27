package org.firstinspires.ftc.teamcode.subsystems.io;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.config.Constants.GoBuildaMotorConstants;

import java.util.function.DoubleSupplier;

public class MotorIOGoBuilda implements MotorIO {
  private final DcMotorEx motor;
  private final DoubleSupplier voltage;
  private final double motorTicksToDegrees;

  private double targetDegrees = 0;
  private double targetRotationsPerMinute = 0;

  // initializes a motor with default motor ticks to degrees
  public MotorIOGoBuilda(HardwareMap hardwareMap, String identifier, DoubleSupplier voltage) {
    this(hardwareMap, identifier, voltage, GoBuildaMotorConstants.motorTicksToDegrees);
  }

  public MotorIOGoBuilda(
      HardwareMap hardwareMap,
      String identifier,
      DoubleSupplier voltage,
      double motorTicksToDegrees) {
    this.motorTicksToDegrees = motorTicksToDegrees;
    motor = hardwareMap.get(DcMotorEx.class, identifier);
    this.voltage = voltage;
  }

  @Override
  public void updateInputs(MotorIOInputsAutoLogged inputs) {
    inputs.position = motor.getCurrentPosition();
    inputs.targetDegrees = targetDegrees;

    inputs.targetVelocity = targetRotationsPerMinute;
    inputs.rpm = (motor.getVelocity(AngleUnit.DEGREES) / 360) * 60;
  }

  @Override
  public void setTargetAngle(double degrees) {
    targetDegrees = degrees;

    // May cause an issue with rounding, should be negligible
    motor.setTargetPosition((int) (degrees * motorTicksToDegrees));
  }

  @Override
  public void setPower(double power) {
    motor.setPower(power);
  }

  @Override
  public void setTargetVelocity(double rotationsPerMinute) {
    targetRotationsPerMinute = rotationsPerMinute;
    motor.setVelocity((rotationsPerMinute / 60) * 360, AngleUnit.DEGREES);
  }
}
