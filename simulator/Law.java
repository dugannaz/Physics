package simulator;

import java.util.Vector;
import simulator.Component;

/**
 * A Class which extends this Class takes a set of Objects as input and changes
 * some values of some objects from the input set according to a rule
 * which is a function of all the objects in the set.
 * @author Nazim Dugan
 * @version 1.0
 */
abstract public class Law {

  /**
   * Determines the type of the objects which are in the input set of the law.
   */
  protected Class inputType;

  /**
   * Constructs an instance of the law with specified Class.
   */
  public Law() {
    this.setInputType("java.lang.Object");
  }

  /**
   * Returns the input type of the class.
   * @return Class
   */
  public Class getInputType() {
    return inputType;
  }

  /**
   * Sets the input type of the class.
   * @param inputType String
   * @throws java.lang.ClassNotFoundException e
   */
  protected void setInputType(String inputType) {
    try {
      this.inputType = Class.forName(inputType);
    } catch (java.lang.ClassNotFoundException e) {
      System.out.println("Class " + inputType + " not found !");
    }
  }

  /**
   * Returns an Object specified by the argument index from the input set of the
   * law.
   * @param v Vector
   * @param index int
   * @return Object
   */
  protected Object getObject(Vector objects, int index) {
    Component c = null;
    boolean check = true;
    while (check) {
      check = false;
      c = (Component)objects.get(index);
      if (c.doesExist() == false) {
        objects.remove(index);
        check = true;
      }
    }
    return c.getObject();
  }

  /**
   * Main body of the rule of the law.
   * @param object Vector
   * @param index int
   * @param timeInterval double
   */
  abstract public void itterate(Vector objects);

}