package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.ControlHub.ControlHubSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Shooter.TurretSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.vision.VisionSubsystem;
import org.firstinspires.ftc.teamcode.util.CurrentAlliance;

public class Robot {
  public final ElapsedTime runtime = new ElapsedTime();
  public final Drivetrain drive;
  public final IntakeSubsystem intake;
  public final ShooterSubsystem shooter;
  public final TurretSubsystem turret;
  public final ControlHubSubsystem controlHub;
  public final VisionSubsystem vision;

  public final GamepadEx driverController;

  public Robot() {
    controlHub = new ControlHubSubsystem(hardwareMap);
    drive = new Drivetrain(hardwareMap);
    intake = new IntakeSubsystem(hardwareMap, controlHub.getVoltageSupplier());
    shooter = new ShooterSubsystem(hardwareMap, controlHub.getVoltageSupplier());
    vision = new VisionSubsystem(hardwareMap, drive, shooter);
    turret = new TurretSubsystem(hardwareMap, controlHub.getVoltageSupplier());

    driverController = new GamepadEx(gamepad1);
  }
}
