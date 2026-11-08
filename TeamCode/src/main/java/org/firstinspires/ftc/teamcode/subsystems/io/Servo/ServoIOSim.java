package org.firstinspires.ftc.teamcode.subsystems.io.Servo;


public class ServoIOSim implements ServoIO {
    double targetVoltage = 0;
    double targetPosition = 0;

    public ServoIOSim() {

    }

    @Override
    public void updateInputs(ServoIOInputsAutoLogged inputs) {
        inputs.voltage = targetVoltage;
        inputs.position = targetPosition; // too lazy to actually simulate lol
        inputs.targetPosition = targetPosition;
    }

    @Override
    public void setTargetPosition(int position) {
        targetPosition = position;
    }

    @Override
    public void setTargetVoltage(int voltage) {
        targetVoltage = voltage;
    }
}
