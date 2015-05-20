package laws;

import objects.PhysicalObject;
import geometry.Vector2D;

/**
 * Calculates the final velocities of two crashing physical objects by applying
 * energy conservation and momentum conservation.
 * @author Nazim Dugan
 * @version 1.0
 */
public class ElasticCollusion extends simulator.Law{

  public ElasticCollusion() {
    this.setInputType("objects.PhysicalObject");
  }

  public void itterate(java.util.Vector objects) {
    for (int i=0; i < objects.size(); i++) {
      PhysicalObject o1 = (PhysicalObject)getObject(objects, i);
      if (Math.abs(o1.getMass()) < 0.00000001) return;
      PhysicalObject o2;
      for (int j = i+1; j < objects.size(); j++) {
        o2 = (PhysicalObject)getObject(objects, j);
        checkCrashing(o1, o2);
      }
    }
  }
  protected static void checkCrashing(PhysicalObject o1, PhysicalObject o2) {
    double distance = o1.getPosition().getDistance(o2.getPosition());
    geometry.Circle c = (geometry.Circle)o1.getShape();
    double r1 = c.getRadius();
    c = (geometry.Circle)o2.getShape();
    double r2 = c.getRadius();
    if (distance < r1 + r2) crash(o1, r1, o2, r2);
  }

  protected static void crash(PhysicalObject o1, double r1, PhysicalObject o2, double r2) {
    double m1 = o1.getMass();
    double m2 = o2.getMass();
    Vector2D p1 = o1.getPosition();
    Vector2D p2 = o2.getPosition();
    Vector2D v1 = o1.getVelocity();
    Vector2D v2 = o2.getVelocity();

    /* Transformation to Center of mass frame */
    double transformationX = - (m1 * v1.x + m2 * v2.x) / (m1 + m2);
    double transformationY = - (m1 * v1.y + m2 * v2.y) / (m1 + m2);
    Vector2D transformation = new Vector2D(transformationX, transformationY);
    v1.add(transformation);
    v2.add(transformation);

    /* Rotation of velocity vectors */
    Vector2D posDiff = new Vector2D(p2.x - p1.x, p2.y - p1.y);
    double angle = 2.0 * posDiff.getAngle(v1) - Math.PI;
    v1.rotate(angle);
    v2.rotate(angle);

    /* Transformation back to canvas frame */
    v1.add(transformation.multiply(-1.0));
    v2.add(transformation);

    /* Arrangement of position */
    double distance = p1.getDistance(p2);
    double theta = posDiff.theta();
    double x = (-r1 - r2 + distance) * Math.cos(theta);
    double y = (-r1 - r2 + distance) * Math.sin(theta);
    p1.add(new Vector2D(x, y));
  }
}
