package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Subsystem;

import org.firstinspires.ftc.teamcode.config.Constants.IntakeConstants;
import org.firstinspires.ftc.teamcode.util.MotorMath;

import java.util.function.DoubleSupplier;


public class IntakeSubsystem implements Subsystem {

    DcMotor rollerMotorLeft;
    DcMotor rollerMotorRight;
    final DoubleSupplier voltageSupplier;


    public IntakeSubsystem(HardwareMap hardwareMap, DoubleSupplier voltageSupplier) {
        this.voltageSupplier = voltageSupplier;

        rollerMotorLeft = hardwareMap.get(DcMotor.class, IntakeConstants.rollerMotorLeftKey);
        rollerMotorRight = hardwareMap.get(DcMotor.class, IntakeConstants.rollerMotorRightKey);

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

    void setVoltage(double targetVoltage) {
        double power = MotorMath.voltsToPower(targetVoltage, voltageSupplier.getAsDouble());
        setPower(power);
    }

    public void startIntake() {
        setVoltage(6);
    }

    public void stopIntake() {
        setVoltage(0);
    }
}
