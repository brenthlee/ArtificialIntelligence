import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class ArtificialIntelligence extends JFrame implements KeyListener, java.awt.event.MouseListener
{
  private static ArtificialIntelligence.BGPanel bgpanel;
  static Graphics gg;
  static Image offscreen;
  static Dimension dim;
  static int framecount = 0;
  static Stars stars = new Stars();
  static Player player = new Player();
  static Tail playertail = new Tail();
  static Mouse playermouse = new Mouse();
  static float saturation = 1.0F;
  static boolean up = true;
  static int shotTheta = 270;
  static boolean clicked = false;
  static int lastclick = 0;
  static ArrayList<Enemy> Enemies = new ArrayList();
  static ArrayList<Explosion> WallExplosions = new ArrayList();
  static int count = 0;
  boolean initialized = false;
  static Intersect INTERSECTION = new Intersect();
  static int lowcount = 0;
  int last = 0;
  static int highest = 0;
  static int killcount = 0;
  static ArrayList<Enemy> Killer = new ArrayList();
  static Font Font1 = new Font("DialogInput", 0, 12);
  static Font Font2 = new Font("Monospaced", 1, 22);
  static Font Font3 = new Font("Monospaced", 1, 42);
  static Font Font4 = new Font("Monospaced", 1, 28);
  static Font Font5 = new Font("Monospaced", 1, 26);
  static ArrayList<Random> Warps = new ArrayList();
  static boolean highQUALITY = true;
  int enemyCOUNT = 20;
  boolean Spin1 = false;
  boolean Spin2 = false;
  boolean Blue1 = false;
  int Wave1 = 0;
  int waveCOUNT = 0;
  boolean wave1DONE = false;
  boolean waveSTOP = false;
  static int phaseCOUNT = 0;
  int random = 0;
  boolean WaveINFINITE = false;
  boolean WaveINFINITEDONE = false;
  int WaveINFINITEPICKER = 0;
  int WaveINFINITECOUNT = 0;
  int infinitephaseCOUNT = 0;
  boolean infinitePHASE = false;
  static int creatorCOUNT = 3;
  static int lastOFFSET = 5;
  static boolean GAMEOVER = false;
  int lastbombPLUS = 0;
  int lastlifePLUS = 0;
  static boolean PAUSED = false;
  static boolean Play = true;
  static int col;
  static int coltemp;
  public static ArtificialIntelligence star;
                
  public ArtificialIntelligence() { super("SpaceWars");
    initComponents();
    setBounds(150, 150, 1000, 835);
    setDefaultCloseOperation(3);
    setResizable(false);
    
    Container c = getContentPane();
    c.removeAll();
                                
    bgpanel = new ArtificialIntelligence.BGPanel();
    bgpanel.setLayout(null);
    setBackground(Color.BLACK);
    
    c.add(bgpanel, "Center");
    
    ImageIcon emptyIcon = new ImageIcon(new byte[0]);
    Cursor invisibleCursor = getToolkit().createCustomCursor(emptyIcon.getImage(), new Point(0, 0), "Invisible");
    setCursor(invisibleCursor);
    
    dim = getSize();
    offscreen = createImage(dim.width, dim.height);
    gg = offscreen.getGraphics();
    
    for (int xx = 0; xx < 1000; xx += 10) {
      for (int yy = 0; yy < 800; yy += 10) {
        Warps.add(new Random(xx, yy));
      }
    }
    

    addKeyListener(this);
    addMouseListener(this);
                                
    ActionListener task = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ArtificialIntelligence.this.startimer();
      }
    };
    new Timer(45, task).start();
                                
    ActionListener task1 = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ArtificialIntelligence.this.doaction();
        ArtificialIntelligence.framecount += 1;
        if (ArtificialIntelligence.framecount % 5 == 0) { //HOW FAST ENEMIES APPEAR
          ArtificialIntelligence.lowcount += 1;
        }
      }
    };
    new Timer(25, task1).start();
  }
  
  private void initComponents() {
    setDefaultCloseOperation(3);
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));
    
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));
    
    pack();
  }
                
  public static void main(String[] args)
  {
    //JOptionPane.showMessageDialog(null, "To move, use the arrow keys. To aim and shoot, use your mouse\nIf you are trapped and about to lose, press spacebar for a bomb\nBombs are limited;\nHowever many bombs you have left are on the top of the screen\nYou get more bombs every time you reach a score divisble by 9000\nYou get a life every time you get 2 bombs (score divisble by 18000)\nYou also lose a life every time you use a bomb ;)");
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        ArtificialIntelligence.star = new ArtificialIntelligence();
        ArtificialIntelligence.star.setVisible(true);
      }
    });
  }
  
  public void startimer() {
    if (!this.initialized) {
      stars.initialize();
      this.initialized = true;
    }
    if ((!GAMEOVER) && (!PAUSED)) {
      stars.act(player);
    }
  }
  
  public void doaction() {
    do {
      col = (int)(Math.random() * 9.0D);
      coltemp = col;
    } while (col != coltemp);
    
    if (player.LIVES < 1) {
      GAMEOVER = true;
    }
    
    if ((!GAMEOVER) && (!PAUSED)) {
      if (player.score >= this.lastbombPLUS + 20000) {
        if (player.BOMBS < 4) {
          player.BOMBS += 1;
        }
        this.lastbombPLUS = player.score;
      }
      
      if (player.score >= this.lastlifePLUS + 40000) {
        if (player.LIVES < 9) {
          player.LIVES += 1;
        }
        this.lastlifePLUS = player.score;
      }

      /** ELEMENTS OF AI START HERE (SPAWNING RATE) **/

      if ((lowcount > 12) && (!this.Spin1)) {
        Enemies.add(new Spinner(player, true));
        this.Spin1 = true;
      } else if ((lowcount > 22) && (!this.Spin2)) {
        Enemies.add(new Spinner(player, col));
        this.Spin2 = true;
      } else if ((lowcount > 34) && (!this.Blue1)) {
        Enemies.add(new Diamond(player, col));
        this.Blue1 = true;
      }
      
      if (player.Bombs.size() < 1) {
        if ((lowcount > 40) && (lowcount < 180) && (lowcount > this.last + 10)) {
          int r = (int)(Math.random() * 8.0D);
          
          if (r <= 2) {
            Enemies.add(new Spinner(player, col));
          } else if (r >= 3) {
            Enemies.add(new Diamond(player, col));
          }
          
          r = (int)(Math.random() * 8.0D);
          
          if (r <= 2) {
            Enemies.add(new Spinner(player, col));
          } else {
            Enemies.add(new Diamond(player, col));
          }
          
          this.last = lowcount;
        } else if ((lowcount > 180) && (lowcount < 300) && (lowcount > this.last + 10)) {
          int r = (int)(Math.random() * 10.0D);
          
          if (r <= 2) {
            Enemies.add(new Spinner(player, col));
          } else if (r >= 7) {
            Enemies.add(new Square(player, col));
          } else {
            Enemies.add(new Diamond(player, col));
          }
          
          r = (int)(Math.random() * 10.0D);
          
          if (r <= 2) {
            Enemies.add(new Spinner(player, col));
          } else if (r >= 7) {
            Enemies.add(new Square(player, col));
          } else {
            Enemies.add(new Diamond(player, col));
          }
          
          this.last = lowcount;
        } else if ((lowcount > 300) && (lowcount < 400) && (lowcount > this.last + 10)) {
          int r = (int)(Math.random() * 11.0D);
          
          if (r <= 1) {
            Enemies.add(new Spinner(player, col));
          } else if (r >= 8) {
            Enemies.add(new Square(player, col));
          } else if (r >= 5) {
            Enemies.add(new Diamond(player, col));
          } else {
            Enemies.add(new Avoid(player, col));
          }
          
          r = (int)(Math.random() * 11.0D);
          
          if (r <= 1) {
            Enemies.add(new Spinner(player, col));
          } else if (r >= 8) {
            Enemies.add(new Square(player, col));
          } else if (r >= 5) {
            Enemies.add(new Diamond(player, col));
          } else {
            Enemies.add(new Avoid(player, col));
          }
          
          this.last = lowcount;
        } else if ((lowcount > 400) && (lowcount > this.last) && (!this.wave1DONE)) {
          this.enemyCOUNT = 1000;
          
          if ((this.Wave1 == 0) && (Enemies.isEmpty())) {
            this.Wave1 = ((int)(Math.random() * 10.0D) + 1);
          }
          
          if (!this.waveSTOP) {
            if ((this.Wave1 > 0) && (this.Wave1 < 9)) {
              Enemies.add(new Avoid((int)(Math.random() * 50.0D), (int)(Math.random() * 50.0D)));
              Enemies.add(new Avoid(1000 - (int)(Math.random() * 50.0D), (int)(Math.random() * 50.0D)));
              Enemies.add(new Avoid((int)(Math.random() * 50.0D), 1000 - (int)(Math.random() * 50.0D)));
              Enemies.add(new Avoid(1000 - (int)(Math.random() * 50.0D), 1000 - (int)(Math.random() * 50.0D)));
            }
            else if (this.Wave1 == 9) {
              Enemies.add(new Square((int)(Math.random() * 50.0D), (int)(Math.random() * 50.0D)));
              Enemies.add(new Square(1000 - (int)(Math.random() * 50.0D), (int)(Math.random() * 50.0D)));
              Enemies.add(new Square((int)(Math.random() * 50.0D), 1000 - (int)(Math.random() * 50.0D)));
              Enemies.add(new Square(1000 - (int)(Math.random() * 50.0D), 1000 - (int)(Math.random() * 50.0D)));
            } else if (this.Wave1 == 10) {
              Enemies.add(new Diamond((int)(Math.random() * 50.0D), (int)(Math.random() * 50.0D)));
              Enemies.add(new Diamond(1000 - (int)(Math.random() * 50.0D), (int)(Math.random() * 50.0D)));
              Enemies.add(new Diamond((int)(Math.random() * 50.0D), 1000 - (int)(Math.random() * 50.0D)));
              Enemies.add(new Diamond(1000 - (int)(Math.random() * 50.0D), 1000 - (int)(Math.random() * 50.0D)));
            }
          }
          

          if (this.Wave1 != 0) {
            this.waveCOUNT += 1;
          }
          
          if (this.waveCOUNT > 9) {
            this.waveSTOP = true;
            this.waveCOUNT = 0;
          }
          
          if ((Enemies.size() < 4) && (this.waveSTOP)) {
            this.wave1DONE = true;
            this.enemyCOUNT = 25;
          }
          
          this.last = lowcount;
        } else if ((this.wave1DONE) && (lowcount > this.last + 5) && (Enemies.size() < this.enemyCOUNT) && (phaseCOUNT < 15)) {
          int r = (int)(Math.random() * 11.0D);
          
          if (r <= 1) {
            Enemies.add(new Spinner(player, col));
          } else if (r >= 8) {
            Enemies.add(new Square(player, col));
          } else if (r >= 5) {
            Enemies.add(new Diamond(player, col));
          } else {
            Enemies.add(new Avoid(player, col));
          }
          
          r = (int)(Math.random() * 11.0D);
          
          if (r <= 1) {
            Enemies.add(new Spinner(player, col));
          } else if (r >= 8) {
            Enemies.add(new Square(player, col));
          } else if (r >= 5) {
            Enemies.add(new Diamond(player, col));
          } else {
            Enemies.add(new Avoid(player, col));
          }
          
          phaseCOUNT += 1;
          
          this.last = lowcount;
        } else if (phaseCOUNT >= 15) {
          if ((this.random == 0) && (Enemies.size() < 15)) {
            this.random = ((int)(Math.random() * 5.0D) + 1);
          }
          
          if ((this.random == 1) && (!this.WaveINFINITE) && (Enemies.size() < 3)) {
            this.WaveINFINITE = true;
            this.enemyCOUNT = 1000;
            this.WaveINFINITEPICKER = ((int)(Math.random() * 10.0D) + 1);
          }
          
          if ((this.WaveINFINITE) && (lowcount > this.last) && (!this.WaveINFINITEDONE) && (this.WaveINFINITECOUNT < 10)) {
            if ((this.WaveINFINITEPICKER > 0) && (this.WaveINFINITEPICKER < 9)) {
              Enemies.add(new Avoid((int)(Math.random() * 50.0D), (int)(Math.random() * 50.0D)));
              Enemies.add(new Avoid(1000 - (int)(Math.random() * 50.0D), (int)(Math.random() * 50.0D)));
              Enemies.add(new Avoid((int)(Math.random() * 50.0D), 1000 - (int)(Math.random() * 50.0D)));
              Enemies.add(new Avoid(1000 - (int)(Math.random() * 50.0D), 1000 - (int)(Math.random() * 50.0D)));
            }
            else if (this.WaveINFINITEPICKER == 9) {
              Enemies.add(new Square((int)(Math.random() * 50.0D), (int)(Math.random() * 50.0D)));
              Enemies.add(new Square(1000 - (int)(Math.random() * 50.0D), (int)(Math.random() * 50.0D)));
              Enemies.add(new Square((int)(Math.random() * 50.0D), 1000 - (int)(Math.random() * 50.0D)));
              Enemies.add(new Square(1000 - (int)(Math.random() * 50.0D), 1000 - (int)(Math.random() * 50.0D)));
            } else if (this.WaveINFINITEPICKER == 10) {
              Enemies.add(new Diamond((int)(Math.random() * 50.0D), (int)(Math.random() * 50.0D)));
              Enemies.add(new Diamond(1000 - (int)(Math.random() * 50.0D), (int)(Math.random() * 50.0D)));
              Enemies.add(new Diamond((int)(Math.random() * 50.0D), 1000 - (int)(Math.random() * 50.0D)));
              Enemies.add(new Diamond(1000 - (int)(Math.random() * 50.0D), 1000 - (int)(Math.random() * 50.0D)));
            }
            
            if (this.WaveINFINITEPICKER != 0) {
              this.waveCOUNT += 1;
            }
            if (this.waveCOUNT > 9) {
              this.waveSTOP = true;
              this.waveCOUNT = 0;
            }
            if ((Enemies.size() < 4) && (this.waveSTOP)) {
              this.WaveINFINITEDONE = true;
              this.enemyCOUNT = 25;
            }
            
            this.last = lowcount;
          } else if ((this.WaveINFINITE) && (this.WaveINFINITECOUNT >= 10) && (Enemies.size() < 4))
          {
            this.WaveINFINITE = false;
            this.enemyCOUNT = 25;
            this.WaveINFINITEPICKER = 0;
            this.WaveINFINITECOUNT = 0;
            this.random = 0;
          }
          
          if ((this.random > 1) && (lowcount > this.last + lastOFFSET) && (Enemies.size() < this.enemyCOUNT) && (this.infinitephaseCOUNT < 30)) {
            for (int i = 0; i < creatorCOUNT; i++) {
              int r = (int)(Math.random() * 11.0D);
              
              if (r <= 1) {
                Enemies.add(new Spinner(player, col));
              } else if (r >= 8) {
                Enemies.add(new Square(player, col));
              } else if (r >= 5) {
                Enemies.add(new Diamond(player, col));
              } else {
                Enemies.add(new Avoid(player, col));
              }
            }
            

            this.last = lowcount;
            this.infinitephaseCOUNT += 1;
          } else if (this.infinitephaseCOUNT >= 30) {
            this.infinitephaseCOUNT = 0;
            creatorCOUNT += 1;
            
            if ((lastOFFSET > 1) && (creatorCOUNT % 2 == 0)) {
              lastOFFSET -= 1;
            }
            
            this.random = 0;
          }
        }
      }
    }

    int panelx = bgpanel.getLocationOnScreen().x;
    int panely = bgpanel.getLocationOnScreen().y;
    
    playermouse.mouseX = (MouseInfo.getPointerInfo().getLocation().x - panelx);
    playermouse.mouseY = (MouseInfo.getPointerInfo().getLocation().y - panely);
    
    if ((!GAMEOVER) && (!PAUSED)) {
      double xdiff = playermouse.mouseX - (player.myX + 5);
      int ydiff = playermouse.mouseY - (player.myY + 5);
      
      shotTheta = (int)(Math.toDegrees(Math.atan(ydiff / xdiff)) + 0.5D) * -1;
      
      if (playermouse.mouseX < player.myX + 5) {
        shotTheta += 180;
      }
      
      if ((!player.isDEAD) && (clicked) && (framecount > lastclick + 3)) {
                   //HOW MANY BULLETS TO SHOOT AT A TIME
//        if (((this.Wave1 > 0) && (this.Wave1 < 9) && (!this.wave1DONE)) || ((this.WaveINFINITEPICKER > 0) && (this.WaveINFINITEPICKER < 9) && (!this.WaveINFINITEDONE)))
//        {
//          player.Shots.add(new Shot(player, shotTheta + 10));
//          player.Shots.add(new Shot(player, shotTheta - 10));
//          player.Shots.add(new Shot(player, shotTheta + 15));
//          player.Shots.add(new Shot(player, shotTheta - 15));
//        }
//        if (lowcount > 300) {
//          player.Shots.add(new Shot(player, shotTheta));
//          player.Shots.add(new Shot(player, shotTheta + 5));
//          player.Shots.add(new Shot(player, shotTheta - 5));
//        } else if (lowcount > 150) {
//          player.Shots.add(new Shot(player, shotTheta + 3));
//          player.Shots.add(new Shot(player, shotTheta - 3));
//        } else {
//          player.Shots.add(new Shot(player, shotTheta));
//        }
        player.Shots.add(new Shot(player, shotTheta-2));
        player.Shots.add(new Shot(player, shotTheta+2));
//        player.Shots.add(new Shot(player, shotTheta + 5));
//        player.Shots.add(new Shot(player, shotTheta - 5));
//        player.Shots.add(new Shot(player, shotTheta + 10));
//        player.Shots.add(new Shot(player, shotTheta - 10));
//        for (int ij = 5; ij <= 180; ij += 5) {
//            player.Shots.add(new Shot(player, shotTheta + ij));
//            player.Shots.add(new Shot(player, shotTheta - ij));
//        }
        lastclick = framecount;
      }
      
      for (int i = player.Shots.size() - 1; (i >= 0) && (player.Shots.size() > 0); i--) {
        ((Shot)player.Shots.get(i)).act();
        
        if (!((Shot)player.Shots.get(i)).active) {
          WallExplosions.add(new Explosion(((Shot)player.Shots.get(i)).myX, ((Shot)player.Shots.get(i)).myY));
          player.Shots.remove(i);
        }
      }
      
      if (!player.isDEAD) {
        for (int i = Enemies.size() - 1; i >= 0; i--) {
          ((Enemy)Enemies.get(i)).act(player);
          
          for (Enemy e : Enemies) {
            ((Enemy)Enemies.get(i)).intersects(e);
          }
          
          if (((Enemy)Enemies.get(i)).purpleCOUNT == 1) {
            Enemies.add(new MiniSquare(((Enemy)Enemies.get(i)).myX, ((Enemy)Enemies.get(i)).myY, true, col));
            Enemies.add(new MiniSquare(((Enemy)Enemies.get(i)).myX, ((Enemy)Enemies.get(i)).myY, false, col));
          }
          
          if ((((Enemy)Enemies.get(i)).whiteCOUNT == 1) && (lowcount > 400)) {
            Enemies.add(new MiniAvoid(((Enemy)Enemies.get(i)).myX, ((Enemy)Enemies.get(i)).myY, true, col));
            Enemies.add(new MiniAvoid(((Enemy)Enemies.get(i)).myX, ((Enemy)Enemies.get(i)).myY, false, col));
          }
          
          if (((Enemy)Enemies.get(i)).yellowCOUNT == 1) {
            Enemies.add(new MiniDiamond(((Enemy)Enemies.get(i)).myX, ((Enemy)Enemies.get(i)).myY, true, col));
            Enemies.add(new MiniDiamond(((Enemy)Enemies.get(i)).myX, ((Enemy)Enemies.get(i)).myY, false, col));
          }
          
          if (((Enemy)Enemies.get(i)).spinnerCOUNT == 1) {
            Enemies.add(new MiniSpinner(((Enemy)Enemies.get(i)).myX, ((Enemy)Enemies.get(i)).myY, true, col));
            Enemies.add(new MiniSpinner(((Enemy)Enemies.get(i)).myX, ((Enemy)Enemies.get(i)).myY, false, col));
          }
          
          if ((((Enemy)Enemies.get(i)).minispinCOUNT == 1) && (lowcount > 100)) {
            Enemies.add(new MiniMiniSpinner(((Enemy)Enemies.get(i)).myX, ((Enemy)Enemies.get(i)).myY, true, col));
            Enemies.add(new MiniMiniSpinner(((Enemy)Enemies.get(i)).myX, ((Enemy)Enemies.get(i)).myY, false, col));
          }
          
          for (Bomb b : player.Bombs) {
            if ((int)Math.sqrt((((Enemy)Enemies.get(i)).myX - b.myX) * (((Enemy)Enemies.get(i)).myX - b.myX) + (((Enemy)Enemies.get(i)).myY - b.myY) * (((Enemy)Enemies.get(i)).myY - b.myY)) < b.radius) {
              ((Enemy)Enemies.get(i)).isDEAD = true;
            }
          }
          

          if (((Enemy)Enemies.get(i)).isDONE) {
            Enemies.remove(i);
          }
        }
        

        for (Explosion w : WallExplosions) {
          w.act();
        }
        
        for (int i = WallExplosions.size() - 1; i >= 0; i--) {
          ((Explosion)WallExplosions.get(i)).act();
          if (((Explosion)WallExplosions.get(i)).done) {
            WallExplosions.remove(i);
          }
        }
        

        playertail.add(player);
        
        if (framecount > 30) {
          playertail.remove();
        }
      }
      
      player.act();
    }
    
    if ((!PAUSED) && (highQUALITY))
    {
      for (Random w : Warps) {
        w.act(player);
      }
    }

    repaint();
  }
  
  public void keyTyped(KeyEvent e) {}
  
  public void keyPressed(KeyEvent e)
  {
    player.keyPressed(e);
    
    if (e.getKeyCode() == 27) {
      System.exit(0);
    }
    
    if (e.getKeyCode() == 72) {
      if (highQUALITY) {
        highQUALITY = false;
      } else {
        highQUALITY = true;
      }
    }
    
    if (e.getKeyCode() == 80) {
      if (PAUSED) {
        PAUSED = false;
      } else {
        PAUSED = true;
      }
    }
  }
  
  public void keyReleased(KeyEvent e) {
    player.keyReleased(e);
  }
  
  public void mouseClicked(MouseEvent e) {}
  
  public void mousePressed(MouseEvent e)
  {
    clicked = true;
  }
  
  public void mouseReleased(MouseEvent e) {
    clicked = false;
  }
  

  public void mouseEntered(MouseEvent e) {}
  
  public void mouseExited(MouseEvent e) {}
  
  private static String addCommas(int num)
  {
    String initial = "" + num;
    if ((num > 999999) && (num < 10000000)) {
      return initial.substring(0, 1) + "," + initial.substring(1, 4) + "," + initial.substring(4);
    }
    if ((num > 99999) && (num < 1000000)) {
      return initial.substring(0, 3) + "," + initial.substring(3);
    }
    if ((num > 9999) && (num < 100000)) {
      return initial.substring(0, 2) + "," + initial.substring(2);
    }
    if ((num > 999) && (num < 10000)) {
      return initial.substring(0, 1) + "," + initial.substring(1);
    }
    return initial;
  }
                
  private static class BGPanel extends javax.swing.JPanel
  {
    public BGPanel() {
      setSize(1000, 800);
    }
                                
    public void paintComponent(Graphics g) {
      if (ArtificialIntelligence.Play == true) {
        ArtificialIntelligence.gg.clearRect(0, 0, ArtificialIntelligence.dim.width, ArtificialIntelligence.dim.width);
                                                                
        if (ArtificialIntelligence.highQUALITY) {
          for (Random w : ArtificialIntelligence.Warps) {
            w.paint(ArtificialIntelligence.gg);
          }
        }
                                                                
        ArtificialIntelligence.stars.paint(ArtificialIntelligence.gg);
                                                                
        if (!ArtificialIntelligence.player.isDEAD) {
          for (Explosion w : ArtificialIntelligence.WallExplosions) {
            w.paint(ArtificialIntelligence.gg);
          }
                                                                                
          if ((ArtificialIntelligence.framecount > 25) && (!ArtificialIntelligence.PAUSED)) {
            ArtificialIntelligence.playertail.paint(ArtificialIntelligence.gg);
          }
        }
        if (!ArtificialIntelligence.player.isDEAD) {
          for (Enemy e : ArtificialIntelligence.Enemies) {
            e.paint(ArtificialIntelligence.gg);
                                                                                                
            for (int i = ArtificialIntelligence.player.Shots.size() - 1; i >= 0; i--) {
              if ((((Shot)ArtificialIntelligence.player.Shots.get(i)).active) && (e.isShot((Shot)ArtificialIntelligence.player.Shots.get(i)))) {
                ArtificialIntelligence.player.Shots.remove(i);
                ArtificialIntelligence.killcount += 1;
              }
            }
            if ((!e.isDEAD) && (ArtificialIntelligence.INTERSECTION.clearIntersection(ArtificialIntelligence.player.poly, e.poly)) && (ArtificialIntelligence.player.Bombs.size() < 1)) {
              ArtificialIntelligence.Killer.add(e);
              ArtificialIntelligence.player.LIVES -= 1;
              ArtificialIntelligence.player.isDEAD = true;
            }
          }
        }
        if (ArtificialIntelligence.player.isDEAD) {
          ArtificialIntelligence.Enemies.clear();
          ArtificialIntelligence.player.Shots.clear();
          ArtificialIntelligence.player.deadCOUNT += 1;
                                                                                
          if ((ArtificialIntelligence.player.explosion.size() > 0) && (((ExplosionShot)ArtificialIntelligence.player.explosion.get(0)).brightness <= 0.0F)) {
            ArtificialIntelligence.player.isDEAD = false;
            ArtificialIntelligence.player.deadCOUNT = 0;
            ArtificialIntelligence.Killer.clear();
          }
        }
        for (Enemy e : ArtificialIntelligence.Killer) {
          e.paint(ArtificialIntelligence.gg);
        }
                                                                
        if (!ArtificialIntelligence.GAMEOVER) {
          ArtificialIntelligence.player.paint(ArtificialIntelligence.gg);
        } else {
          ArtificialIntelligence.gg.setFont(ArtificialIntelligence.Font3);
          ArtificialIntelligence.gg.setColor(Color.GREEN);
          ArtificialIntelligence.gg.drawString("GAME OVER", 380, 395);
        }
      }
      ArtificialIntelligence.gg.setFont(ArtificialIntelligence.Font1);
      if (!ArtificialIntelligence.GAMEOVER) {
        ArtificialIntelligence.gg.setColor(Color.GREEN);
        ArtificialIntelligence.gg.drawString("Press P to Pause", 860, 750);
      } else {
        ArtificialIntelligence.gg.setColor(Color.BLUE);
        ArtificialIntelligence.gg.drawString("Awesome Game", 825, 750);
      }
      ArtificialIntelligence.gg.setColor(Color.MAGENTA);
      if (ArtificialIntelligence.PAUSED) {
        ArtificialIntelligence.gg.setFont(ArtificialIntelligence.Font3);
        ArtificialIntelligence.gg.drawString("PAUSED", 420, 395);
      }
                                                
      ArtificialIntelligence.gg.setFont(ArtificialIntelligence.Font2);
      ArtificialIntelligence.gg.drawString("SCORE: ", 20, 30);
      ArtificialIntelligence.gg.setFont(ArtificialIntelligence.Font5);
      ArtificialIntelligence.gg.drawString(ArtificialIntelligence.player.LIVES + " ♥", 440, 35);
      ArtificialIntelligence.gg.drawString(ArtificialIntelligence.player.BOMBS + " ☢", 510, 35);
                                                
      ArtificialIntelligence.gg.setFont(ArtificialIntelligence.Font3);
      ArtificialIntelligence.gg.drawString("" + ArtificialIntelligence.addCommas(ArtificialIntelligence.player.score), 20, 65);
                                                
      ArtificialIntelligence.playermouse.paint(ArtificialIntelligence.gg);
                                                
      g.drawImage(ArtificialIntelligence.offscreen, 0, 0, this);
    }
  }
}