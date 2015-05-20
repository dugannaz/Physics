package objects;

public class PhysicalObject extends LocalizedObject{
  protected double mass;

  public PhysicalObject(double mass, geometry.Shape shape) {
    super(shape);
    this.mass = mass;
  }

  public double getMass() {
    return this.mass;
  }
  public void setMass(double mass) {
    this.mass = mass;
  }

}
