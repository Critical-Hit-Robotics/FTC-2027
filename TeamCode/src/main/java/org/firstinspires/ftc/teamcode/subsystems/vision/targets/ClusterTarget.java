package org.firstinspires.ftc.teamcode.subsystems.vision.targets;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import java.util.List;

public final class ClusterTarget extends VisionTarget {
    public final String name;
    public final double fractionVisible;


    public ClusterTarget(long timestampNanoseconds, Pose3D targetPose, String name, double fractionVisible) {
        super(timestampNanoseconds, targetPose);

        this.name = name;
        this.fractionVisible = fractionVisible;
    }
}