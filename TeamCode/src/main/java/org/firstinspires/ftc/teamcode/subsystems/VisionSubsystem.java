package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import android.util.Size;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.config.Constants;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisionSubsystem extends Subsystem {
    public enum CurrentAlliance {
        RED, BLUE
    }

    CurrentAlliance alliance;
    AprilTagProcessor aprilTagProcessor;


    private VisionPortal visionPortal;
    private final boolean USE_WEBCAM = false;

    private final int[][] RED_SCORING_TAGS = {{30, 31}, {32, 33}};
    private final int[][] BLUE_SCORING_TAGS = {{42, 43}, {44, 45}};

    private final int[][] BLUE_AUDIENCE_TAGS = {};
    private final int[][] RED_AUDIENCE_TAGS = {};

    AprilTagDetection[] detections = {};

    public VisionSubsystem() {
        aprilTagProcessor = new AprilTagProcessor.Builder().build();

        VisionPortal.Builder builder = new VisionPortal.Builder();

        if (USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, Constants.VisionConstants.webcamKey));
        } else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }

        builder.setCameraResolution(new Size(640, 480));
        builder.setStreamFormat(VisionPortal.StreamFormat.MJPEG);

        builder.addProcessor(aprilTagProcessor);
        visionPortal = builder.build();
    }

    public ArrayList<AprilTagDetection> getHives() {
        ArrayList<AprilTagDetection> hiveDetections = new ArrayList<>();
        List<AprilTagDetection> currentDetections = aprilTagProcessor.getDetections();

        for (AprilTagDetection detection : currentDetections) {
            if (detection instanceof AprilTagSingleDetection) {
                AprilTagSingleDetection singleDet = (AprilTagSingleDetection) detection;

                if (isCurrentHive(singleDet.id)) {
                    hiveDetections.add(detection);
                }
            }
        }

        return hiveDetections;
    }

    // Can return null.
    public AprilTagDetection getNearestHive() {
        ArrayList<AprilTagDetection> hives = getHives();

        if (hives.isEmpty()) {
            return null;
        }

        AprilTagDetection nearest = hives.get(0);

        for (AprilTagDetection detection : hives) {
            if (detection.ftcPose.range < nearest.ftcPose.range) {
                nearest = detection;
            }
        }

        return nearest;
    }


    // Returns if the passed april tag ID is our alliance's ID.
    boolean isCurrentHive(int aprilTagValue) {
        int[][] targetScoringGroups;

        switch (alliance) {
            case BLUE:
                targetScoringGroups = BLUE_SCORING_TAGS;
                break;
            case RED:
                targetScoringGroups = RED_SCORING_TAGS;
                break;

            default:
                targetScoringGroups = new int[][]{};
        }

        for (int[] group : targetScoringGroups) {
            if (Arrays.stream(group).anyMatch(n -> n == aprilTagValue)) {
                return true;
            }
        }

        return false;
    }
}
