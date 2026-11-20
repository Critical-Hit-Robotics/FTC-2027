package org.firstinspires.ftc.teamcode.subsystems.io;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import java.util.function.DoubleSupplier;

public class MotorIOServo implements MotorIO {
    private final ServoEx servo;
    private final DoubleSupplier voltage;
    private final int targetPosition = -1;
    private final int targetVoltage = -1;
    private final int targetPower = -1;

    public MotorIOServo(HardwareMap hardwareMap, String identifier, DoubleSupplier voltage) {
        servo = hardwareMap.get(ServoEx.class, identifier);
        this.voltage = voltage;
    }

    @Override
    public void updateInputs(MotorIOInputsAutoLogged inputs) {
        inputs.targetPosition = targetPosition;
        inputs.voltage = targetVoltage;
        
    }

    @Override
    public void setTargetVoltage(double voltage) {

    }

    @Override
    public void setTargetPosition(double voltage) {

    }

    @Override
    public void setPower(double power) {

    }

}
