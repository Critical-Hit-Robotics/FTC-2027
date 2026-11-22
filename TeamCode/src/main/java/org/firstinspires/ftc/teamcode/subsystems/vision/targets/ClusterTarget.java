package org.firstinspires.ftc.teamcode.subsystems.vision.targets;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.subsystems.vision.VisionSubsystem;

import java.util.List;

public final class ClusterTarget extends VisionTarget {
    public final String tagName;
    public final VisionSubsystem.CurrentAlliance tagAlliance;

    // Can be null
    public final List<TagTarget> foundTargets;

    public ClusterTarget(long timestampNanoseconds, Pose3D targetPose, String tagName, VisionSubsystem.CurrentAlliance tagAlliance, List<TagTarget> foundTargets, int totalTargets) {
        super(timestampNanoseconds, targetPose, foundTargets.size() / (double) totalTargets);

        this.tagName = tagName;
        this.tagAlliance = tagAlliance;
        this.foundTargets = foundTargets;
    }

    public ClusterTarget(long timestampNanoseconds, Pose3D targetPose, String tagName, VisionSubsystem.CurrentAlliance tagAlliance, double fractionVisible) {
        super(timestampNanoseconds, targetPose, fractionVisible);

        this.tagName = tagName;
        this.tagAlliance = tagAlliance;
        this.foundTargets = null;
    }

}