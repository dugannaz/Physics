package simulator;

import java.util.Vector;

/**
 * Class Universe is the engine of a simulation program. It applies
 * each law to each object which is compatible with the law.
 * @author Nazim Dugan
 * @version 1.0
 */
public class Universe implements Runnable {

  /**
   * Specifies the sleeping time between each iteration.
   */
  protected long sleep;

  /**
   * Each component of the vector component is a vector which holds the objects
   * competible with a specific law.
   */
  public Vector component = new Vector();

  /**
   * Laws of the Universe.
   */
  protected Law[] law;

  /**
   * Constructs a Universe with laws specified by argument law and a sleeping
   * value specified by the sleep argument.
   * @param law Law[]
   * @param sleep long
   */
  public Universe(Law[] law, long sleep) {
    this.law = law;
    this.sleep = sleep;
    for (int i=0; i < this.law.length; i++) {
      Vector v = new Vector();
      this.component.add(v);
    }
  }

  /**
   * Starting point of the thread in which an instance of the Universe class runs.
   */
  public void run() {
    this.evolve();
  }

  /**
   * Main body of the evolution of a Universe object. When this method returns,
   * universe dies.
   * @return boolean
   */
  public boolean evolve() {
    boolean doom = false;
    while (! doom) {
      try {
        Thread.currentThread().sleep(this.sleep);
      } catch (InterruptedException e) {}
      this.applyLaws();
    }
    return false;
  }

  /**
   * Applies each law to each object which is compatible with the law.
   */
  protected void applyLaws() {
   for (int i=0; i < this.law.length; i++) {
     Vector lawComponents = (Vector)this.component.get(i);
     //System.out.println("law " + i);
     this.getLaw(i).itterate(lawComponents);
   }
   
   //System.out.println("Laws applied!");
   
   for (int i=0; i < this.law.length; i++) {
	   	simulator.Law curLaw = getLaw(i);
	     if (curLaw instanceof simulator.Display) 
	        ((simulator.Display)(curLaw)).draw();
	   }
  }

 /**
   * Adds a new Object to the Universe. Determines the laws which are compatible
   * with this new Object.
   * @param o Object
   */
  public void addComponent(Object o) {
    for (int i=0; i < this.law.length; i++) {
      if (common.Methods.satisfy(o, this.getLaw(i).getInputType())) {
        Vector v = (Vector)this.component.get(i);
        Component c = new Component(o);
        v.add(c);
      }
    }
  }

  /**
   * Returns a law of the Universe.
   * @param lawIndex int
   * @return Law
   */
  public Law getLaw(int lawIndex) {
    return this.law[lawIndex];
  }
}