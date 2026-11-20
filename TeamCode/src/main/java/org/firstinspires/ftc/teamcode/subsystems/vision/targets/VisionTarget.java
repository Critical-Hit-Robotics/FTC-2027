package org.firstinspires.ftc.teamcode.subsystems.vision.targets;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;

public abstract class VisionTarget {
    public final long timestampNanoseconds;
    public final Pose3D targetPose;

    protected VisionTarget(long timestampNanoseconds, Pose3D targetPose) {
        this.timestampNanoseconds = timestampNanoseconds;
        this.targetPose = targetPose;
    }


    public double getDistance() {
        Position position = targetPose.getPosition();
        return Math.hypot(position.x, position.y);
    }
}


