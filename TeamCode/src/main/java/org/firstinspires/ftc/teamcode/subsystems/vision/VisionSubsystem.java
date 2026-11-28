package org.firstinspires.ftc.teamcode.subsystems.vision;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Subsystem;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.vision.interfaces.HardwareVision;
import org.firstinspires.ftc.teamcode.subsystems.vision.interfaces.LimelightVision;
import org.firstinspires.ftc.teamcode.subsystems.vision.interfaces.VisionInterface;
import org.firstinspires.ftc.teamcode.subsystems.vision.targets.ClusterTarget;
import org.firstinspires.ftc.teamcode.util.PoseMath;
import org.firstinspires.ftc.teamcode.config.Constants.VisionConstants;
import org.firstinspires.ftc.teamcode.config.Constants.DashboardConstants;

import java.util.List;
import java.util.stream.Collectors;

public class VisionSubsystem implements Subsystem {

  private final VisionInterface vision;
  private final Drivetrain drivetrain;
  private final ShooterSubsystem shooter;

  public VisionSubsystem(HardwareMap hardwareMap, Drivetrain drivetrain, ShooterSubsystem shooter) {
    this.drivetrain = drivetrain;
    this.shooter = shooter;

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

    // Find the angle at which the turret should be aiming
    Pose3D currentHub = getClosestHive().targetPose;
    Pose3D currentPose = drivetrain.getRobotPose();

    Position projectedRobot =
        PoseMath.advance2d(
            currentPose.getPosition(), drivetrain.getRobotVelocity(), 100, DistanceUnit.CM);
  }

  public ClusterTarget getClosestHive() {
    List<ClusterTarget> targets =
        vision.getTargets().stream()
            .filter(x -> {
              boolean isCluster = x instanceof ClusterTarget;
              if (isCluster) {
                ClusterTarget target = (ClusterTarget) x;
                return target.tagAlliance == DashboardConstants.alliance;
              }

              return false;
            })
            .map(x -> (ClusterTarget) x)
            .collect(Collectors.toList());

    if (targets.isEmpty()) {
      return null;
    }

    Position currentPosition = drivetrain.getRobotPose().getPosition();

    ClusterTarget closest = targets.stream().findFirst().get();
    double closestDistance =
        PoseMath.distance2d(closest.targetPose.getPosition(), currentPosition, DistanceUnit.CM);

    for (ClusterTarget target : targets) {
      double currentDist =
          PoseMath.distance2d(target.targetPose.getPosition(), currentPosition, DistanceUnit.CM);

      if (currentDist < closestDistance) {
        closest = target;
        closestDistance = currentDist;
      }
    }

    return closest;
  }
}
