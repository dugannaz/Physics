package laws;

import geometry.Vector2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Canvas;
import java.awt.Color;

import objects.Drawable;
import objects.LocalizedObject;

import java.awt.Graphics;

public class DisplayCanvas extends JPanel {
  LocalizedObject[]  object = new LocalizedObject[0];
  LocalizedObject[] refObject;
  Vector2D position;

  public DisplayCanvas(int length, int heigth) {
	position = new Vector2D(300, 300);
    this.setBackground(new Color(0,0,0));
    this.setSize(length, heigth);
    this.setVisible(true);
    JFrame frame = new JFrame();
    frame.getContentPane().setLayout(null);
    frame.setSize(length+30, heigth+30);
    frame.getContentPane().add(this);
    frame.show();
  }

  public void draw(LocalizedObject[] object) {
    this.object = object;
    if (refObject==null) {
    	refObject = new LocalizedObject[object.length];
    	for (int i=0; i < object.length; i++)
    		refObject[i] = (LocalizedObject)common.Methods.clone(object[i]);
    } else {
    	for (int i=0; i < refObject.length; i++) {
    		refObject[i].setPosition(object[i].getPosition().add2(position));
    	}
    }
    this.repaint();
  }
  public void paintComponent(Graphics g) {
	  
	super.paintComponent(g);
	  
    g.setColor(new Color(225,225,225));
    for (int i=0; i < this.refObject.length; i++) {
      this.refObject[i].draw(g);
    }
  }
}
