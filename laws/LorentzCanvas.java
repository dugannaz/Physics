package laws;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import objects.LocalizedObject;

import java.awt.Graphics;

import geometry.Vector2D;

public class LorentzCanvas extends JPanel {
  LocalizedObject[] object = new LocalizedObject[0];
  LocalizedObject[] refObject;
  Vector2D velocity;
  Vector2D origin;
  Vector2D gammaVec;
  Motion motion;
  double t;
  Graphics g;

  public LorentzCanvas(String title, int length, int height, int locx, int locy, Motion motion, Vector2D vel) {
	this.motion = motion;
	velocity = vel;
	origin = new Vector2D(length/2, height/2);
	double gamma=velocity.x/motion.c;
	gamma = Math.sqrt(1.0-gamma*gamma);
	gammaVec = new Vector2D(gamma,1.0);
	t=0.0;
    this.setBackground(new Color(0,0,0));
    this.setSize(length, height);
    this.setVisible(true);
    JFrame frame = new JFrame();
    frame.getContentPane().setLayout(null);
    frame.setSize(length+30, height+30);
    frame.getContentPane().add(this);
    frame.setLocation(locx, locy);
    frame.setTitle(title);
    frame.setVisible(true);
  }

  public void draw(LocalizedObject[] object) {
	t+=motion.timeInterval;
    this.object = object;
    if (refObject==null) {
    	refObject = new LocalizedObject[object.length];
    	for (int i=0; i < object.length; i++) {
    		refObject[i] = (LocalizedObject)common.Methods.clone(object[i]);
    		refObject[i].contraction(gammaVec);
    	}
    	
    } else {
    	
    	for (int i=0; i < refObject.length; i++) {
    		refObject[i].setPosition(object[i].getPosition().add2(velocity.multiply2(t)));
    		refObject[i].getPosition().multiplyVec(gammaVec).add(origin);
    		//refObject[i].getPosition().add(origin);
    	}
    }
 
    this.repaint();
  }
  
  public void paintComponent( Graphics g ) {
      
      super.paintComponent(g);
       
      g.setColor(new Color(225,225,225));
      for (int i=0; i < refObject.length; i++) {
        this.refObject[i].draw(g);
      }
  }
  
  
}
