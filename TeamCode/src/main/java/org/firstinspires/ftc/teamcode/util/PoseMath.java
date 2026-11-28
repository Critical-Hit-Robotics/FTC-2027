package org.firstinspires.ftc.teamcode.util;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

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

  public static Position advance2d(Position a, Velocity v, double millis, DistanceUnit unit) {
    Position p1 = a.toUnit(unit);
    Velocity v1 = v.toUnit(unit);

    double x = p1.x + v1.xVeloc * millis / 1000;
    double y = p1.y + v1.yVeloc * millis / 1000;

    return new Position(unit, x, y, p1.z, a.acquisitionTime);
  }
}
