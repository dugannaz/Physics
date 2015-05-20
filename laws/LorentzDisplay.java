package laws;

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
    this.canvas = new LorentzCanvas(title, length, height, locx, locy, motion.getC(), vel);
    this.sleep = sleep;
    dim = new Vector2D(length, height);
  }
  
  public Vector2D getDimensions() {
	  return dim;
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
}
