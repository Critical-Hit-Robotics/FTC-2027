package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class StartIntake extends CommandBase {
    private final IntakeSubsystem m_intake;

    public StartIntake(IntakeSubsystem intake) {
        m_intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        m_intake.startIntake();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
