package org.firstinspires.ftc.teamcode.subsystems.io;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.function.DoubleSupplier;

public class MotorIOGoBuilda implements MotorIO {
    private final DcMotorEx motor;
    private final DoubleSupplier m_voltage;
    private double targetPosition = 0;
    private double targetRotationsPerMinute = 0;

    public MotorIOGoBuilda(HardwareMap hardwareMap, String identifier, DoubleSupplier voltage) {
        motor = hardwareMap.get(DcMotorEx.class, identifier);
        m_voltage = voltage;
    }

    @Override
    public void updateInputs(MotorIOInputsAutoLogged inputs) {

    }

    @Override
    public void setTargetAngle(AngleUnit angle) {

    }

    @Override
    public void setTargetVelocity(double rotationsPerMinute) {
        targetRotationsPerMinute = rotationsPerMinute;
    }
}
