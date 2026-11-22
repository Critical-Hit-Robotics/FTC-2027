package org.firstinspires.ftc.teamcode.subsystems.vision.targets;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import java.util.List;

public final class ClusterTarget extends VisionTarget {
    public enum ClusterTargetAlliance {
        Red, Blue
    }

    public final String tagName;
    public final ClusterTargetAlliance tagAlliance;

    // Can be null
    public final List<TagTarget> foundTargets;

    public ClusterTarget(long timestampNanoseconds, Pose3D targetPose, String tagName, ClusterTargetAlliance tagAlliance, List<TagTarget> foundTargets, int totalTargets) {
        super(timestampNanoseconds, targetPose, foundTargets.size() / (double) totalTargets);

        this.tagName = tagName;
        this.tagAlliance = tagAlliance;
        this.foundTargets = foundTargets;
    }

    public ClusterTarget(long timestampNanoseconds, Pose3D targetPose, String tagName, ClusterTargetAlliance tagAlliance, double fractionVisible) {
        super(timestampNanoseconds, targetPose, fractionVisible);

        this.tagName = tagName;
        this.tagAlliance = tagAlliance;
        this.foundTargets = null;
    }

}