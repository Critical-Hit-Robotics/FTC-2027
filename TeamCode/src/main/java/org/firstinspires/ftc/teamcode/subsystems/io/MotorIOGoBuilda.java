package org.firstinspires.ftc.teamcode.subsystems.io;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.function.DoubleSupplier;

public class MotorIOGoBuilda implements MotorIO {
    DcMotorEx m_motor;
    DoubleSupplier m_voltage;

    public MotorIOGoBuilda(HardwareMap hardwareMap, String identifier, DoubleSupplier voltage) {
        m_motor = hardwareMap.get(DcMotorEx.class, identifier);
    }

    @Override
    public void updateInputs(MotorIOInputsAutoLogged inputs) {

    }

    @Override
    public void setTargetVoltage(double voltage) {

    }

    @Override
    public void setTargetPosition(double voltage) {

    }

    @Override
    public void setPower(double power) {

    }

}
