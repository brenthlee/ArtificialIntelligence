import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Bomb {
  int myX;
  int myY;
  int radius = 0;
  boolean done = false;
  int col;
  int count = 0;
  int lastcount = -5;
  ArrayList<ExplosionShot> explosion = new ArrayList();
  
  public Bomb(int x, int y) {
    this.myX = x;
    this.myY = y;
  }
  
  public void act() {
    this.col = ((int)(Math.random() * 9.0D));
    this.radius += 20;
    if (this.radius >= 1280) {
      this.done = true;
    }
    
    if (this.radius >= 40) {
      for (int i = 0; i < 360; i += 5) {
        this.explosion.add(new ExplosionShot(this.myX + (int)((this.radius - 40) * Math.cos(i)), this.myY + (int)((this.radius - 40) * Math.sin(i)), i, (float)Math.random(), true));
      }
    }
    

    for (int i = this.explosion.size() - 1; i >= 0; i--) {
      if (((ExplosionShot)this.explosion.get(i)).brightness <= 0.0F) {
        this.explosion.remove(i);
      }
    }
    
    for (ExplosionShot e : this.explosion) {
      e.act();
    }
  }
  
  public void paint(Graphics gg) {
    if (this.col == 0) {
      gg.setColor(Color.MAGENTA);
    } else if (this.col == 1) {
      gg.setColor(Color.YELLOW);
    } else if (this.col == 2) {
      gg.setColor(Color.GREEN);
    } else if (this.col == 3) {
      gg.setColor(Color.BLUE);
    } else if (this.col == 4) {
      gg.setColor(Color.RED);
    } else if (this.col == 5) {
      gg.setColor(Color.WHITE);
    } else if (this.col == 6) {
      gg.setColor(Color.CYAN);
    } else if (this.col == 7) {
      gg.setColor(Color.ORANGE);
    } else if (this.col == 8) {
      gg.setColor(Color.PINK);
    }
    gg.drawOval(this.myX - this.radius, this.myY - this.radius, this.radius * 2, this.radius * 2);
    int radius1 = this.radius - 10;
    gg.drawOval(this.myX - radius1, this.myY - radius1, radius1 * 2, radius1 * 2);
    
    for (ExplosionShot e : this.explosion) {
      e.paint(gg);
    }
  }
}