import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

public class Spinner extends Enemy {
  int spinTheta = 0;
  int theta = 0;
  int moveDIR = 0;
  boolean turning = false;
  int moveDIST = 3;
  boolean colorUP = true;
  float hue = 0.74F;
  ArrayList<ExplosionShot> explosion = new ArrayList();
  int count = 0;
  int random;
  Color xx;
  
  public Spinner(int x, int y) {
    this.myX = x;
    this.myY = y;
  }
  
  public Spinner(Player p, int col) {
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
  
  public Spinner(Player p, boolean initial) {
    this.myX = ((int)(Math.random() * 500.0D) + 250);
    this.myY = ((int)(Math.random() * 400.0D) + 200);
    
    while ((int)Math.sqrt((this.myX - p.myX) * (this.myX - p.myX) + (this.myY - p.myY) * (this.myY - p.myY)) < 100) {
      this.myX = ((int)(Math.random() * 500.0D) + 250);
      this.myY = ((int)(Math.random() * 400.0D) + 200);
    }
  }
  
  public void act(Player p) {
    this.numEX = this.explosion.size();
    
    if (this.isDEAD) {
      this.deadCOUNT += 1;
    }
    
    if (!this.isDEAD) {
      this.count += 2;
      
      if (this.spinTheta == 360) {
        this.spinTheta = 0;
      }
      
      if ((this.theta % 90 == 0) || (this.theta == 0)) {
        this.turning = false;
      } else {
        this.turning = true;
      }
      
      if ((this.myX < 15) || (this.myX > 978) || (this.myY < 15) || (this.myY > 751)) {
        this.turning = false;
      }
      
      if (!this.turning) {
        this.moveDIR = ((int)(Math.random() * 2.0D));
        
        if (this.myX >= 981) {
          this.theta = 180;
        } else if (this.myX <= 12) {
          this.theta = 0;
        } else if (this.myY <= 12) {
          this.theta = 270;
        } else if (this.myY >= 752) {
          this.theta = 90;
        }
      }
      

      if (this.moveDIR == 0) {
        this.theta += 1;
        this.spinTheta += 8;
      } else {
        this.theta -= 1;
        this.spinTheta -= 8;
      }
      
      if (this.count > 14) {
        this.myX += (int)(this.moveDIST * Math.cos(Math.toRadians(this.theta)));
        this.myY -= (int)(this.moveDIST * Math.sin(Math.toRadians(this.theta)));
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
      if (this.count < 7) {
        gg.setColor(Color.WHITE);
        
        int x1 = this.myX - (int)(this.count * Math.cos(Math.toRadians(this.spinTheta)));
        int y1 = this.myY + (int)(this.count * Math.sin(Math.toRadians(this.spinTheta)));
        int x2 = this.myX + (int)(this.count * Math.cos(Math.toRadians(this.spinTheta)));
        int y2 = this.myY - (int)(this.count * Math.sin(Math.toRadians(this.spinTheta)));
        int x3 = this.myX - (int)(this.count * Math.cos(Math.toRadians(this.spinTheta + 90)));
        int y3 = this.myY + (int)(this.count * Math.sin(Math.toRadians(this.spinTheta + 90)));
        int x4 = this.myX + (int)(this.count * Math.cos(Math.toRadians(this.spinTheta + 90)));
        int y4 = this.myY - (int)(this.count * Math.sin(Math.toRadians(this.spinTheta + 90)));
        
        gg.drawLine(x1 + (int)((this.count / 2 - 1) * Math.cos(Math.toRadians(this.spinTheta + 90))), y1 - (int)((this.count / 2 - 1) * Math.sin(Math.toRadians(this.spinTheta + 90))), x1 - (int)((this.count / 2 - 1) * Math.cos(Math.toRadians(this.spinTheta + 90))), y1 + (int)((this.count / 2 - 1) * Math.sin(Math.toRadians(this.spinTheta + 90))));
        gg.drawLine(x2 + (int)((this.count / 2 - 1) * Math.cos(Math.toRadians(this.spinTheta + 90))), y2 - (int)((this.count / 2 - 1) * Math.sin(Math.toRadians(this.spinTheta + 90))), x2 - (int)((this.count / 2 - 1) * Math.cos(Math.toRadians(this.spinTheta + 90))), y2 + (int)((this.count / 2 - 1) * Math.sin(Math.toRadians(this.spinTheta + 90))));
        gg.drawLine(x3 + (int)((this.count / 2 - 1) * Math.cos(Math.toRadians(this.spinTheta))), y3 - (int)((this.count / 2 - 1) * Math.sin(Math.toRadians(this.spinTheta))), x3 - (int)((this.count / 2 - 1) * Math.cos(Math.toRadians(this.spinTheta))), y3 + (int)((this.count / 2 - 1) * Math.sin(Math.toRadians(this.spinTheta))));
        gg.drawLine(x4 + (int)((this.count / 2 - 1) * Math.cos(Math.toRadians(this.spinTheta))), y4 - (int)((this.count / 2 - 1) * Math.sin(Math.toRadians(this.spinTheta))), x4 - (int)((this.count / 2 - 1) * Math.cos(Math.toRadians(this.spinTheta))), y4 + (int)((this.count / 2 - 1) * Math.sin(Math.toRadians(this.spinTheta))));
        gg.drawLine(this.myX, this.myY, x1 + (int)((this.count / 2 - 1) * Math.cos(Math.toRadians(this.spinTheta + 90))), y1 - (int)((this.count / 2 - 1) * Math.sin(Math.toRadians(this.spinTheta + 90))));
        gg.drawLine(this.myX, this.myY, x1 - (int)((this.count / 2 - 1) * Math.cos(Math.toRadians(this.spinTheta + 90))), y1 + (int)((this.count / 2 - 1) * Math.sin(Math.toRadians(this.spinTheta + 90))));
        gg.drawLine(this.myX, this.myY, x2 + (int)((this.count / 2 - 1) * Math.cos(Math.toRadians(this.spinTheta + 90))), y2 - (int)((this.count / 2 - 1) * Math.sin(Math.toRadians(this.spinTheta + 90))));
        gg.drawLine(this.myX, this.myY, x2 - (int)((this.count / 2 - 1) * Math.cos(Math.toRadians(this.spinTheta + 90))), y2 + (int)((this.count / 2 - 1) * Math.sin(Math.toRadians(this.spinTheta + 90))));
        gg.drawLine(this.myX, this.myY, x3 + (int)((this.count / 2 - 1) * Math.cos(Math.toRadians(this.spinTheta))), y3 - (int)((this.count / 2 - 1) * Math.sin(Math.toRadians(this.spinTheta))));
        gg.drawLine(this.myX, this.myY, x3 - (int)((this.count / 2 - 1) * Math.cos(Math.toRadians(this.spinTheta))), y3 + (int)((this.count / 2 - 1) * Math.sin(Math.toRadians(this.spinTheta))));
        gg.drawLine(this.myX, this.myY, x4 + (int)((this.count / 2 - 1) * Math.cos(Math.toRadians(this.spinTheta))), y4 - (int)((this.count / 2 - 1) * Math.sin(Math.toRadians(this.spinTheta))));
        gg.drawLine(this.myX, this.myY, x4 - (int)((this.count / 2 - 1) * Math.cos(Math.toRadians(this.spinTheta))), y4 + (int)((this.count / 2 - 1) * Math.sin(Math.toRadians(this.spinTheta))));
        gg.drawOval(this.myX - 1, this.myY - 1, 3, 3);
      } else {
        gg.setColor(this.xx);
        int x1 = this.myX - (int)(12.0D * Math.cos(Math.toRadians(this.spinTheta)));
        int y1 = this.myY + (int)(12.0D * Math.sin(Math.toRadians(this.spinTheta)));
        int x2 = this.myX + (int)(12.0D * Math.cos(Math.toRadians(this.spinTheta)));
        int y2 = this.myY - (int)(12.0D * Math.sin(Math.toRadians(this.spinTheta)));
        int x3 = this.myX - (int)(12.0D * Math.cos(Math.toRadians(this.spinTheta + 90)));
        int y3 = this.myY + (int)(12.0D * Math.sin(Math.toRadians(this.spinTheta + 90)));
        int x4 = this.myX + (int)(12.0D * Math.cos(Math.toRadians(this.spinTheta + 90)));
        int y4 = this.myY - (int)(12.0D * Math.sin(Math.toRadians(this.spinTheta + 90)));
        
        gg.drawLine(x1 + (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta + 90))), y1 - (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta + 90))), x1 - (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta + 90))), y1 + (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta + 90))));
        gg.drawLine(x2 + (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta + 90))), y2 - (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta + 90))), x2 - (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta + 90))), y2 + (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta + 90))));
        gg.drawLine(x3 + (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta))), y3 - (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta))), x3 - (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta))), y3 + (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta))));
        gg.drawLine(x4 + (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta))), y4 - (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta))), x4 - (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta))), y4 + (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta))));
        gg.drawLine(this.myX, this.myY, x1 + (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta + 90))), y1 - (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta + 90))));
        gg.drawLine(this.myX, this.myY, x1 - (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta + 90))), y1 + (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta + 90))));
        gg.drawLine(this.myX, this.myY, x2 + (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta + 90))), y2 - (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta + 90))));
        gg.drawLine(this.myX, this.myY, x2 - (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta + 90))), y2 + (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta + 90))));
        gg.drawLine(this.myX, this.myY, x3 + (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta))), y3 - (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta))));
        gg.drawLine(this.myX, this.myY, x3 - (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta))), y3 + (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta))));
        gg.drawLine(this.myX, this.myY, x4 + (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta))), y4 - (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta))));
        gg.drawLine(this.myX, this.myY, x4 - (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta))), y4 + (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta))));
        gg.drawOval(this.myX - 1, this.myY - 1, 3, 3);
        
        this.poly.reset();
        this.poly.addPoint(x1 + (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta + 90))), y1 - (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta + 90))));
        this.poly.addPoint(x1 - (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta + 90))), y1 + (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta + 90))));
        this.poly.addPoint(x3 - (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta))), y3 + (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta))));
        this.poly.addPoint(x3 + (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta))), y3 - (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta))));
        this.poly.addPoint(x2 - (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta + 90))), y2 + (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta + 90))));
        this.poly.addPoint(x2 + (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta + 90))), y2 - (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta + 90))));
        this.poly.addPoint(x4 + (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta))), y4 - (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta))));
        this.poly.addPoint(x4 - (int)(5.0D * Math.cos(Math.toRadians(this.spinTheta))), y4 + (int)(5.0D * Math.sin(Math.toRadians(this.spinTheta))));
      }
    } else if (this.deadCOUNT > 1) {
      for (ExplosionShot e : this.explosion) {
        e.paint(gg);
      }
      
      this.spinnerCOUNT += 1;
      
      if (((ExplosionShot)this.explosion.get(0)).brightness <= 0.0F) {
        this.isDONE = true;
      }
    }
  }
}