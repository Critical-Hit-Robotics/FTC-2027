package org.firstinspires.ftc.teamcode.config;

import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

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

//        public static FollowerConstants followerConstants = new FollowerConstants()
//                .predictiveBrakingCoefficients(new PredictiveBrakingCoefficients(0.2, 0.0729150938, 0.00192608311))
//                .headingPIDFCoefficients(new PIDFCoefficients(2, 0, 0.1, 0.025))
//                .centripetalScaling(0);
//
//        public static MecanumConstants driveConstants = new MecanumConstants()
//                .maxPower(1)
//                .rightFrontMotorName("frontRight")
//                .rightRearMotorName("backRight")
//                .leftRearMotorName("backLeft")
//                .leftFrontMotorName("frontLeft")
//                .leftFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
//                .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
//                .rightFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
//                .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
//                .xVelocity(74.0662)
//                .yVelocity(55.7839);
//
//        public static PinpointConstants localizerConstants = new PinpointConstants()
//                .forwardPodY(4.091074485478439)
//                .strafePodX(-6.032020989365465)
//                .distanceUnit(DistanceUnit.INCH)
//                .hardwareMapName("pinpoint")
//                .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
//                .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
//                .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED);
//
//        public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);
//
//        public static Follower createFollower(HardwareMap hardwareMap) {
//            return new FollowerBuilder(followerConstants, hardwareMap)
//                    .mecanumDrivetrain(driveConstants)
//                    .pinpointLocalizer(localizerConstants)
//                    .pathConstraints(pathConstraints)
//                    .build();
//        }
    }

    public static class VisionConstants {
        public static final String webcamKey = "";
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
        }


    }


    public static class OuttakeConstants {
        public static final String outtakeMotorKey = "";
    }
}
