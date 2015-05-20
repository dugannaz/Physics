package simulator;

/**
 * Instances of this class makes an object a component of a Universe.
 * @author Nazim Dugan
 * @version 1.0
 */
public class Component {
  /**
   * Existance status of this component. When this field becomes false all the
   * references to this component are deleted.
   */
  protected boolean existance = true;

  /**
   * The Object represented by this component
   */
  protected Object object;

  /**
   * Constracts a component with the argument object.
   * @param o Object
   */
  public Component(Object object) {
    this.object = object;
  }

  /**
   * Returns the existance status of this component.
   * @return boolean
   */
  public boolean doesExist() {
    return this.existance;
  }

  /**
   * Returns the object represented by this component.
   * @return Object
   */
  public Object getObject() {
    return this.object;
  }

  /**
   * Sets the existance status of this component to <code>false</code>.
   */
  public void disappear() {
    this.existance = false;
  }
}
