import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class Shot
{
  int myX;
  int myY;
  int theta;
  int moveDIST = 17;
  boolean active = true;
  Polygon screen = new Polygon();
  int count = 0;
  private int random;
  
  public Shot(Player p, int angle) {
    this.myX = (p.myX - 1);
    this.myY = (p.myY - 1);
    this.theta = angle;
    
    this.screen.addPoint(0, 0);
    this.screen.addPoint(0, 766);
    this.screen.addPoint(993, 766);
    this.screen.addPoint(993, 0);
  }
  
  public void act() {
    this.count += 1;
    this.random = ((int)(Math.random() * 9.0D));
    if (!this.screen.contains(this.myX + 1, this.myY + 1)) {
      this.active = false;
      
      if (this.myX < 0) {
        this.myX = 0;
      } else if (this.myX > 993) {
        this.myX = 993;
      }
      if (this.myY < 0) {
        this.myY = 0;
      } else if (this.myY > 766) {
        this.myY = 766;
      }
    }
    

    if (this.active) {
      this.myX += (int)(this.moveDIST * Math.cos(Math.toRadians(this.theta)) + 0.5D);
      this.myY -= (int)(this.moveDIST * Math.sin(Math.toRadians(this.theta)) + 0.5D);
    }
  }
  
  public void paint(Graphics gg) {
    if (this.active) {
      if (this.random == 0) {
        gg.setColor(Color.MAGENTA);
      } else if (this.random == 1) {
        gg.setColor(Color.YELLOW);
      } else if (this.random == 2) {
        gg.setColor(Color.GREEN);
      } else if (this.random == 3) {
        gg.setColor(Color.BLUE);
      } else if (this.random == 4) {
        gg.setColor(Color.RED);
      } else if (this.random == 5) {
        gg.setColor(Color.WHITE);
      } else if (this.random == 6) {
        gg.setColor(Color.CYAN);
      } else if (this.random == 7) {
        gg.setColor(Color.ORANGE);
      } else if (this.random == 8) {
        gg.setColor(Color.PINK);
      }
      gg.drawOval(this.myX, this.myY, 5, 5);
    }
  }
}