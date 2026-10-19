package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.OmniDriveSubsystem;

public class OmniOpMode extends OpMode {

    final ElapsedTime runtime = new ElapsedTime();
    OmniDriveSubsystem drive = null;

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
        drive = new OmniDriveSubsystem();

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

        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }
}
