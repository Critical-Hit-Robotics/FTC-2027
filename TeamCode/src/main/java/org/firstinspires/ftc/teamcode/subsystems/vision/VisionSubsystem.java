package org.firstinspires.ftc.teamcode.subsystems.vision;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Subsystem;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.subsystems.vision.interfaces.HardwareVision;
import org.firstinspires.ftc.teamcode.subsystems.vision.interfaces.LimelightVision;
import org.firstinspires.ftc.teamcode.subsystems.vision.interfaces.VisionInterface;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.teamcode.config.Constants.VisionConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisionSubsystem implements Subsystem {
    public enum CurrentAlliance {
        RED, BLUE
    }


    private final VisionInterface vision;
    private CurrentAlliance alliance;
    private Pose3D robotPose;


    public VisionSubsystem(HardwareMap hardwareMap) {
        switch (VisionConstants.visionType) {
            case NATIVE:
                vision = new HardwareVision(BuiltinCameraDirection.BACK);
                break;

            case LIMELIGHT:
                vision = new LimelightVision(hardwareMap);
                break;

            default:
                vision = new HardwareVision(hardwareMap, VisionConstants.webcamKey);
        }
    }

    @Override
    public void periodic() {
        vision.periodic();
    }
}
