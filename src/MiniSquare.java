import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

public class MiniSquare extends Enemy {
  int theta = 0;
  int moveDIST = 5;
  float hue = 0.82F;
  ArrayList<ExplosionShot> explosion = new ArrayList();
  boolean movingSIDE;
  int movingSIDEcount = 1;
  Color xx;
  
  public MiniSquare(int x, int y, boolean side, int col) {
    if (col == 0) {
      this.xx = Color.MAGENTA;
    } else if (col == 1) {
      this.xx = Color.YELLOW;
    } else if (col == 2) {
      this.xx = Color.GREEN;
    } else if (col == 3) {
      this.xx = Color.BLUE;
    } else if (col == 4) {
      this.xx = Color.RED;
    } else if (col == 5) {
      this.xx = Color.WHITE;
    } else if (col == 6) {
      this.xx = Color.CYAN;
    } else if (col == 7) {
      this.xx = Color.ORANGE;
    } else if (col == 8) {
      this.xx = Color.PINK;
    }
    this.myX = x;
    this.myY = y;
    
    if (side) {
      this.movingSIDE = true;
    } else {
      this.movingSIDE = false;
    }
  }
  
  public void act(Player p) {
    if (this.isDEAD) {
      this.deadCOUNT += 1;
    }
    
    if (!this.isDEAD) {
      double xdiff = this.myX - p.myX;
      int ydiff = this.myY - p.myY;
      
      this.theta = ((int)(Math.toDegrees(Math.atan(ydiff / xdiff)) + 0.5D) * -1);
      
      this.theta += 180;
      
      if (this.myX < p.myX) {
        this.theta += 180;
      }
      
      if (this.theta > 360) {
        this.theta -= 360;
      }
      
      this.myX += (int)(this.moveDIST * Math.cos(Math.toRadians(this.theta)));
      this.myY -= (int)(this.moveDIST * Math.sin(Math.toRadians(this.theta)));
      
      if (this.movingSIDE) {
        this.myX += (int)(5.0D * Math.cos(Math.toRadians(this.theta + 90)));
        this.myY -= (int)(5.0D * Math.sin(Math.toRadians(this.theta + 90)));
      } else {
        this.myX += (int)(-5.0D * Math.cos(Math.toRadians(this.theta + 90)));
        this.myY -= (int)(-5.0D * Math.sin(Math.toRadians(this.theta + 90)));
      }
      
      this.movingSIDEcount += 1;
      
      if (this.movingSIDEcount == 12) {
        this.movingSIDE = (!this.movingSIDE);
        this.movingSIDEcount = 0;
      }
      
      if (this.myX < 9) {
        this.myX = 9;
      } else if (this.myX > 984) {
        this.myX = 984;
      }
      if (this.myY < 9) {
        this.myY = 9;
      } else if (this.myY > 757) {
        this.myY = 757;
      }
    } else if (this.deadCOUNT == 1) {
      if (p.Bombs.size() < 1) {
        p.score += 50;
      }
      for (int i = 0; i < 17; i++) {
        this.explosion.add(new ExplosionShot(this.myX + (int)(7.0D * Math.random() - 3.0D), this.myY + (int)(7.0D * Math.random() - 3.0D), (int)(Math.random() * 360.0D), this.hue));
      }
    } else if (this.deadCOUNT > 1) {
      for (ExplosionShot e : this.explosion) {
        e.act();
      }
    }
  }
  
  public void paint(Graphics gg) {
    if (!this.isDEAD) {
      gg.setColor(this.xx);
      
      int x1 = this.myX - (int)(6.0D * Math.cos(Math.toRadians(this.theta)));
      int y1 = this.myY + (int)(6.0D * Math.sin(Math.toRadians(this.theta)));
      int x2 = this.myX + (int)(6.0D * Math.cos(Math.toRadians(this.theta)));
      int y2 = this.myY - (int)(6.0D * Math.sin(Math.toRadians(this.theta)));
      int x3 = this.myX - (int)(6.0D * Math.cos(Math.toRadians(this.theta + 90)));
      int y3 = this.myY + (int)(6.0D * Math.sin(Math.toRadians(this.theta + 90)));
      int x4 = this.myX + (int)(6.0D * Math.cos(Math.toRadians(this.theta + 90)));
      int y4 = this.myY - (int)(6.0D * Math.sin(Math.toRadians(this.theta + 90)));
      
      this.poly.reset();
      this.poly.addPoint(x1 + (int)(6.0D * Math.cos(Math.toRadians(this.theta + 90))), y1 - (int)(6.0D * Math.sin(Math.toRadians(this.theta + 90))));
      this.poly.addPoint(x1 - (int)(6.0D * Math.cos(Math.toRadians(this.theta + 90))), y1 + (int)(6.0D * Math.sin(Math.toRadians(this.theta + 90))));
      this.poly.addPoint(x3 + (int)(6.0D * Math.cos(Math.toRadians(this.theta))), y3 - (int)(6.0D * Math.sin(Math.toRadians(this.theta))));
      this.poly.addPoint(x4 + (int)(6.0D * Math.cos(Math.toRadians(this.theta))), y4 - (int)(6.0D * Math.sin(Math.toRadians(this.theta))));
      
      gg.drawPolygon(this.poly);
      
      gg.drawLine(x1 + (int)(6.0D * Math.cos(Math.toRadians(this.theta + 90))), y1 - (int)(6.0D * Math.sin(Math.toRadians(this.theta + 90))), x3 + (int)(6.0D * Math.cos(Math.toRadians(this.theta))), y3 - (int)(6.0D * Math.sin(Math.toRadians(this.theta))));
      gg.drawLine(x1 - (int)(6.0D * Math.cos(Math.toRadians(this.theta + 90))), y1 + (int)(6.0D * Math.sin(Math.toRadians(this.theta + 90))), x4 + (int)(6.0D * Math.cos(Math.toRadians(this.theta))), y4 - (int)(6.0D * Math.sin(Math.toRadians(this.theta))));
    } else if (this.deadCOUNT > 1) {
      for (ExplosionShot e : this.explosion) {
        e.paint(gg);
      }
      
      if (((ExplosionShot)this.explosion.get(0)).brightness <= 0.0F) {
        this.isDONE = true;
      }
    }
  }
}