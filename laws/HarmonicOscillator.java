package laws;

import geometry.Vector2D;
import objects.PhysicalObject;

/**
 * Applies a force on a physical object due to all the other physical objects
 * in the Universe. This force is proportional to the distance between the objects.
 * @author Nazim Dugan
 * @version 1.0
 */
public class HarmonicOscillator extends simulator.Law {
  protected double k = -1.0;

  public HarmonicOscillator() {
    this.setInputType("objects.PhysicalObject");
  }
  public HarmonicOscillator(double k) {
    this.setInputType("objects.PhysicalObject");
    this.k = k;
  }

  public void itterate(java.util.Vector objects) {
    for (int i =0; i < objects.size(); i++) {
      PhysicalObject o1 = (PhysicalObject)getObject(objects, i);
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
    Vector2D distance = o2.getPosition().add(o1.getPosition().multiply(-1));
    double teta = distance.theta();
    double magnitude = this.k * distance.r();
    return new Vector2D(magnitude * Math.cos(teta),magnitude * Math.sin(teta));
  }
}
