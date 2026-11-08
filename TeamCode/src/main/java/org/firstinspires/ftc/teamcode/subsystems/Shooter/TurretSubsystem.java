package org.firstinspires.ftc.teamcode.subsystems.Shooter;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Subsystem;
import com.seattlesolvers.solverslib.controller.PIDController;

import org.firstinspires.ftc.teamcode.config.Constants.ShooterConstants.TurretConstants;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.io.Servo.ServoIO;
import org.firstinspires.ftc.teamcode.subsystems.io.Servo.ServoIOInputsAutoLogged;
import org.firstinspires.ftc.teamcode.subsystems.io.Servo.ServoIOReal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.function.DoubleSupplier;

public class TurretSubsystem implements Subsystem {
    public enum TurretState {
        TARGETING, IDLE
    }

    public enum TurretTarget {
        RED_HIVE, BLUE_HIVE, NONE
    }

    VisionSubsystem visionSubsystem;

    private final ServoIO leftServo;
    private final ServoIO rightServo;
    private final ServoIOInputsAutoLogged leftServoInputs;
    private final ServoIOInputsAutoLogged rightServoInputs;
    private final DoubleSupplier voltageSupplier;

    TurretState state;
    TurretTarget target;

    PIDController controller = new PIDController(
            TurretConstants.azimuthPIDCoefficients.p, TurretConstants.azimuthPIDCoefficients.i, TurretConstants.azimuthPIDCoefficients.d
    );

    public TurretSubsystem(HardwareMap hardwareMap, DoubleSupplier voltageSupplier) {
        this.voltageSupplier = voltageSupplier;
        leftServo = new ServoIOReal(hardwareMap, TurretConstants.leftServoKey);
        rightServo = new ServoIOReal(hardwareMap, TurretConstants.rightServoKey);

        leftServoInputs = new ServoIOInputsAutoLogged();
        rightServoInputs = new ServoIOInputsAutoLogged();
    }

    public void setVoltage() {
        // todo!
    }

    @Override
    public void periodic() {
        leftServo.updateInputs(leftServoInputs);
        rightServo.updateInputs(rightServoInputs);

        switch (state) {
            case TARGETING:
                AprilTagDetection nearestHub = visionSubsystem.getNearestHive();

                if (nearestHub == null) {
                    break;
                }

                break;
            case IDLE:


                break;
        }
    }

    public void setState(TurretState state) {
        this.state = state;
    }

    public void setTarget(TurretTarget target) {
        this.target = target;
    }
}

