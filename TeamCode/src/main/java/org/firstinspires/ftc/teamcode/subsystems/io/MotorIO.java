package org.firstinspires.ftc.teamcode.subsystems.io;

import Ori.Coval.Logging.AutoLog;

public interface MotorIO {
    @AutoLog
    public class MotorIOInputs {
        public double position;
        public double power;
        public double voltage;

        public double targetVoltage;
        public double targetPosition;
        public double rpm;
    }

    public void updateInputs(MotorIOInputsAutoLogged inputs);

    public void setTargetVoltage(double voltage);

    public void setTargetPosition(double voltage);

    public void setPower(double power);
}
