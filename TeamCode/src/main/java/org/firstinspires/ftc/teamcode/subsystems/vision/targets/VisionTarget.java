package org.firstinspires.ftc.teamcode.subsystems.vision.targets;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;

public abstract class VisionTarget {
    public final long timestampNanoseconds;
    public final Pose3D targetPose;
    public final double fractionVisible;

    protected VisionTarget(long timestampNanoseconds, Pose3D targetPose, double fractionVisible) {
        this.timestampNanoseconds = timestampNanoseconds;
        this.targetPose = targetPose;
        this.fractionVisible = fractionVisible;
    }
}


