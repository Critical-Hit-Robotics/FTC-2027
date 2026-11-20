package org.firstinspires.ftc.teamcode.subsystems.vision.interfaces;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.config.Constants;
import org.firstinspires.ftc.teamcode.subsystems.vision.targets.VisionTarget;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LimelightVision implements VisionInterface {
    private final Limelight3A limelight;
    private LLResult result;

    public LimelightVision(HardwareMap hardwareMap) {
        limelight = hardwareMap.get(Limelight3A.class, Constants.VisionConstants.limelightKey);
        limelight.setPollRateHz(100);
        limelight.pipelineSwitch(0);
        limelight.start();
    }


    @Override
    public void periodic() {
        result = limelight.getLatestResult();
    }

    @Override
    public List<Integer> getAprilTagIds() {
        if (result == null) {
            return null;
        }

        return result.getFiducialResults().stream().map(LLResultTypes.FiducialResult::getFiducialId).collect(Collectors.toList());
    }

    @Override
    public List<VisionTarget> getTargets() {
        ArrayList<VisionTarget> targets = new ArrayList<>();


        result.getFiducialResults().forEach(tagResult -> {
            
        });

        return targets;
    }
}
