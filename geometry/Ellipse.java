package geometry;

public class Ellipse extends Shape{
  protected double semiMajor;
  protected double semiMinor;

  public Ellipse(double semiMajor, double semiMinor) {
    this.semiMajor = semiMajor;
    this.semiMinor = semiMinor;
  }

  public double getSemiMajor() {
    return this.semiMajor;
  }
  public void setSemiMajor(double semiMajor) {
    this.semiMajor = semiMajor;
  }
  public double getSemiMinor() {
    return this.semiMinor;
  }
  public void setSemiMinor(double semiMinor) {
    this.semiMinor = semiMinor;
  }
  public double getArea() {
    return Math.PI * this.semiMajor * this.semiMinor;
  }
  public double getCircumference() {
    return Math.PI * (this.semiMajor + this.semiMinor);
  }
  public void contraction(Vector2D gamma) {
	  setSemiMajor(getSemiMajor() * gamma.x);
	  setSemiMinor(getSemiMinor() * gamma.y);
  }
  
  public void draw(geometry.Vector2D position, double angularState, java.awt.Graphics g) {
	  g.drawOval((int)(position.x - this.getSemiMajor()), (int)(position.y - this.getSemiMinor()),
              (int)(2 * this.getSemiMajor()), (int)(2 * this.getSemiMinor()));
  }
}
