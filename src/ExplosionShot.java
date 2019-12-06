import java.awt.Color;
import java.awt.Graphics;

public class ExplosionShot {
  int myX;
  int myY;
  int myX1;
  int myY1;
  int myX2;
  int myY2;
  int myX3;
  int myY3;
  int theta;
  float hue;
  float brightness = 1.0F;
  int moveDIST = 24;
  int moveDIST1 = 25;
  int thetaOFF;
  boolean big;
  boolean white = false;
  
  public ExplosionShot(int x, int y, int angle, float HUE) {
    this.myX = x;
    this.myY = y;
    this.myX1 = x;
    this.myY1 = y;
    this.myX2 = x;
    this.myY2 = y;
    this.myX3 = x;
    this.myY3 = y;
    this.theta = angle;
    this.hue = HUE;
    
    this.thetaOFF = ((int)(Math.random() * 25.0D));
  }
  
  public ExplosionShot(int x, int y, int angle, float HUE, boolean w) {
    this.myX = x;
    this.myY = y;
    this.myX1 = x;
    this.myY1 = y;
    this.myX2 = x;
    this.myY2 = y;
    this.myX3 = x;
    this.myY3 = y;
    this.theta = angle;
    this.hue = HUE;
    
    this.thetaOFF = ((int)(Math.random() * 25.0D));
    
    if (w) {
      this.white = true;
    }
  }
  
  public void act() {
    this.myX += (int)(this.moveDIST * Math.cos(Math.toRadians(this.theta)) + 0.5D);
    this.myY -= (int)(this.moveDIST * Math.sin(Math.toRadians(this.theta)) + 0.5D);
    this.myX1 += (int)(4.0D * Math.cos(Math.toRadians(this.theta)) + 0.5D);
    this.myY1 -= (int)(4.0D * Math.sin(Math.toRadians(this.theta)) + 0.5D);
    this.myX2 += (int)(10.0D * Math.cos(Math.toRadians(this.theta + this.thetaOFF)) + 0.5D);
    this.myY2 -= (int)(10.0D * Math.sin(Math.toRadians(this.theta + this.thetaOFF)) + 0.5D);
    this.myX3 += (int)(20.0D * Math.cos(Math.toRadians(this.theta + this.thetaOFF)) + 0.5D);
    this.myY3 -= (int)(20.0D * Math.sin(Math.toRadians(this.theta + this.thetaOFF)) + 0.5D);
    
    this.brightness -= 0.08F;
    
    this.moveDIST -= 1;
    this.moveDIST1 -= 1;
  }
  
  public void paint(Graphics gg) {
    if (this.brightness > 0.0F)
    {
      if (this.white) {
        gg.setColor(Color.getHSBColor(this.hue, 0.0F, this.brightness));
        gg.setColor(Color.MAGENTA);
      }
      else {
        gg.setColor(Color.getHSBColor(this.hue, 1.0F, this.brightness));
        gg.setColor(Color.MAGENTA);
      }
      
      if (this.hue == 0.17F) {
        gg.fillRect(this.myX1, this.myY1, 1, 1);
      } else {
        gg.fillRect(this.myX, this.myY, 1, 1);
        gg.fillOval(this.myX2, this.myY2, 3, 3);
        gg.fillRect(this.myX3, this.myY3, 1, 1);
      }
    }
  }
}