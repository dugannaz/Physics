package laws;

import objects.LocalizedObject;;

public class Display extends simulator.Law {
  DisplayCanvas canvas;
  int sleep;
  int count;

  public Display(int length, int heigth, int sleep) {
    this.setInputType("objects.Drawable");
    this.canvas = new DisplayCanvas(length, heigth);
    this.sleep = sleep;
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
