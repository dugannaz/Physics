package physics;

import simulator.Law;
import simulator.Universe;
import simulator.Simulation;

import java.util.Vector;

import objects.*;
import geometry.*;

/**
 * Relativistic motion simulation
 * Velocities in the lab frame are calculated using Lorentz transformations.
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

    //law.add(new laws.HarmonicOscillator(-1));
    law.add(new laws.Gravitation());
    //law.add(new laws.ConstantForce(1000.0));
    
    // Speed of light = 100.0
    laws.Motion relativistic = new laws.Motion(0.01, 100.0);
    law.add(relativistic);
    law.add(new laws.ElasticCollusion());
   
    //law.add(new laws.Display(600, 600, 60));
    laws.LorentzDisplay restFrame = new 
    		laws.LorentzDisplay("Rest Frame", 600, 600, 0, 0, 60, relativistic, new Vector2D(0.0, 0.0));
    law.add(restFrame);
    //law.add(new laws.PeriodicBoundaryCondition(restFrame));
    laws.LorentzDisplay labFrame = new 
    		laws.LorentzDisplay("Lab Frame", 600, 600, 600, 0, 60, relativistic, new Vector2D(90.0, 0.0));
    law.add(labFrame);
    
    law.add(new laws.Boundary(restFrame));
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
    o.setVelocity(10,0);
    object.add(o);

    /*LocalizedObject lo = new LocalizedObject(null);
    Vector2D[] corners = new Vector2D[3];
    corners[0] = new Vector2D(0,0);
    corners[1] = new Vector2D(40,0);
    corners[2] = new Vector2D(0,40);
    Polygon p = new Polygon(corners);
    lo.setShape(p);
    lo.setPosition(200,150);
    lo.setVelocity(5,5);
    lo.setAngularVelocity(0.3);
    object.add(lo);*/

    return object;
  }

  public static void main(String[] args) {
    startSimulation();
  }
}
