package org.firstinspires.ftc.teamcode.subsystems.ControlHub;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.ControlHub.ControlHubIOInputsAutoLogged;

import java.util.function.DoubleSupplier;

public class ControlHubSubsystem implements Subsystem {
    private ControlHubIO io;
    private ControlHubIOInputsAutoLogged inputs;


    @Override
    public void periodic() {
        io.updateInputs(inputs);
    }

    public ControlHubSubsystem(HardwareMap hardwareMap) {
        io = new ControlHubIOReal(hardwareMap);
        inputs = new ControlHubIOInputsAutoLogged();
    }

    public DoubleSupplier getVoltageSupplier() {
        return this::getVoltage;
    }

    public double getVoltage() {
        return inputs.voltage;
    }
}
