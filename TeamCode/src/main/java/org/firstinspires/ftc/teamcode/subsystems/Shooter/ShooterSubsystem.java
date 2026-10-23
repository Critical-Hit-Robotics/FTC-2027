package org.firstinspires.ftc.teamcode.subsystems.Shooter;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.config.Constants;
import org.firstinspires.ftc.teamcode.subsystems.ControlHubSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class ShooterSubsystem extends Subsystem {
    DcMotor rotatorMotor;
    DcMotor frontFlyWheel;
    DcMotor backFlyWheel;

    ControlHubSubsystem controlHub;

    public enum ShooterState {
        SHOOTING, PREPARATION, OFF
    }

    ShooterState state;

    public ShooterSubsystem(ControlHubSubsystem controlHub) {
        rotatorMotor = hardwareMap.get(DcMotor.class, Constants.ShooterConstants.rotatorMotorKey);
        frontFlyWheel = hardwareMap.get(DcMotor.class, Constants.ShooterConstants.frontFlywheelKey);
        backFlyWheel = hardwareMap.get(DcMotor.class, Constants.ShooterConstants.backFlywheelKey);
        this.controlHub = controlHub;
    }

    @Override
    public void update() {

    }
}
