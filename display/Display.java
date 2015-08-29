package display;

import geometry.Vector2D;
import objects.LocalizedObject;;

public class Display extends simulator.Law implements simulator.Display {
  DisplayCanvas canvas;
  int sleep;
  int count;
  Vector2D dim;

  public Display(int length, int height, int sleep) {
    this.setInputType("objects.Drawable");
    this.canvas = new DisplayCanvas(length, height);
    this.sleep = sleep;
    dim = new Vector2D(length, height);
  }

  public Vector2D getDimensions() {
	  return dim;
  }

  public LocalizedObject getObject(int i) {
	  
	  if (canvas.refObject != null)
		  return canvas.refObject[i];
	  else
		  return null;
  }
  
  public void itterate(java.util.Vector objects) {
    this.count++;
    if (this.count < this.sleep) return;
    this.count = 0;
    LocalizedObject[] drawable = new LocalizedObject[objects.size()];
    for (int i=0; i < objects.size(); i++) {
      drawable[i] = (LocalizedObject)getObject(objects, i);
    }
    this.canvas.draw(drawable);
  }
  
public void draw() {
	  
	if (count == 0)
	  canvas.repaint();
  }
}
