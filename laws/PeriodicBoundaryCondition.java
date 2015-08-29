package laws;

import geometry.Vector2D;
import objects.LocalizedObject;

/**
 * Sets the boundaries of the Universe.
 * @author Nazim Dugan
 * @version 1.0
 */
public class PeriodicBoundaryCondition extends simulator.Law {
  double sizeX;
  double sizeY;
  simulator.Display display;

  public PeriodicBoundaryCondition(simulator.Display display) {
	  sizeX = display.getDimensions().x;
	  sizeY = display.getDimensions().y;
	  this.display = display;
	  this.setInputType("objects.LocalizedObject");
  }

  public void itterate(java.util.Vector objects) {
    for (int i=0; i < objects.size(); i++) {
      //LocalizedObject o = (LocalizedObject)getObject(objects, i);
      LocalizedObject o = display.getObject(i);
      if (o != null) {
    	  Vector2D p = o.getPosition();
    	  if (p.x < 0) {
    		  int div=(int)(p.x/sizeX);
    		  p.x += (div+1)*sizeX;
    	  }
    	  if (p.y < 0) {
    		  int div=(int)(p.y/sizeY);
    		  p.y += (div+1)*sizeY;
    	  }
    	  p.x = p.x % sizeX;
    	  p.y = p.y % sizeY;
      }
    }
  }
}
