import java.awt.Graphics;
import java.util.ArrayList;

public class Explosion {
  int myX;
  int myY;
  boolean initialized = false;
  boolean done = false;
  ArrayList<ExplosionShot> explosion = new ArrayList();
  
  public Explosion(int x, int y) {
    this.myX = x;
    this.myY = y;
  }
  
  public void act() {
    if (!this.initialized) {
      for (int i = 0; i < 17; i++) {
        this.explosion.add(new ExplosionShot(this.myX + (int)(7.0D * Math.random() - 3.0D), this.myY + (int)(7.0D * Math.random() - 3.0D), (int)(Math.random() * 360.0D), 0.17F));
      }
      this.initialized = true;
    } else {
      for (ExplosionShot e : this.explosion) {
        e.act();
      }
    }
  }
  
  public void paint(Graphics gg) {
    for (ExplosionShot e : this.explosion) {
      e.paint(gg);
    }
    
    if (((ExplosionShot)this.explosion.get(0)).brightness <= 0.0F) {
      this.done = true;
    }
  }
}