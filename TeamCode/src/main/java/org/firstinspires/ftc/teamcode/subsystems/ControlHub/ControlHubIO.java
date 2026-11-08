package org.firstinspires.ftc.teamcode.subsystems.ControlHub;

import org.firstinspires.ftc.teamcode.subsystems.ControlHub.ControlHubIOInputsAutoLogged;

import Ori.Coval.Logging.AutoLog;


public interface ControlHubIO {
    @AutoLog
    public class ControlHubIOInputs {
        public double voltage;
    }

    public void updateInputs(ControlHubIOInputsAutoLogged inputs);

    public double getVoltage();
}
