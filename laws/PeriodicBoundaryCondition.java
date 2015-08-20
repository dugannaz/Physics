package laws;

import geometry.Vector2D;
import objects.LocalizedObject;

/**
 * Sets the boundaries of the Universe.
 * @author Nazim Dugan
 * @version 1.0
 */
public class PeriodicBoundaryCondition extends simulator.Law {
  double size;

  public PeriodicBoundaryCondition(simulator.Display display) {
	  size = display.getDimensions().x;
	  this.setInputType("objects.LocalizedObject");
  }

  public void itterate(java.util.Vector objects) {
    for (int i=0; i < objects.size(); i++) {
      LocalizedObject o = (LocalizedObject)getObject(objects, i);
      Vector2D p = o.getPosition();
      Vector2D v = o.getVelocity();
      if (p.x < -size/2) p.x += size;
      else if (p.x > size/2) p.x -= size;
      if (p.y < -size/2) p.y += size;
      else if (p.y > size/2) p.y -= size;
    }
  }
}
