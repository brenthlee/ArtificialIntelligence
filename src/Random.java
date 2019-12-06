import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Random
{
  int myX;
  int myY;
  int originX;
  int originY;
  int theta;
  int moveDIST = 2;
  
  public Random(int x, int y) {
    this.myX = x;
    this.myY = y;
    this.originX = x;
    this.originY = y;
  }
  
  public void act(Player p) {
    if ((this.myX != this.originX) || (this.myY != this.originY)) {
      double xdiff = this.myX - this.originX;
      int ydiff = this.myY - this.originY;
      
      this.theta = ((int)(Math.toDegrees(Math.atan(ydiff / xdiff)) + 0.5D) * -1);
      
      this.theta += 180;
      
      if (this.myX < this.originX) {
        this.theta += 180;
      }
      
      if (this.theta > 360) {
        this.theta -= 360;
      }
      
      this.myX += (int)(this.moveDIST * Math.cos(Math.toRadians(this.theta)));
      this.myY -= (int)(this.moveDIST * Math.sin(Math.toRadians(this.theta)));
    }
    
    for (Shot s : p.Shots) {
      if ((s.count > 1) && ((this.myX < s.myX + 15) || (this.myX > s.myX - 15) || (this.myY < s.myY + 15) || (this.myY > s.myY - 15)) && ((int)Math.sqrt((this.myX - s.myX + 1) * (this.myX - s.myX + 1) + (this.myY - s.myY + 1) * (this.myY - s.myY + 1)) < 45))
      {
        double xdifference = this.myX - (s.myX + 1);
        int ydifference = this.myY - (s.myY + 1);
        
        int moveTheta = (int)(Math.toDegrees(Math.atan(ydifference / xdifference)) + 0.5D) * -1;
        
        moveTheta += 180;
        
        if (this.myX < s.myX) {
          moveTheta += 180;
        }
        
        if (moveTheta > 360) {
          moveTheta -= 360;
        }
        
        moveTheta += 180;
        
        this.myX += (int)(4.0D * Math.cos(Math.toRadians(moveTheta)));
        this.myY -= (int)(4.0D * Math.sin(Math.toRadians(moveTheta)));
      }
    }
    

    if (p.Bombs.size() == 1) {
      int dist = (int)Math.sqrt((this.myX - ((Bomb)p.Bombs.get(0)).myX + 1) * (this.myX - ((Bomb)p.Bombs.get(0)).myX + 1) + (this.myY - ((Bomb)p.Bombs.get(0)).myY + 1) * (this.myY - ((Bomb)p.Bombs.get(0)).myY + 1));
      
      if ((dist < ((Bomb)p.Bombs.get(0)).radius) && (((Bomb)p.Bombs.get(0)).radius < 400)) {
        double xdifference = this.myX - (((Bomb)p.Bombs.get(0)).myX + 1);
        int ydifference = this.myY - (((Bomb)p.Bombs.get(0)).myY + 1);
        
        int moveTheta = (int)(Math.toDegrees(Math.atan(ydifference / xdifference)) + 0.5D) * -1;
        
        moveTheta += 180;
        
        if (this.myX < ((Bomb)p.Bombs.get(0)).myX) {
          moveTheta += 180;
        }
        
        if (moveTheta > 360) {
          moveTheta -= 360;
        }
        
        moveTheta += 180;
        
        this.myX += (int)(18.0D * Math.cos(Math.toRadians(moveTheta)));
        this.myY -= (int)(18.0D * Math.sin(Math.toRadians(moveTheta)));
      }
    }
  }
  
  public void paint(Graphics gg) {
    gg.setColor(Color.getHSBColor(0.2F, 0.2F, 0.2F));
    gg.drawOval(this.myX, this.myY, 3, 3); //size of the stars in the background
  }
}