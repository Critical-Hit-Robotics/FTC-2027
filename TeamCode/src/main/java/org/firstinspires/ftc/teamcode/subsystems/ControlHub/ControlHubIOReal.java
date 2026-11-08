package org.firstinspires.ftc.teamcode.subsystems.ControlHub;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.config.Constants;

public class ControlHubIOReal implements ControlHubIO {
    private final VoltageSensor voltageSensor;

    public ControlHubIOReal(HardwareMap hardwareMap) {
        voltageSensor = hardwareMap.get(VoltageSensor.class, Constants.ControlHubConstants.voltageSensorKey);
    }

    @Override
    public void updateInputs(ControlHubIOInputsAutoLogged inputs) {
        inputs.voltage = getVoltage();
    }

    @Override
    public double getVoltage() {
        return voltageSensor.getVoltage();
    }

}
