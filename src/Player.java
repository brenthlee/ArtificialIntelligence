import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class Player
  implements KeyListener
{
  boolean added = true; boolean changed = true;
  int myScore = 0; int BAdded = 0;
  int myX = 495;
  int myY = 385;
  boolean movingUP = false;
  boolean movingDOWN = false;
  boolean movingLEFT = false;
  boolean movingRIGHT = false;
  int theta = 90;
  final int moveDIST = 9;
  int moveX = 0;
  int moveY = 8;
  int mouseX = 0;
  int mouseY = 0;
  Polygon poly = new Polygon();
  ArrayList<Shot> Shots = new ArrayList();
  int score = 0;
  boolean isDEAD = false;
  boolean bomb = false;
  ArrayList<Bomb> Bombs = new ArrayList();
  int deadCOUNT = 0;
  ArrayList<ExplosionShot> explosion = new ArrayList();
  boolean initializedEXPLOSION = false;
  boolean BAD = true;
  int LIVES = 3;
  int BOMBS = 2;
  
  public void act() {
    if (!this.isDEAD) {
      this.explosion.clear();
      this.initializedEXPLOSION = false;
    }
    
    if ((this.isDEAD) && (!this.initializedEXPLOSION)) {
      for (int i = 0; i < 17; i++) {
        this.explosion.add(new ExplosionShot(this.myX + (int)(7.0D * Math.random() - 3.0D), this.myY + (int)(7.0D * Math.random() - 3.0D), (int)(Math.random() * 360.0D), 1.0F, true));
      }
      this.initializedEXPLOSION = true;
    }
    
    if (this.isDEAD) {
      for (ExplosionShot e : this.explosion) {
        e.act();
      }
    }
    

    if (this.theta == 360) {
      this.theta = 0;
    }
    if (this.theta < 0) {
      this.theta += 360;
    } else if (this.theta > 360) {
      this.theta -= 360;
    }
    
    if (!this.isDEAD)
    {
      if ((this.added == true) && (this.changed == true) && (this.myScore != this.score)) {
        this.added = false;
        this.changed = false;
      }
      if ((this.score % 9000 == 0) && (this.score != 0) && (!this.added)) {
        this.myScore = this.score;
        this.changed = true;
        this.BOMBS += 1;
        this.BAdded += 1;
        this.added = true;
        if (this.BAD == true) {
          this.BAD = false;
        }
        if ((this.BAdded % 2 == 0) && (!this.BAD)) {
          this.LIVES += 1;
          this.BAD = true;
        }
      }
      
      if (((this.movingUP) && (!this.movingDOWN) && (!this.movingLEFT) && (!this.movingRIGHT)) || ((this.movingUP) && (this.movingLEFT) && (this.movingRIGHT) && (!this.movingDOWN))) {
        if ((this.theta < 90) || (this.theta > 270)) {
          this.theta += 15;
        } else if ((this.theta > 90) && (this.theta < 270)) {
          this.theta -= 15;
        } else if (this.theta == 270) {
          this.theta = 90;
        }
        
        this.moveX = ((int)(8.0D * Math.cos(Math.toRadians(this.theta)) + 0.5D));
        this.moveY = ((int)(8.0D * Math.sin(Math.toRadians(this.theta)) + 0.5D));
        this.myX += this.moveX;
        this.myY -= this.moveY;
      } else if (((this.movingDOWN) && (!this.movingUP) && (!this.movingLEFT) && (!this.movingRIGHT)) || ((this.movingDOWN) && (this.movingLEFT) && (this.movingRIGHT) && (!this.movingUP))) {
        if ((this.theta < 270) && (this.theta > 90)) {
          this.theta += 15;
        } else if ((this.theta > 270) || (this.theta < 90)) {
          this.theta -= 15;
        } else if (this.theta == 90) {
          this.theta = 270;
        }
        
        this.moveX = ((int)(8.0D * Math.cos(Math.toRadians(this.theta)) + 0.5D));
        this.moveY = ((int)(8.0D * Math.sin(Math.toRadians(this.theta)) + 0.5D));
        this.myX += this.moveX;
        this.myY -= this.moveY;
      } else if ((this.movingLEFT) && (!this.movingUP) && (!this.movingDOWN) && (!this.movingRIGHT)) {
        if ((this.theta < 180) && (this.theta > 0)) {
          this.theta += 15;
        } else if (this.theta > 180) {
          this.theta -= 15;
        } else if (this.theta == 0) {
          this.theta = 180;
        }
        
        this.moveX = ((int)(8.0D * Math.cos(Math.toRadians(this.theta)) + 0.5D));
        this.moveY = ((int)(8.0D * Math.sin(Math.toRadians(this.theta)) + 0.5D));
        this.myX += this.moveX;
        this.myY -= this.moveY;
      } else if ((this.movingRIGHT) && (!this.movingUP) && (!this.movingDOWN) && (!this.movingLEFT)) {
        if ((this.theta < 180) && (this.theta > 0)) {
          this.theta -= 15;
        } else if (this.theta > 180) {
          this.theta += 15;
        } else if (this.theta == 180) {
          this.theta = 0;
        }
        
        this.moveX = ((int)(8.0D * Math.cos(Math.toRadians(this.theta)) + 0.5D));
        this.moveY = ((int)(8.0D * Math.sin(Math.toRadians(this.theta)) + 0.5D));
        this.myX += this.moveX;
        this.myY -= this.moveY;
      } else if ((this.movingUP) && (this.movingRIGHT) && (!this.movingDOWN) && (!this.movingLEFT)) {
        if ((this.theta < 45) || (this.theta > 225)) {
          this.theta += 15;
        } else if ((this.theta < 225) && (this.theta > 45)) {
          this.theta -= 15;
        } else if (this.theta == 225) {
          this.theta = 45;
        }
        
        this.moveX = ((int)(8.0D * Math.cos(Math.toRadians(this.theta)) + 0.5D));
        this.moveY = ((int)(8.0D * Math.sin(Math.toRadians(this.theta)) + 0.5D));
        this.myX += this.moveX;
        this.myY -= this.moveY;
      } else if ((this.movingUP) && (this.movingLEFT) && (!this.movingDOWN) && (!this.movingRIGHT)) {
        if ((this.theta < 135) || (this.theta > 315)) {
          this.theta += 15;
        } else if ((this.theta > 135) && (this.theta < 315)) {
          this.theta -= 15;
        } else if (this.theta == 315) {
          this.theta = 135;
        }
        
        this.moveX = ((int)(8.0D * Math.cos(Math.toRadians(this.theta)) + 0.5D));
        this.moveY = ((int)(8.0D * Math.sin(Math.toRadians(this.theta)) + 0.5D));
        this.myX += this.moveX;
        this.myY -= this.moveY;
      } else if ((this.movingDOWN) && (this.movingRIGHT) && (!this.movingUP) && (!this.movingLEFT)) {
        if ((this.theta > 315) || (this.theta < 135)) {
          this.theta -= 15;
        } else if ((this.theta < 315) && (this.theta > 135)) {
          this.theta += 15;
        } else if (this.theta == 135) {
          this.theta = 315;
        }
        
        this.moveX = ((int)(8.0D * Math.cos(Math.toRadians(this.theta)) + 0.5D));
        this.moveY = ((int)(8.0D * Math.sin(Math.toRadians(this.theta)) + 0.5D));
        this.myX += this.moveX;
        this.myY -= this.moveY;
      } else if ((this.movingDOWN) && (this.movingLEFT) && (!this.movingUP) && (!this.movingRIGHT)) {
        if ((this.theta < 225) && (this.theta > 45)) {
          this.theta += 15;
        } else if ((this.theta > 225) || (this.theta < 45)) {
          this.theta -= 15;
        } else if (this.theta == 45) {
          this.theta = 225;
        }
        
        this.moveX = ((int)(8.0D * Math.cos(Math.toRadians(this.theta)) + 0.5D));
        this.moveY = ((int)(8.0D * Math.sin(Math.toRadians(this.theta)) + 0.5D));
        this.myX += this.moveX;
        this.myY -= this.moveY;
      }
    }
    

    if (this.myX <= 11) {
      this.myX = 11;
      if (this.theta < 180) {
        this.theta = 90;
      } else if (this.theta > 180) {
        this.theta = 270;
      }
      this.moveX = 0;
    } else if (this.myX >= 988) {
      this.myX = 988;
      if ((this.theta < 180) && (this.theta > 0)) {
        this.theta = 90;
      } else if (this.theta > 180) {
        this.theta = 270;
      }
      this.moveX = 0;
    }
    
    if (this.myY <= 11) {
      this.myY = 11;
      if (this.theta > 90) {
        this.theta = 180;
      } else if (this.theta < 90) {
        this.theta = 0;
      }
      this.moveY = 0;
    } else if (this.myY >= 747) {
      this.myY = 747;
      if ((this.theta < 270) && (this.theta > 180)) {
        this.theta = 180;
      } else if ((this.theta > 270) || (this.theta < 0)) {
        this.theta = 0;
      }
      this.moveY = 0;
    }
    
    if ((this.bomb) && (this.Bombs.size() < 1) && (this.BOMBS > 0) && (!this.isDEAD)) {
      //&&(this.LIVES > 1)) {
      this.Bombs.add(new Bomb(this.myX, this.myY));
      this.BOMBS -= 1;
      //this.LIVES -= 1;
    }
    

    for (int i = this.Bombs.size() - 1; i >= 0; i--) {
      ((Bomb)this.Bombs.get(i)).act();
      if (((Bomb)this.Bombs.get(i)).done) {
        this.Bombs.remove(i);
      }
    }
  }
  
  public void keyTyped(KeyEvent e) {}
  
  public void keyPressed(KeyEvent e)
  {
    if ((e.getKeyCode() == 38) || (e.getKeyCode() == 87)) {
      this.movingUP = true;
    }
    if ((e.getKeyCode() == 40) || (e.getKeyCode() == 83)) {
      this.movingDOWN = true;
    }
    if ((e.getKeyCode() == 37) || (e.getKeyCode() == 65)) {
      this.movingLEFT = true;
    }
    if ((e.getKeyCode() == 39) || (e.getKeyCode() == 68)) {
      this.movingRIGHT = true;
    }
    if (e.getKeyCode() == 32) {
      this.bomb = true;
    }
  }
  
  public void keyReleased(KeyEvent e) {
    if ((e.getKeyCode() == 38) || (e.getKeyCode() == 87)) {
      this.movingUP = false;
    }
    if ((e.getKeyCode() == 40) || (e.getKeyCode() == 83)) {
      this.movingDOWN = false;
    }
    if ((e.getKeyCode() == 37) || (e.getKeyCode() == 65)) {
      this.movingLEFT = false;
    }
    if ((e.getKeyCode() == 39) || (e.getKeyCode() == 68)) {
      this.movingRIGHT = false;
    }
    if (e.getKeyCode() == 32) {
      this.bomb = false;
    }
  }
  
  public void paint(Graphics gg) {
    for (Shot s : this.Shots) {
      s.paint(gg);
    }
    
    for (Bomb b : this.Bombs) {
      b.paint(gg);
    }
    
    for (ExplosionShot e : this.explosion) {
      e.paint(gg);
    }
    
    int x1 = this.myX - (int)(10.0D * Math.cos(Math.toRadians(this.theta)));
    int y1 = this.myY + (int)(10.0D * Math.sin(Math.toRadians(this.theta)));
    int x2 = this.myX + (int)(10.0D * Math.cos(Math.toRadians(this.theta)));
    int y2 = this.myY - (int)(10.0D * Math.sin(Math.toRadians(this.theta)));
    int x3 = this.myX - (int)(10.0D * Math.cos(Math.toRadians(this.theta + 90)));
    int y3 = this.myY + (int)(10.0D * Math.sin(Math.toRadians(this.theta + 90)));
    int x4 = this.myX + (int)(10.0D * Math.cos(Math.toRadians(this.theta + 90)));
    int y4 = this.myY - (int)(10.0D * Math.sin(Math.toRadians(this.theta + 90)));
    
    this.poly.reset();
    this.poly.addPoint(x1 + (int)(10.0D * Math.cos(Math.toRadians(this.theta + 90))), y1 - (int)(10.0D * Math.sin(Math.toRadians(this.theta + 90))));
    this.poly.addPoint(x1 - (int)(10.0D * Math.cos(Math.toRadians(this.theta + 90))), y1 + (int)(10.0D * Math.sin(Math.toRadians(this.theta + 90))));
    this.poly.addPoint(x2 - (int)(10.0D * Math.cos(Math.toRadians(this.theta + 90))), y2 + (int)(10.0D * Math.sin(Math.toRadians(this.theta + 90))));
    this.poly.addPoint(x2 + (int)(10.0D * Math.cos(Math.toRadians(this.theta + 90))), y2 - (int)(10.0D * Math.sin(Math.toRadians(this.theta + 90))));
    
    gg.setColor(Color.BLACK);
    gg.fillPolygon(this.poly);
    
    gg.setColor(Color.MAGENTA);
    gg.drawLine(x1 + (int)(10.0D * Math.cos(Math.toRadians(this.theta + 90))), y1 - (int)(10.0D * Math.sin(Math.toRadians(this.theta + 90))), x1 - (int)(10.0D * Math.cos(Math.toRadians(this.theta + 90))), y1 + (int)(10.0D * Math.sin(Math.toRadians(this.theta + 90))));
    
    gg.drawLine(x3 + (int)(10.0D * Math.cos(Math.toRadians(this.theta))), y3 - (int)(10.0D * Math.sin(Math.toRadians(this.theta))), x3 - (int)(10.0D * Math.cos(Math.toRadians(this.theta))), y3 + (int)(10.0D * Math.sin(Math.toRadians(this.theta))));
    gg.drawLine(x4 + (int)(10.0D * Math.cos(Math.toRadians(this.theta))), y4 - (int)(10.0D * Math.sin(Math.toRadians(this.theta))), x4 - (int)(10.0D * Math.cos(Math.toRadians(this.theta))), y4 + (int)(10.0D * Math.sin(Math.toRadians(this.theta))));
  }
}