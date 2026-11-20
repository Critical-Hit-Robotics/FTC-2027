package org.firstinspires.ftc.teamcode.subsystems.vision.interfaces;

import org.firstinspires.ftc.teamcode.subsystems.vision.targets.VisionTarget;

import java.util.List;

public interface VisionInterface {

    default public void periodic() {

    }

    public List<Integer> getAprilTagIds();

    public List<VisionTarget> getTargets();

}
