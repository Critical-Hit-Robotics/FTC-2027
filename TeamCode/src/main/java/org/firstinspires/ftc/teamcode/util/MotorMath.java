package org.firstinspires.ftc.teamcode.util;

public final class MotorMath {
    public static double voltsToPower(double volts, double robotVoltage) {
        if (volts == 0) {
            return 0;
        }

        // This should never happen !!!!
        if (volts > robotVoltage) {
            return robotVoltage;
        }

        return volts / robotVoltage;
    }
}
