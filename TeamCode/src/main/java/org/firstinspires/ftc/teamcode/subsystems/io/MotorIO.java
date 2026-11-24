package org.firstinspires.ftc.teamcode.subsystems.io;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import Ori.Coval.Logging.AutoLog;

public interface MotorIO {
    @AutoLog
    public class MotorIOInputs {
        public double position;
        public double power;

        public double targetVoltage;
        public double targetPosition;
        public double rpm;
    }

    public void updateInputs(MotorIOInputsAutoLogged inputs);

    public void setTargetAngle(AngleUnit angle);

    public default void setTargetVelocity(double rotationsPerMinute) {
        throw new UnsupportedOperationException("Cannot set velocity on this class.");
    }
}
