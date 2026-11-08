package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.ControlHub.ControlHubSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class Robot {
    public final ElapsedTime runtime = new ElapsedTime();
    public final Drivetrain drive;
    public final IntakeSubsystem intake;
    public final ShooterSubsystem shooter;
    public final ControlHubSubsystem controlHub;
    public final VisionSubsystem vision;

    public final GamepadEx driverController;


    public Robot() {
        controlHub = new ControlHubSubsystem(hardwareMap);
        drive = new Drivetrain(hardwareMap);
        intake = new IntakeSubsystem(hardwareMap, controlHub.getVoltageSupplier());
        shooter = new ShooterSubsystem(hardwareMap, controlHub.getVoltageSupplier());
        vision = new VisionSubsystem(hardwareMap);
        driverController = new GamepadEx(gamepad1);
    }
}
