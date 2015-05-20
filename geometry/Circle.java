package geometry;

public class Circle extends Ellipse{

  public Circle(double radius) {
    super(radius, radius);
  }

  public double getRadius() {
    return this.semiMajor;
  }
  public void setRadius(double radius) {
    this.semiMajor = radius;
    this.semiMinor = radius;
  }
  /*public void draw(geometry.Vector2D position, double angularState, java.awt.Graphics g) {
    g.drawOval((int)(position.x - this.getRadius()), (int)(position.y - this.getRadius()),
               (int)(2 * this.getRadius()), (int)(2 * this.getRadius()));
  }*/

}
