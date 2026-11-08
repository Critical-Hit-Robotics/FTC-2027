package org.firstinspires.ftc.teamcode.subsystems.io.Servo;

import org.firstinspires.ftc.teamcode.subsystems.io.Servo.ServoIOInputsAutoLogged;

import Ori.Coval.Logging.AutoLog;

public interface ServoIO {
    @AutoLog
    public class ServoIOInputs {
        public double position;
        public double targetPosition;
        public double voltage;
    }

    public void updateInputs(ServoIOInputsAutoLogged inputs);

    public void setTargetPosition(int position);

    public void setTargetVoltage(int voltage);
}
