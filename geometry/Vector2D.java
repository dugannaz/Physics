package geometry;

public class Vector2D {
  public double x;
  public double y;

  public Vector2D() {

  }
  public Vector2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public Vector2D add(Vector2D v) {
    this.x += v.x;
    this.y += v.y;
    return this;
  }
  public Vector2D add2(Vector2D v) {
    return  new Vector2D(this.x + v.x, this.y + v.y);
  }
  
  public Vector2D addLorentz(Vector2D v, double c) {
	  this.x = (v.x+this.x)/(1.0+((v.x*this.x)/(c*c)));
	  this.y = (v.y+this.y)/(1.0+((v.y*this.y)/(c*c)));
	  return this;
  }
  
  public Vector2D addLorentz2(Vector2D v, double c) {
	    return  new Vector2D((v.x+this.x)/(1.0+((v.x*this.x)/(c*c))), 
	    					 (v.y+this.y)/(1.0+((v.y*this.y)/(c*c))));
  }
  
  public Vector2D multiply(double a) {
    this.x *= a;
    this.y *= a;
    return this;
  }
  public Vector2D multiply2(double a) {
    return new Vector2D(this.x * a, this.y * a);
  }
  
  public Vector2D multiplyVec(Vector2D a) {
	    this.x *= a.x;
	    this.y *= a.y;
	    return this;
	  }
  
  public double getDistance(Vector2D v) {
    return this.add2(v.multiply2(-1)).r();
  }
  
  public double r() {
    return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
  }
  
  public void setR(double r) {
    double teta = this.theta();
    this.x = r * Math.cos(teta);
    this.y = r * Math.sin(teta);
  }
  
  public double theta() {
    return Math.atan2(this.y, this.x);
  }
  public void setTheta(double theta) {
    this.rotate(theta - this.theta());
  }
  public double getAngle(Vector2D a) {
    return this.theta() - a.theta();
  }
  public Vector2D rotate(double angle) {
    return this.cartesianToRadial().rotateRadial(angle).radialToCartesian();
  }
  public Vector2D rotate2(double angle) {
    Vector2D radialV = this.cartesianToRadial2();
    return radialV.rotateRadial(angle).radialToCartesian();
  }
  private Vector2D rotateRadial(double angle) {
    this.y += angle;
    return this;
  }
  private Vector2D rotateRadial2(double angle) {
    return new Vector2D(this.x, this.y + angle);
  }
  private Vector2D radialToCartesian() {
    double x = this.x * Math.cos(this.y);
    this.y = this.x * Math.sin(this.y);
    this.x = x;
    return this;
  }
  private Vector2D radialToCartesian2() {
    return new Vector2D(this.x * Math.cos(this.y), this.x * Math.sin(this.y));
  }
  private Vector2D cartesianToRadial() {
    double r = this.r();
    this.y = Math.atan2(this.y, this.x);
    this.x = r;
    return this;
  }
  private Vector2D cartesianToRadial2() {
    double r = this.r();
    double theta = Math.atan2(this.y, this.x);
    return new Vector2D(r, theta);
  }
}
