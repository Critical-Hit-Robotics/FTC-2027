package org.firstinspires.ftc.teamcode.subsystems.vision.interfaces;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.subsystems.vision.VisionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.vision.targets.ClusterTarget;
import org.firstinspires.ftc.teamcode.subsystems.vision.targets.TagTarget;
import org.firstinspires.ftc.teamcode.subsystems.vision.targets.VisionTarget;
import org.firstinspires.ftc.teamcode.util.CurrentAlliance;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagClusterDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import Ori.Coval.Logging.Logger.KoalaLog;

public class HardwareVision implements VisionInterface {

  private final VisionPortal portal;
  private final AprilTagProcessor processor;
  private List<AprilTagDetection> detections;

  AprilTagProcessor getProcessor() {
    return new AprilTagProcessor.Builder()
        .setTagLibrary(AprilTagGameDatabase.getCurrentGameTagLibrary())
        .build();
  }

  public HardwareVision(HardwareMap hardwareMap, String cameraName) {
    processor = getProcessor();
    portal =
        new VisionPortal.Builder()
            .addProcessor(processor)
            .setCamera(hardwareMap.get(WebcamName.class, cameraName))
            .build();
  }

  public HardwareVision(BuiltinCameraDirection direction) {
    processor = getProcessor();
    portal = new VisionPortal.Builder().addProcessor(processor).setCamera(direction).build();
  }

  @Override
  public void periodic() {
    this.detections = processor.getDetections();
  }

  @Override
  public List<Integer> getAprilTagIds() {
    return detections.stream()
        .map(detection -> ((AprilTagSingleDetection) detection).id)
        .collect(Collectors.toList());
  }

  @Override
  public List<VisionTarget> getTargets() {
    ArrayList<VisionTarget> targets = new ArrayList<>();

    for (AprilTagDetection detection : detections) {
      long timestampNanoseconds = detection.frameAcquisitionNanoTime;

      AprilTagPoseFtc targetPose = detection.ftcPose;
      Pose3D newPose =
          new Pose3D(
              new Position(
                  detection.distanceUnit,
                  targetPose.x,
                  targetPose.y,
                  targetPose.z,
                  detection.frameAcquisitionNanoTime),
              new YawPitchRollAngles(
                  AngleUnit.DEGREES,
                  targetPose.yaw,
                  targetPose.pitch,
                  targetPose.roll,
                  detection.frameAcquisitionNanoTime));

      VisionTarget target = null;

      if (detection instanceof AprilTagSingleDetection) {
        AprilTagSingleDetection singleDetection = (AprilTagSingleDetection) detection;
        target = new TagTarget(timestampNanoseconds, newPose, singleDetection.id);
      }

      if (detection instanceof AprilTagClusterDetection) {
        AprilTagClusterDetection clusterDetection = (AprilTagClusterDetection) detection;

        String clusterName = clusterDetection.metadata.name;
        CurrentAlliance alliance;

        if (clusterName.toLowerCase().contains("red")) {
          alliance = CurrentAlliance.RED;
        } else {
          alliance = CurrentAlliance.BLUE;
        }

        target =
            new ClusterTarget(
                timestampNanoseconds,
                newPose,
                clusterName,
                alliance,
                null,
                ((AprilTagClusterDetection) detection).percentClusterFound);
      }

      if (target == null) {
        // log that we couldn't find a proper tag for this!
        continue;
      }

      targets.add(target);
    }

    return targets;
  }
}
