package org.firstinspires.ftc.teamcode.subsystems.Shooter;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Subsystem;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.config.Constants.ShooterConstants.TurretConstants;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.io.MotorIO;
import org.firstinspires.ftc.teamcode.subsystems.io.MotorIOInputsAutoLogged;
import org.firstinspires.ftc.teamcode.subsystems.io.MotorIOServo;

import java.util.function.DoubleSupplier;

public class TurretSubsystem implements Subsystem {
    public enum TurretState {
        TARGETING, IDLE
    }

    public enum TurretTarget {
        RED_HIVE, BLUE_HIVE, NONE
    }

    VisionSubsystem visionSubsystem;

    private final MotorIO leftServo;
    private final MotorIO rightServo;
    private final MotorIOInputsAutoLogged leftServoInputs;
    private final MotorIOInputsAutoLogged rightServoInputs;
    private final DcMotorEx turretAzimuthEncoder;

    private double targetDegrees = 0;

    PIDController controller = new PIDController(
            TurretConstants.azimuthPIDCoefficients.p, TurretConstants.azimuthPIDCoefficients.i, TurretConstants.azimuthPIDCoefficients.d
    );

    public TurretSubsystem(HardwareMap hardwareMap, DoubleSupplier voltageSupplier) {

        leftServo = new MotorIOServo(hardwareMap, TurretConstants.leftServoKey, voltageSupplier);
        rightServo = new MotorIOServo(hardwareMap, TurretConstants.rightServoKey, voltageSupplier);

        turretAzimuthEncoder = hardwareMap.get(DcMotorEx.class, TurretConstants.azimuthEncoder);

        leftServoInputs = new MotorIOInputsAutoLogged();
        rightServoInputs = new MotorIOInputsAutoLogged();
    }

    public void setVoltage(double volts) {
        leftServo.setTargetVoltage(volts);
        rightServo.setTargetVoltage(volts);
    }

    public void setAngle(double degrees) {
        if (!TurretConstants.isValidAngle(degrees)) {
            // Log here
            return;
        }

//        double currentAngle = turretAzimuthEncoder.getCurrentPosition() / ;
//        double difference = AngleUnit.normalizeDegrees(degrees) - currentAngle;
//
//
//        leftServo.setTargetPosition(difference / 2);
//        rightServo.setTargetPosition(difference / 2);
    }

    @Override
    public void periodic() {
        leftServo.updateInputs(leftServoInputs);
        rightServo.updateInputs(rightServoInputs);


    }

    public void setState(TurretState state) {

    }

    public void setTarget(TurretTarget target) {

    }
}

