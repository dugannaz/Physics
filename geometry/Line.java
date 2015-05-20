package geometry;

import geometry.Vector2D;

public class Line {
  public Vector2D end1;
  public Vector2D end2;

  public Line(Vector2D end1) {
    this.end1 = end1;
    this.end2 = new Vector2D();
  }
  public Line(Vector2D end1, Vector2D end2) {
    this.end1 = end1;
    this.end2 = end2;
  }

  public double length() {
    return this.end1.getDistance(this.end2);
  }

  public Vector2D intersection(Line line) {
    double x = (this.end1.x * (line.end2.x * (line.end1.y - this.end2.y) +
      line.end1.x * (this.end2.y - line.end2.y)) + this.end2.x * (line.end2.x *
      (this.end1.y - line.end1.y) + line.end1.x * (line.end2.y - this.end1.y))) /
      ( - (line.end1.x - line.end2.x) * (this.end1.y - this.end2.y) +
      (this.end1.x - this.end2.x) * (line.end1.y - line.end2.y));

    double y = (line.end2.x*(this.end1.y-this.end2.y)*line.end1.y +
      this.end1.x*this.end2.y*line.end1.y - line.end1.x*this.end1.y*line.end2.y -
      this.end1.x*this.end2.y*line.end2.y + line.end1.x*this.end2.y*line.end2.y +
      this.end2.x*this.end1.y*(line.end2.y-line.end1.y))/
      (-(line.end1.x - line.end2.x)*(this.end1.y - this.end2.y) +
      (this.end1.x - this.end2.x)*(line.end1.y - line.end2.y));

    double xmin1 = Math.min(this.end1.x, this.end2.x);
    double xmax1 = Math.max(this.end1.x, this.end2.x);
    double ymin1 = Math.min(this.end1.y, this.end2.y);
    double ymax1 = Math.max(this.end1.y, this.end2.y);
    double xmin2 = Math.min(line.end1.x, line.end2.x);
    double xmax2 = Math.max(line.end1.x, line.end2.x);
    double ymin2 = Math.min(line.end1.y, line.end2.y);
    double ymax2 = Math.max(line.end1.y, line.end2.y);

    if (x >= xmin1 && x <= xmax1 && x >= xmin2  && x <= xmax2 && y >= ymin1 &&
        y <= ymax1 && y >= ymin2  && y <= ymax2)
      return new Vector2D(x,y);
    else return null;
  }


}
