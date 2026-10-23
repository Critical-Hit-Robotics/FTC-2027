package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.ControlHubSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.OmniDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class MainOpMode extends OpMode {

    final ElapsedTime runtime = new ElapsedTime();
    OmniDriveSubsystem drive;
    IntakeSubsystem intake;
    ShooterSubsystem shooter;
    ControlHubSubsystem controlHub;
    VisionSubsystem vision;

    /**
     * This method will be called repeatedly during the period between when
     * the init button is pressed and when the play button is pressed (or the
     * OpMode is stopped).
     */
    @Override
    public void init_loop() {
        // TODO: Poll for our current team?
    };

    /**
     * This method will be called once, when the play button is pressed.
     */
    @Override
    public void start() {
        runtime.reset();
    };

    /**
     * This method will be called once, when the INIT button is pressed.
     */
    @Override
    public void init() {


        controlHub = new ControlHubSubsystem();
        drive = new OmniDriveSubsystem();
        intake = new IntakeSubsystem(controlHub);
        shooter = new ShooterSubsystem(controlHub);
        vision = new VisionSubsystem();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    /**
     * This method will be called repeatedly during the period between when
     * the play button is pressed and when the OpMode is stopped.
     */
    @Override
    public void loop() {
        drive.update();
        intake.update();
        shooter.update();

        // Intake binds:
        // B: Start intaking
        // A: Stop intaking

        if (gamepad1.b) {
            intake.setState(IntakeSubsystem.IntakeState.INTAKE);
        }

        if (gamepad1.a) {
            intake.setState(IntakeSubsystem.IntakeState.STOP_INTAKE);
        }

//        if (gamepad1.b && gamepad1.a) {
//            intake.setState(IntakeSubsystem.IntakeState.IDLE);
//        }

        // Shooter binds:
        // X: Shoot

        if (gamepad1.y) {

        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }
}
