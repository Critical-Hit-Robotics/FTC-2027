package org.firstinspires.ftc.teamcode.subsystems.vision.interfaces;

import androidx.annotation.NonNull;

import com.pedropathing.math.Pose;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.config.Constants;
import org.firstinspires.ftc.teamcode.subsystems.vision.VisionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.vision.targets.ClusterTarget;
import org.firstinspires.ftc.teamcode.subsystems.vision.targets.TagTarget;
import org.firstinspires.ftc.teamcode.subsystems.vision.targets.VisionTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class LimelightVision implements VisionInterface {
    private final Limelight3A limelight;
    private LLResult result;

    Map<String, List<Integer>> clusterTagGroups = Map.ofEntries(
            Map.entry("Red Scoring", Arrays.asList(30, 31, 32, 33)),
            Map.entry("Red Audience", Arrays.asList(34, 35, 36, 37)),
            Map.entry("Blue Audience", Arrays.asList(38, 39, 40, 41)),
            Map.entry("Blue Scoring", Arrays.asList(42, 43, 44, 45))
    );

    public LimelightVision(HardwareMap hardwareMap) {
        limelight = hardwareMap.get(Limelight3A.class, Constants.VisionConstants.limelightKey);
        limelight.setPollRateHz(100);
        limelight.pipelineSwitch(0);
        limelight.start();
    }

    Pose3D averagePose(List<TagTarget> targets, DistanceUnit unit) {
        double total = 0;
        double x = 0, y = 0, z = 0;
        double yawS = 0, yawC = 0, pitS = 0, pitC = 0, rolS = 0, rolC = 0;
        long timestampNanoseconds = 0;

        for (VisionTarget target : targets) {
            double w = target.fractionVisible;
            Pose3D targetPose = target.targetPose;
            Position targetPosition = targetPose.getPosition();

            if (timestampNanoseconds == 0) {
                // comes from the same frame, doesn't matter which we use
                timestampNanoseconds = target.timestampNanoseconds;
            }

            x += targetPosition.x * w;
            y += targetPosition.y * w;
            z += targetPosition.z * w;

            YawPitchRollAngles o = targetPose.getOrientation();

            double yaw = o.getYaw(AngleUnit.RADIANS);
            double pit = o.getPitch(AngleUnit.RADIANS);
            double rol = o.getRoll(AngleUnit.RADIANS);

            yawS += Math.sin(yaw) * w;
            yawC += Math.cos(yaw) * w;
            pitS += Math.sin(pit) * w;
            pitC += Math.cos(pit) * w;
            rolS += Math.sin(rol) * w;
            rolC += Math.cos(rol) * w;

            total += w;
        }

        if (total == 0) {
            return null;
        }

        Position averagePosition = new Position(unit, x / total, y / total, z / total, timestampNanoseconds);
        YawPitchRollAngles angles = new YawPitchRollAngles(AngleUnit.RADIANS,
                Math.atan2(yawS, yawC),
                Math.atan2(pitS, pitC),
                Math.atan2(rolS, rolC), timestampNanoseconds);

        return new Pose3D(averagePosition, angles);
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
        ArrayList<TagTarget> singleTargets = getTagTargets();
        List<ClusterTarget> clusterTargets = getClusterTargets(singleTargets);
        List<VisionTarget> allTargets = new ArrayList<>(singleTargets);
        allTargets.addAll(clusterTargets);

        return allTargets;
    }

    @NonNull
    private List<ClusterTarget> getClusterTargets(ArrayList<TagTarget> singleTargets) {
        ArrayList<ClusterTarget> clusterTargets = new ArrayList<>();


        // Mapping targets by their ID to improve lookup times
        Map<Integer, TagTarget> targetByID = new HashMap<>();

        for (TagTarget target : singleTargets) {
            targetByID.put(target.id, target);
        }

        for (Map.Entry<String, List<Integer>> entry : clusterTagGroups.entrySet()) {
            List<Integer> aprilTagMembers = entry.getValue();
            List<TagTarget> foundTargets = new ArrayList<>();

            for (int aprilTagID : aprilTagMembers) {
                TagTarget target = targetByID.remove(aprilTagID);

                if (target != null) {
                    foundTargets.add(target);
                }
            }

            if (foundTargets.isEmpty()) {
                continue;
            }

            String clusterName = entry.getKey();
            VisionSubsystem.CurrentAlliance alliance;

            if (clusterName.toLowerCase().contains("red")) {
                alliance = VisionSubsystem.CurrentAlliance.RED;
            } else {
                alliance = VisionSubsystem.CurrentAlliance.BLUE;
            }

            // must be true, we just checked if it's empty
            TagTarget baseTarget = foundTargets.stream().findFirst().get();
            Pose3D averagedPose = averagePose(foundTargets, baseTarget.targetPose.getPosition().unit);

            ClusterTarget clusterTarget = new ClusterTarget(baseTarget.timestampNanoseconds, averagedPose, clusterName, alliance, foundTargets, aprilTagMembers.size());
            clusterTargets.add(clusterTarget);
        }

        return clusterTargets;
    }

    @NonNull
    private ArrayList<TagTarget> getTagTargets() {
        ArrayList<TagTarget> singleTargets = new ArrayList<>();

        for (LLResultTypes.FiducialResult fiducialResult : result.getFiducialResults()) {
            Pose3D tagPose = fiducialResult.getTargetPoseRobotSpace();
            long timestampNanoseconds = (long) result.getCaptureLatency();
            int id = fiducialResult.getFiducialId();

            TagTarget singleTag = new TagTarget(timestampNanoseconds, tagPose, id);
            singleTargets.add(singleTag);
        }
        return singleTargets;
    }
}


