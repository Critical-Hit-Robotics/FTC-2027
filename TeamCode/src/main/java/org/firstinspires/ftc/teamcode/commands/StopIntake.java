package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class StopIntake extends CommandBase {
    private final IntakeSubsystem m_intake;

    public StopIntake(IntakeSubsystem intake) {
        m_intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        m_intake.stopIntake();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
