package display;

import objects.LocalizedObject;
import laws.Motion;
import geometry.Vector2D;

public class LorentzDisplay extends simulator.Law implements simulator.Display {
  LorentzCanvas canvas;
  int sleep;
  int count;
  Vector2D dim;

  public LorentzDisplay(String title, int length, int height, int locx, int locy, int sleep, Motion motion, Vector2D vel) {
    this.setInputType("objects.Drawable");
    this.canvas = new LorentzCanvas(title, length, height, locx, locy, motion, vel, sleep);
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
    count++;
    //System.out.println("Count " + count);
    if (count < sleep) return;
    count = 0;
    LocalizedObject[] drawable = new LocalizedObject[objects.size()];
    for (int i=0; i < objects.size(); i++) {
      drawable[i] = (LocalizedObject)getObject(objects, i);
    }
    canvas.reference(drawable);
  }
  
  public void draw() {
	  
	  if (count == 0) {
	    //System.out.println("Repaint");
	  	canvas.repaint();
	  }
  }
}
