package org.firstinspires.ftc.teamcode.subsystems.io.Servo;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.subsystems.io.Servo.ServoIOInputsAutoLogged;

public class ServoIOReal implements ServoIO {
    public ServoEx servo;
    double targetVoltage = 0;
    double targetPosition = 0;

    public ServoIOReal(HardwareMap hardwareMap, String identifier) {
        servo = hardwareMap.get(ServoEx.class, identifier);
    }

    @Override
    public void updateInputs(ServoIOInputsAutoLogged inputs) {
        inputs.voltage = targetVoltage;
        inputs.position = servo.getRawPosition();
        inputs.targetPosition = targetPosition;
    }

    @Override
    public void setTargetPosition(int position) {
        targetPosition = position;
        servo.set(position);
    }

    @Override
    public void setTargetVoltage(int voltage) {
        targetVoltage = voltage;
//        servo.setPwm() TODO!
    }
}
