package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.config.Constants;

public class ShooterSubsystem implements Subsystem {
    DcMotor rotatorMotor;
    DcMotor frontFlyWheel;
    DcMotor backFlyWheel;

    public ShooterSubsystem() {
        rotatorMotor = hardwareMap.get(DcMotor.class, Constants.ShooterConstants.rotatorMotorKey);
        frontFlyWheel = hardwareMap.get(DcMotor.class, Constants.ShooterConstants.frontFlywheelKey);
        backFlyWheel = hardwareMap.get(DcMotor.class, Constants.ShooterConstants.backFlywheelKey);
    }

    @Override
    public void update() {

    }
}
