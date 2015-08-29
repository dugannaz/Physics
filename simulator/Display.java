package simulator;

import geometry.Vector2D;
import objects.LocalizedObject;

public interface Display {

	public Vector2D getDimensions();
	public LocalizedObject getObject(int i);
	public void draw(); 
}
