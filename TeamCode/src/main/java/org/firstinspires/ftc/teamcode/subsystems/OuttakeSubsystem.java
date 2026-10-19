package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.config.Constants;

public class OuttakeSubsystem implements Subsystem {
    DcMotor outtakeMotor;

    public OuttakeSubsystem() {
        outtakeMotor = hardwareMap.get(DcMotor.class, Constants.OuttakeConstants.outtakeMotorKey);
    }

    public void update() {

    }
}
