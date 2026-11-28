package org.firstinspires.ftc.teamcode.util;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;

public class PoseMath {
  public static double distance(Position a, Position b, DistanceUnit unit) {
    Position p1 = a.toUnit(unit);
    Position p2 = b.toUnit(unit);

    return Math.sqrt(
        Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2) + Math.pow((p1.z - p2.z), 2));
  }

  // Flat (field-floor) distance, ignoring height:
  public static double distance2d(Position a, Position b, DistanceUnit unit) {
    Position p1 = a.toUnit(unit);
    Position p2 = b.toUnit(unit);

    return Math.hypot(p1.x - p2.x, p1.y - p2.y);
  }

  public static Position advance2d(Position a, Position b, DistanceUnit unit) {
    Position p1 = a.toUnit(unit);
    Position p2 = b.toUnit(unit);

    double x = p1.x + p2.x;
    double y = p1.y + p2.y;

    return new Position(unit, x, y, p1.z, a.acquisitionTime);
  }
}
