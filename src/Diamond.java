import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

public class Diamond extends Enemy
{
  int theta = 0;
  int moveDIST = 4;
  
  float hue = 0.5F;
  
  Color xx;
  ArrayList<ExplosionShot> explosion = new ArrayList();
  
  int count = 0;
  
  public Diamond(int x, int y) {
    this.myX = x;
    this.myY = y;
  }
  
  public Diamond(Player p, int col) {
    if (col == 0) {
      this.xx = Color.MAGENTA;
    } else if (col == 1) {
      this.xx = Color.YELLOW;
    } else if (col == 2) {
      this.xx = Color.GREEN;
    } else if (col == 3 || col == 4) {
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
    this.myX = ((int)(Math.random() * 1000.0D));
    this.myY = ((int)(Math.random() * 800.0D));
    
    while ((int)Math.sqrt((this.myX - p.myX) * (this.myX - p.myX) + (this.myY - p.myY) * (this.myY - p.myY)) < 200) {
      this.myX = ((int)(Math.random() * 1000.0D));
      this.myY = ((int)(Math.random() * 800.0D));
    }
  }
  
  public void act(Player p)
  {
    this.numEX = this.explosion.size();
    
    if (this.isDEAD) {
      this.deadCOUNT += 1;
    }
    
    if (!this.isDEAD) {
      this.count += 2;
      
      double xdiff = this.myX - p.myX;
      int ydiff = this.myY - p.myY;
      
      this.theta = ((int)((Math.toDegrees(Math.atan(ydiff / xdiff)) + 0.5D) * -1.0D));
      
      this.theta += 180;
      
      if (this.myX < p.myX) {
        this.theta += 180;
      }
      
      if (this.theta > 360) {
        this.theta -= 360;
      }
      
      if (this.count > 14) {
        this.myX += (int)(this.moveDIST * Math.cos(Math.toRadians(this.theta)));
        this.myY -= (int)(this.moveDIST * Math.sin(Math.toRadians(this.theta)));
      }
      
      if (this.myX < 12) {
        this.myX = 12;
      } else if (this.myX > 981) {
        this.myX = 981;
      }
      if (this.myY < 12) {
        this.myY = 12;
      } else if (this.myY > 754) {
        this.myY = 754;
      }
      
    }
    else if (this.deadCOUNT == 1) {
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
    if (!this.isDEAD)
    {
      if (this.count < 5) {
        gg.setColor(Color.WHITE);
        
        int x1 = this.myX - (int)(this.count * Math.cos(Math.toRadians(this.theta)));
        int y1 = this.myY + (int)(this.count * Math.sin(Math.toRadians(this.theta)));
        int x2 = this.myX + (int)(this.count * Math.cos(Math.toRadians(this.theta)));
        int y2 = this.myY - (int)(this.count * Math.sin(Math.toRadians(this.theta)));
        int x3 = this.myX - (int)((this.count - 2) * Math.cos(Math.toRadians(this.theta + 90)));
        int y3 = this.myY + (int)((this.count - 2) * Math.sin(Math.toRadians(this.theta + 90)));
        int x4 = this.myX + (int)((this.count - 2) * Math.cos(Math.toRadians(this.theta + 90)));
        int y4 = this.myY - (int)((this.count - 2) * Math.sin(Math.toRadians(this.theta + 90)));
        
        gg.drawLine(x1, y1, x3, y3);
        gg.drawLine(x3, y3, x2, y2);
        gg.drawLine(x2, y2, x4, y4);
        gg.drawLine(x1, y1, x4, y4);
      }
      else
      {
        gg.setColor(this.xx);
        
        int x1 = this.myX - (int)(14.0D * Math.cos(Math.toRadians(this.theta)));
        int y1 = this.myY + (int)(14.0D * Math.sin(Math.toRadians(this.theta)));
        int x2 = this.myX + (int)(14.0D * Math.cos(Math.toRadians(this.theta)));
        int y2 = this.myY - (int)(14.0D * Math.sin(Math.toRadians(this.theta)));
        int x3 = this.myX - (int)(12.0D * Math.cos(Math.toRadians(this.theta + 90)));
        int y3 = this.myY + (int)(12.0D * Math.sin(Math.toRadians(this.theta + 90)));
        int x4 = this.myX + (int)(12.0D * Math.cos(Math.toRadians(this.theta + 90)));
        int y4 = this.myY - (int)(12.0D * Math.sin(Math.toRadians(this.theta + 90)));
        
        gg.drawLine(x1, y1, x3, y3);
        gg.drawLine(x3, y3, x2, y2);
        gg.drawLine(x2, y2, x4, y4);
        gg.drawLine(x1, y1, x4, y4);
        
        this.poly.reset();
        this.poly.addPoint(x1, y1);
        this.poly.addPoint(x3, y3);
        this.poly.addPoint(x2, y2);
        this.poly.addPoint(x4, y4);
      }
      
    }
    else if (this.deadCOUNT > 1)
    {
      for (ExplosionShot e : this.explosion) {
        e.paint(gg);
      }
      
      this.yellowCOUNT += 1;
      
      if (((ExplosionShot)this.explosion.get(0)).brightness <= 0.0F) {
        this.isDONE = true;
      }
    }
  }
}