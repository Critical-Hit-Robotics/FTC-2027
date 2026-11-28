package org.firstinspires.ftc.teamcode.subsystems.Shooter;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Subsystem;

import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.teamcode.config.Constants;
import org.firstinspires.ftc.teamcode.subsystems.io.MotorIO;
import org.firstinspires.ftc.teamcode.subsystems.io.MotorIOGoBuilda;
import org.firstinspires.ftc.teamcode.subsystems.io.MotorIOInputsAutoLogged;

import java.util.function.DoubleSupplier;

public class ShooterSubsystem implements Subsystem {

  MotorIO frontFlyWheel;
  MotorIO backFlyWheel;

  MotorIOInputsAutoLogged backFlyWheelInputs;
  MotorIOInputsAutoLogged frontFlyWheelInputs;

  public ShooterSubsystem(HardwareMap hardwareMap, DoubleSupplier voltageSupplier) {
    frontFlyWheel =
        new MotorIOGoBuilda(
            hardwareMap, Constants.ShooterConstants.frontFlywheelKey, voltageSupplier);
    backFlyWheel =
        new MotorIOGoBuilda(
            hardwareMap, Constants.ShooterConstants.backFlywheelKey, voltageSupplier);
    frontFlyWheelInputs = new MotorIOInputsAutoLogged();
    backFlyWheelInputs = new MotorIOInputsAutoLogged();
  }

  // Seconds
  public double timeOfFlight(Position robotPosition, Position hubPosition) {
    return 1.0;
  }

  @Override
  public void periodic() {
    backFlyWheel.updateInputs(backFlyWheelInputs);
    frontFlyWheel.updateInputs(frontFlyWheelInputs);
  }
}
