package laws;

import geometry.Vector2D;
import objects.PhysicalObject;

/*
 ** Applies a constant force on a physical object
 * @author Nazim Dugan
 * @version 1.0
 */
public class ConstantForce extends simulator.Law {

	protected double f = 1.0;
	
	public ConstantForce(double f) {
	    this.setInputType("objects.PhysicalObject");
	    this.f = f;
	  }
	
	public void itterate(java.util.Vector objects) {
	    for (int i =0; i < objects.size(); i++) {
	      PhysicalObject o1 = (PhysicalObject) getObject(objects, i);
	      Vector2D netForce = new Vector2D(f,0.0);
	      o1.getAcceleration().add(netForce.multiply(1.0 / Math.abs(o1.getMass())));
	    }
	  }
}
