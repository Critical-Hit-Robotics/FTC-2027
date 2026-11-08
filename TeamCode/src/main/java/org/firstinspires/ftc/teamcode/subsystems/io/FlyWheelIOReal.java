package org.firstinspires.ftc.teamcode.subsystems.io;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.function.DoubleSupplier;

public class FlyWheelIOReal implements FlywheelIO {
    DcMotorEx m_motor;
    DoubleSupplier m_voltage;

    public FlyWheelIOReal(HardwareMap hardwareMap, String identifier, DoubleSupplier voltage) {
        m_motor = hardwareMap.get(DcMotorEx.class, identifier);
    }

    @Override
    public void setPower(double power) {
        m_motor.setPower(power);
    }

    @Override
    public void setVoltage(double voltage) {

    }

    @Override
    public void updateInputs(FlywheelInputsAutoLogged inputs) {

    }
}
