package org.firstinspires.ftc.teamcode.subsystems.io;


public class MotorIOSim implements MotorIO {
    double targetVoltage = 0;
    double targetPosition = 0;

    public MotorIOSim() {

    }


    @Override
    public void setTargetPosition(double position) {

        targetPosition = position;

    }

    @Override
    public void setPower(double power) {

    }

    @Override
    public void updateInputs(MotorIOInputsAutoLogged inputs) {
        inputs.voltage = targetVoltage;
        inputs.position = targetPosition;
        inputs.targetPosition = targetPosition;
    }

    @Override
    public void setTargetVoltage(double voltage) {
        targetVoltage = voltage;
    }
}
