package simulator;

/**
 * A class which extends the class <code>God</code> is the starting
 * point of a simulation program.
 * @author Nazim Dugan
 * @version 1.0
 */
public class Simulation {

  /**
   * Main body of the simulation program A class which extends this class
   * should override this method.
   */
  protected static void startSimulation() {
	  
  }

  /**
   * Starts a new thread using the Runnable argument r
   * @param r Runnable
   */
  protected static void startNewTask(Runnable r) {
    new Thread(r).start();
  }

}