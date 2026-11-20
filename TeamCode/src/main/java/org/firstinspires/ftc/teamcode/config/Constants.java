package org.firstinspires.ftc.teamcode.config;

import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Constants {
    public static class ControlHubConstants {
        public static final String voltageSensorKey = "";
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
            WEBCAM, LIMELIGHT, NATIVE
        }

        public static final String webcamKey = "";

        public static final VisionType visionType = VisionType.WEBCAM;

        public static final int[][] RED_SCORING_TAGS = {{30, 31}, {32, 33}};
        public static final int[][] BLUE_SCORING_TAGS = {{42, 43}, {44, 45}};

        public static final int[][] BLUE_AUDIENCE_TAGS = {};
        public static final int[][] RED_AUDIENCE_TAGS = {};
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
            public static final String azimuthEncoder = "";

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
