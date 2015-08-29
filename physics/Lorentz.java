package physics;

import simulator.Law;
import simulator.Universe;
import simulator.Simulation;
import java.util.Vector;
import objects.*;
import geometry.*;
import display.*;

/**
 * Relativistic motion simulation
 * Velocities in the lab frame are calculated using Lorentz transformations
 * Length contractions are also calculated and objects in the lab frame are
 * drawn accordingly.
 * 
 * @author Nazim Dugan
 */
public class Lorentz extends Simulation {

  protected static void startSimulation() {
    Universe universe = getUniverse();
    startNewTask(universe);
  }
  
  /*
   * Define universe with objects and laws
   */
  protected static Universe getUniverse() {
    Vector object = getObjects();
    Vector lawVector = getLaws();
    Law[] law = new Law[lawVector.size()];
    lawVector.toArray(law);
    Universe universe = new Universe(law, 1);
    for (int i=0; i < object.size(); i++) {
      universe.addComponent(object.get(i));
    }
    return universe;
  }
  
  /*
   * Define laws
   */
  protected static Vector getLaws() {
    Vector law = new Vector();

    law.add(new laws.Gravitation());
    //law.add(new laws.HarmonicOscillator(-1));
    //law.add(new laws.ConstantForce(1000.0));
    
    // Speed of light = 14.0
    laws.Motion relativistic = new laws.Motion(0.01, 14.0);
    law.add(relativistic);
    law.add(new laws.ElasticCollusion());
   
    LorentzDisplay restFrame = new 
    		LorentzDisplay("Rest Frame", 600, 600, 0, 0, 5, relativistic, new Vector2D(0.0, 0.0));
    law.add(restFrame);
    //LabFrame has a velocity (12.0, 0.0) relative to restFrame
    LorentzDisplay labFrame = new 
    		LorentzDisplay("Lab Frame", 600, 600, 630, 0, 5, relativistic, new Vector2D(12.0, 0.0));
    law.add(labFrame);
    
    law.add(new laws.Boundary(restFrame));
    law.add(new laws.PeriodicBoundaryCondition(labFrame));
    return law;
  }
  
  /*
   * Define objects
   */
  protected static Vector getObjects() {
    Vector object = new Vector();
    PhysicalObject o;
    
    o = new PhysicalObject(10000, new Circle(20));    
    o.setPosition(0, 0);
    o.setVelocity(-0.5,0);
    object.add(o);

    o = new PhysicalObject(500, new Circle(5));
    o.setPosition(0, -200);
    o.setVelocity(6,0);
    object.add(o);
    
    o = new PhysicalObject(500, new Circle(5));
    o.setPosition(0,-100);
    o.setVelocity(8,0);
    object.add(o);

    return object;
  }

  public static void main(String[] args) {
    startSimulation();
  }
}
