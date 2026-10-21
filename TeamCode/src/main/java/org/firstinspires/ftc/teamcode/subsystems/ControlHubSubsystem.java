package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.VoltageSensor;

public class ControlHubSubsystem {
    private VoltageSensor voltageSensor;

    public ControlHubSubsystem() {
        voltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");

    }

    public double voltageToPower(double volts) {
        double voltage = voltageSensor.getVoltage();

        // This should never happen !!!!
        if (volts > voltage) {
            return voltage;
        }

        return volts / voltage;
    }
}
