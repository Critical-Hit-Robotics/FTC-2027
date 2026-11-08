package org.firstinspires.ftc.teamcode.subsystems.io;

import Ori.Coval.Logging.AutoLog;

public interface FlywheelIO {
    @AutoLog
    public class FlywheelInputs {
        private double position;
        private double power;
        private double voltage;
        private double targetPosition;
        private double rpm;
    }

    public void updateInputs(FlywheelInputsAutoLogged inputs);

    public void setVoltage(double voltage);

    public void setPower(double power);
}
