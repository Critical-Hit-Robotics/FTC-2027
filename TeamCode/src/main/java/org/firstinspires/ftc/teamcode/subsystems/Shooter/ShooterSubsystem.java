package org.firstinspires.ftc.teamcode.subsystems.Shooter;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Subsystem;

import org.firstinspires.ftc.teamcode.config.Constants;
import org.firstinspires.ftc.teamcode.subsystems.io.FlyWheelIOReal;
import org.firstinspires.ftc.teamcode.subsystems.io.FlywheelIO;
import org.firstinspires.ftc.teamcode.subsystems.io.FlywheelInputsAutoLogged;

import java.util.function.DoubleSupplier;

public class ShooterSubsystem implements Subsystem {

    FlywheelIO frontFlyWheel;
    FlywheelIO backFlyWheel;

    FlywheelInputsAutoLogged backFlyWheelInputs;
    FlywheelInputsAutoLogged frontFlyWheelInputs;

    public ShooterSubsystem(HardwareMap hardwareMap, DoubleSupplier voltageSupplier) {
        frontFlyWheel = new FlyWheelIOReal(hardwareMap, Constants.ShooterConstants.frontFlywheelKey, voltageSupplier);
        backFlyWheel = new FlyWheelIOReal(hardwareMap, Constants.ShooterConstants.backFlywheelKey, voltageSupplier);
        frontFlyWheelInputs = new FlywheelInputsAutoLogged();
        backFlyWheelInputs = new FlywheelInputsAutoLogged();
    }

    @Override
    public void periodic() {
        backFlyWheel.updateInputs(backFlyWheelInputs);
        frontFlyWheel.updateInputs(frontFlyWheelInputs);
    }
}
