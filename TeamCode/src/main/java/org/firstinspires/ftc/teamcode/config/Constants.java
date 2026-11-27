package org.firstinspires.ftc.teamcode.config;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.util.CurrentAlliance;

public class Constants {

  @Config
  public static class DashboardConstants {
    public static CurrentAlliance alliance = CurrentAlliance.BLUE;
  }

  public static class ControlHubConstants {
    public static final String voltageSensorKey = "";
  }

  public static class ServoIOConstants {
    public static final int kMotorTicksToRotation = 8190;
    public static final int kMaximumDegrees = 270;
  }

  public static class GoBuildaMotorConstants {
    public static final double motorTicksToDegrees = 30;
  }

  public static class DriveConstants {
    public static final String frontLeftDriveKey = "";
    public static final String frontRightDriveKey = "";
    public static final String backLeftDriveKey = "";
    public static final String backRightDriveKey = "";

    public static final String IMUKey = "";
  }

  public static class VisionConstants {
    public static enum VisionType {
      WEBCAM,
      LIMELIGHT,
      NATIVE
    }

    public static final String webcamKey = "";
    public static final String limelightKey = "";

    public static final VisionType visionType = VisionType.WEBCAM;
  }

  public static class IntakeConstants {
    public static final String rollerMotorLeftKey = "";
    public static final String rollerMotorRightKey = "";

    public static final double ROLLER_GEAR_RATIO = 5;
    public static final double OUTTAKE_GEAR_RATIO = 3;

    public static final double ROLLER_TARGET_VOLTAGE = 6;
  }

  public static class ShooterConstants {
    public static final String frontFlywheelKey = "";
    public static final String backFlywheelKey = "";

    public static class TurretConstants {
      public static final PIDCoefficients azimuthPIDCoefficients = new PIDCoefficients(0, 0, 0);
      public static final String leftServoKey = "";
      public static final String rightServoKey = "";
      public static final String azimuthMotorKey = "";

      public static final double servoToAzimuthGearRatio = 30.0 / 2.0;

      public static final int kEncoderTicksToDegrees = 4000;
      public static final int kServoTicksToDegrees = 4000;

      public static boolean isValidAngle(double inputAngle) {
        return AngleUnit.DEGREES.normalize(inputAngle) > 30;
      }
    }
  }

  public static class OuttakeConstants {
    public static final String outtakeMotorKey = "";
  }
}
