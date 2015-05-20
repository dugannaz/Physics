package laws;

import geometry.Vector2D;
import objects.PhysicalObject;

/**
 * Applies a force on a physical object due to all the other physical objects
 * in the Universe. This force is inversely proportional to the distance square.
 * @author Nazim Dugan
 * @version 1.0
 */
public class Gravitation extends simulator.Law {
  protected double G = 1.0;

  public Gravitation() {
    this.setInputType("objects.PhysicalObject");
  }
  public Gravitation(double G) {
    this.setInputType("objects.PhysicalObject");
    this.G = G;
  }

  public void itterate(java.util.Vector objects) {
    for (int i =0; i < objects.size(); i++) {
      PhysicalObject o1 = (PhysicalObject) getObject(objects, i);
      if (Math.abs(o1.getMass()) < 0.000000000001)return;
      PhysicalObject o2;
      Vector2D netForce = new Vector2D();
      for (int j = 0; j < objects.size(); j++) {
        if (j != i) {
          o2 = (PhysicalObject)getObject(objects, j);
          netForce.add(this.getForce(o1, o2));
        }
      }
      o1.getAcceleration().add(netForce.multiply(1.0 / Math.abs(o1.getMass())));
    }
  }
  protected Vector2D getForce(PhysicalObject o1, PhysicalObject o2) {
    Vector2D distance = o2.getPosition().add2(o1.getPosition().multiply2(-1));
    double teta = distance.theta();
    double magnitude = this.G * o1.getMass() * o2.getMass() / Math.pow(distance.r(),2);
    return new Vector2D(magnitude * Math.cos(teta),magnitude * Math.sin(teta));
  }
}
