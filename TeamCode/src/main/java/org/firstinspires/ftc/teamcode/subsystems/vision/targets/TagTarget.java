package org.firstinspires.ftc.teamcode.subsystems.vision.targets;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public final class TagTarget extends VisionTarget {
    public final int id;

    public TagTarget(long timestampNanoseconds, Pose3D targetPose, int id) {
        super(timestampNanoseconds, targetPose);
        this.id = id;
    }
}