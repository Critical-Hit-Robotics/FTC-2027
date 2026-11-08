package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.command.FunctionalCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.commands.StartIntake;
import org.firstinspires.ftc.teamcode.commands.StopIntake;

import Ori.Coval.Logging.Logger.KoalaLog;

public class MainOpMode extends OpMode {

    protected Robot robot;


    @Override
    public void init() {
        KoalaLog.setup(hardwareMap);

        robot = new Robot();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }
    
    @Override
    public void start() {
        robot.runtime.reset();
        KoalaLog.start();
    }


    @Override
    public void loop() {
        robot.driverController.getGamepadButton(GamepadKeys.Button.B).whenPressed(new StartIntake(robot.intake));
        robot.driverController.getGamepadButton(GamepadKeys.Button.A).whenPressed(new StopIntake(robot.intake));

        telemetry.addData("Status", "Run Time: " + robot.runtime.toString());
    }

    @Override
    public void stop() {
        KoalaLog.stop();
    }


}
