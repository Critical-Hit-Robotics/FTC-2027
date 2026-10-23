package org.firstinspires.ftc.teamcode.subsystems.Shooter;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.config.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class TurretSubsystem extends Subsystem {
    public enum TurretState {
        TARGETING, IDLE
    }

    public enum TurretTarget {
        RED_HIVE, BLUE_HIVE, NONE
    }

    DcMotor azimuthMotor;
    VisionSubsystem visionSubsystem;

    TurretState state;
    TurretTarget target;



    public TurretSubsystem() {
        azimuthMotor = hardwareMap.get(DcMotor.class, Constants.ShooterConstants.azimuthMotorKey);
        azimuthMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        azimuthMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void update() {
        switch (state) {

        }
    }

    public void setState(TurretState state) {
        this.state = state;
    }

    public void setTarget(TurretTarget target) {
        this.target = target;
    }
}
