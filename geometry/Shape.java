package geometry;

import geometry.Vector2D;

abstract public class Shape implements objects.Drawable {

  public Shape() {
  }

  abstract public double getArea();
  abstract public double getCircumference();
  abstract public void draw(Vector2D position, double angularState, java.awt.Graphics g);
  abstract public void contraction(Vector2D gamma);
  
  public void draw(java.awt.Graphics g) {
    this.draw(new Vector2D(), 0, g);
  }
  public void draw(Vector2D position, java.awt.Graphics g) {
    this.draw(position, 0, g);
  }
  public void draw(double angularState, java.awt.Graphics g) {
    this.draw(new Vector2D(), angularState, g);
  }

}
