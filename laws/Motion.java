package laws;

import objects.LocalizedObject;
import geometry.Vector2D;


/**
 * Sets the position of a localized object by Euler method. This method assumes that
 * the object goes on a straight path during a timeInterval
 * @author Nazim Dugan
 * @version 1.0
 */
public class Motion extends simulator.Law {
  public double timeInterval;
  protected double c; // speed of light

  public Motion(double timeInterval, double c) {
    this.setInputType("objects.LocalizedObject");
    this.timeInterval = timeInterval;
    this.c = c;
  }

  public double getC() {
	  return c;
  }
  
  public void itterate(java.util.Vector objects) {
    for (int i=0; i < objects.size(); i++) {
      LocalizedObject o = (LocalizedObject) getObject(objects, i);
      o.getVelocity().addLorentz(o.getAcceleration().multiply(timeInterval), c);
      o.getPosition().add(o.getVelocity().multiply2(timeInterval));
      o.setAcceleration(new Vector2D());
      o.setAngularVelocity(o.getAngularVelocity() + o.getAngularAcceleration() * timeInterval);
      o.setAngularState(o.getAngularState() + o.getAngularVelocity() * timeInterval);
      o.setAngularAcceleration(0);
    }
  }
}
