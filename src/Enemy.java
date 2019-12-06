import java.awt.Graphics;
import java.awt.Polygon;

public abstract class Enemy {
  int myX;
  int myY;
  Intersect INTERSECTION = new Intersect();
  Polygon poly = new Polygon();
  boolean isDEAD = false;
  int deadCOUNT = 0;
  boolean isDONE = false;
  
  int minispinCOUNT = 0;
  int spinnerCOUNT = 0;
  int yellowCOUNT = 0;
  int whiteCOUNT = 0;
  int purpleCOUNT = 0;
  int numEX = 0;
  

  public void act(Player p) {}
  

  public boolean isShot(Shot s)
  {
    if ((!this.isDEAD) && (this.poly.intersects(s.myX - 3, s.myY - 3, 9.0D, 9.0D))) {
      this.isDEAD = true;
      return true;
    }
    return false;
  }
  
  public void intersects(Enemy e)
  {
    if (this.INTERSECTION.intersection(this.poly, e.poly)) {
      if (this.myX < e.myX) {
        this.myX -= 1;
        e.myX += 1;
      } else {
        this.myX += 1;
        e.myX -= 1;
      }
      
      if (this.myY < e.myY) {
        this.myY -= 1;
        e.myY += 1;
      } else {
        this.myY += 1;
        e.myY -= 1;
      }
    }
  }
  
  public void paint(Graphics gg) {}
}