package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.config.Constants;

public class IntakeSubsystem implements Subsystem {
    DcMotor pivotMotor;
    DcMotor rollerMotor;

    public IntakeSubsystem() {
        pivotMotor = hardwareMap.get(DcMotor.class, Constants.IntakeConstants.pivotMotorKey);
        rollerMotor = hardwareMap.get(DcMotor.class, Constants.IntakeConstants.rollerMotorKey);
    }

    public void update() {}
}
