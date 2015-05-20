package geometry;

import java.util.Vector;
import geometry.Vector2D;

public class Polygon extends Shape{
  protected Vector corners = new Vector();
  protected Vector2D[] corner;
  protected Vector2D center;

  public Polygon() {

  }
  public Polygon(Vector2D[] corners) {
    for (int i=0; i < corners.length; i++) {
      this.corners.add(corners[i]);
    }
    this.setCornerArray();
  }

  public void addCorner(Vector2D c) {
    this.corners.add(c);
    this.setCornerArray();
  }
  public void removeCorner(int index) {
    this.corners.remove(index);
    this.setCornerArray();
  }
  public Vector2D getCorner(int index) {
    return (Vector2D)this.corners.get(index);
  }
  protected void setCornerArray() {
    this.corner = new Vector2D[this.corners.size()+1];
    this.corners.toArray(this.corner);
    corner[this.corners.size()] = (Vector2D)this.corners.get(0);
    this.setCenter();
  }
  protected void setCenter() {
    double area = this.getArea();
    double integral_xds = 0;
    for (int i=0; i < this.corners.size(); i++)
      integral_xds += (Math.pow(corner[i].x,2) + Math.pow(corner[i+1].x,2)
                       + corner[i].x * corner[i+1].x)*(corner[i+1].y - corner[i].y);
    integral_xds /= 6;
    double x0 = integral_xds / area;
    double integral_yds =0;
    for (int i=0; i < this.corners.size(); i++)
      integral_yds += (Math.pow(corner[i].y,2) + Math.pow(corner[i+1].y,2)
                       + corner[i].y * corner[i+1].y)*(corner[i+1].x - corner[i].x);
    integral_yds /= -6;
    double y0 = integral_yds / area;
    this.center = new Vector2D(x0,y0);
  }
  public Vector2D getCenter() {
    return this.center;
  }
  public double getArea() {
    double area =0;
    for (int i=0; i < this.corners.size(); i++)
      area += (corner[i].x + corner[i+1].x) * (corner[i+1].y - corner[i].y);
    return area/2;
  }
  public double getCircumference() {
    double circumference =0;
    for (int i=0; i < this.corners.size(); i++)
      circumference += corner[i].getDistance(corner[i+1]);
    return circumference;
  }
  public void contraction(Vector2D gamma) {
	  
  }
  public void draw(Vector2D position, double angularState, java.awt.Graphics g) {
    for (int i = 0; i < this.corners.size(); i++) {
      Vector2D corner1 = this.corner[i].add2(center.multiply2(-1));
      Vector2D corner2 = this.corner[i+1].add2(center.multiply2(-1));
      corner1.rotate(angularState).add(position).add(center);
      corner2.rotate(angularState).add(position).add(center);
      g.drawLine( (int) corner1.x, (int) corner1.y, (int) corner2.x,
                  (int) corner2.y);
    }
  }
}
