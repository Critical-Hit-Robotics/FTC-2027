package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.config.Constants;

public class IntakeSubsystem extends Subsystem {
    public enum IntakeState {
        INTAKE, STOP_INTAKE, IDLE
    }

    ControlHubSubsystem controlHub;
    IntakeState state = IntakeState.IDLE;

    DcMotor rollerMotorLeft;
    DcMotor rollerMotorRight;


    public IntakeSubsystem(ControlHubSubsystem controlHub) {
        this.controlHub = controlHub;

        rollerMotorLeft = hardwareMap.get(DcMotor.class, Constants.IntakeConstants.rollerMotorLeftKey);
        rollerMotorRight = hardwareMap.get(DcMotor.class, Constants.IntakeConstants.rollerMotorRightKey);

        // Change to use an encoder if it doesn't affect performance
        rollerMotorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rollerMotorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rollerMotorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rollerMotorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    void setPower(double power) {
        rollerMotorRight.setPower(power);
        rollerMotorLeft.setPower(power);
    }

    void setVolts(double volts) {
        double power = controlHub.voltageToPower(volts);
        setPower(power);
    }

    public void update() {
        switch (this.state) {
            case IDLE:
                break;
            case INTAKE:
                setVolts(3);
                break;
            case STOP_INTAKE:
                setPower(0);
                break;
        }
    }

    public void setState(IntakeState state) {
        this.state = state;
    };
}
