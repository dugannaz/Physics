package objects;

import geometry.Vector2D;
import geometry.Shape;

public class LocalizedObject implements Drawable{
  protected Shape shape;
  protected Vector2D position = new Vector2D();
  protected Vector2D velocity = new Vector2D();
  protected Vector2D acceleration = new Vector2D();
  protected double angularState;
  protected double angularVelocity;
  protected double angularAcceleration;

  public LocalizedObject(Shape shape) {
    this.shape = shape;
  }
  public Shape getShape() {
    return this.shape;
  }
  public void setShape(Shape shape) {
    this.shape = shape;
  }
  public Vector2D getPosition() {
    return this.position;
  }
  public void setPosition(Vector2D p) {
    this.position = p;
  }
  public void setPosition(double x, double y) {
    this.position.x = x;
    this.position.y = y;
  }
  public Vector2D getVelocity() {
    return this.velocity;
  }
  public void setVelocity(Vector2D v) {
    this.velocity = v;
  }
  public void setVelocity(double x, double y) {
    this.velocity.x = x;
    this.velocity.y = y;
  }
  public Vector2D getAcceleration() {
    return this.acceleration;
  }
  public void setAcceleration(Vector2D v) {
    this.acceleration = v;
  }
  public void setAcceleration(double x, double y) {
    this.acceleration.x = x;
    this.acceleration.y = y;
  }

  public double getAngularState() {
    return this.angularState;
  }
  public void setAngularState(double angle) {
    this.angularState = angle;
  }
  public double getAngularVelocity() {
    return this.angularVelocity;
  }
  public void setAngularVelocity(double angle) {
    this.angularVelocity = angle;
  }
  public double getAngularAcceleration() {
    return this.angularAcceleration;
  }
  public void setAngularAcceleration(double angAcc) {
    this.angularAcceleration = angAcc;
  }

  public void contraction(Vector2D gamma) {
	  shape.contraction(gamma);
  }
  
  public void draw(java.awt.Graphics g) {
    if (this.shape !=null)
      this.shape.draw(this.position, this.angularState, g);
    else
      g.drawRect((int)this.position.x, (int)this.position.y, 0, 0);
  }
}
