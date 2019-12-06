import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Tail {
  ArrayList<Integer> posX = new ArrayList();
  ArrayList<Integer> posY = new ArrayList();
  float hue = 0.15F;
  boolean up = true;
  
  public void add(Player p)
  {
    this.posX.add(Integer.valueOf(p.myX));
    this.posY.add(Integer.valueOf(p.myY));
  }
  
  public void remove() {
    this.posX.remove(0);
    this.posY.remove(0);
  }
  
  public void paint(Graphics gg) {
    this.hue += 0.005F;
    if (this.hue >= 0.15D) {
      this.hue = 0.06F;
    }
    
    gg.setColor(Color.MAGENTA);
    
    gg.fillRect(((Integer)this.posX.get(this.posX.size() - 6)).intValue() + (int)(Math.random() * 7.0D) - 3, ((Integer)this.posY.get(this.posY.size() - 6)).intValue() + (int)(Math.random() * 7.0D) - 3, 1, 1);
    
    gg.fillRect(((Integer)this.posX.get(this.posX.size() - 7)).intValue() + (int)(Math.random() * 7.0D) - 3, ((Integer)this.posY.get(this.posY.size() - 7)).intValue() + (int)(Math.random() * 7.0D) - 3, 1, 1);
    
    gg.fillRect(((Integer)this.posX.get(this.posX.size() - 8)).intValue() + (int)(Math.random() * 7.0D) - 3, ((Integer)this.posY.get(this.posY.size() - 8)).intValue() + (int)(Math.random() * 7.0D) - 3, 1, 1);
    
    gg.fillRect(((Integer)this.posX.get(this.posX.size() - 9)).intValue() + (int)(Math.random() * 7.0D) - 3, ((Integer)this.posY.get(this.posY.size() - 9)).intValue() + (int)(Math.random() * 7.0D) - 3, 1, 1);
    
    gg.setColor(Color.BLUE);
    gg.drawLine(((Integer)this.posX.get(this.posX.size() - 2)).intValue() + (int)(Math.random() * 3.0D) - 1, ((Integer)this.posY.get(this.posY.size() - 2)).intValue() + (int)(Math.random() * 3.0D) - 1, ((Integer)this.posX.get(this.posX.size() - 4)).intValue() + (int)(Math.random() * 5.0D) - 2, ((Integer)this.posY.get(this.posY.size() - 4)).intValue() + (int)(Math.random() * 5.0D) - 2);
    
    gg.setColor(Color.GREEN);
    
    gg.drawLine(((Integer)this.posX.get(this.posX.size() - 3)).intValue() + (int)(Math.random() * 3.0D) - 1, ((Integer)this.posY.get(this.posY.size() - 3)).intValue() + (int)(Math.random() * 3.0D) - 1, ((Integer)this.posX.get(this.posX.size() - 5)).intValue() + (int)(Math.random() * 7.0D) - 3, ((Integer)this.posY.get(this.posY.size() - 5)).intValue() + (int)(Math.random() * 7.0D) - 3);
    gg.drawLine(((Integer)this.posX.get(this.posX.size() - 3)).intValue() + (int)(Math.random() * 3.0D) - 1, ((Integer)this.posY.get(this.posY.size() - 3)).intValue() + (int)(Math.random() * 3.0D) - 1, ((Integer)this.posX.get(this.posX.size() - 5)).intValue() + (int)(Math.random() * 7.0D) - 3, ((Integer)this.posY.get(this.posY.size() - 5)).intValue() + (int)(Math.random() * 7.0D) - 3);
    
    gg.setColor(Color.YELLOW);
    
    gg.drawLine(((Integer)this.posX.get(this.posX.size() - 4)).intValue() + (int)(Math.random() * 5.0D) - 2, ((Integer)this.posY.get(this.posY.size() - 4)).intValue() + (int)(Math.random() * 5.0D) - 2, ((Integer)this.posX.get(this.posX.size() - 6)).intValue() + (int)(Math.random() * 5.0D) - 2, ((Integer)this.posY.get(this.posY.size() - 6)).intValue() + (int)(Math.random() * 5.0D) - 2);
    
    gg.setColor(Color.RED);
    
    gg.drawLine(((Integer)this.posX.get(this.posX.size() - 5)).intValue() + (int)(Math.random() * 3.0D) - 1, ((Integer)this.posY.get(this.posY.size() - 5)).intValue() + (int)(Math.random() * 3.0D) - 1, ((Integer)this.posX.get(this.posX.size() - 7)).intValue() + (int)(Math.random() * 3.0D) - 1, ((Integer)this.posY.get(this.posY.size() - 7)).intValue() + (int)(Math.random() * 3.0D) - 1);
    gg.drawLine(((Integer)this.posX.get(this.posX.size() - 6)).intValue() + (int)(Math.random() * 3.0D) - 1, ((Integer)this.posY.get(this.posY.size() - 6)).intValue() + (int)(Math.random() * 3.0D) - 1, ((Integer)this.posX.get(this.posX.size() - 8)).intValue() + (int)(Math.random() * 9.0D) - 4, ((Integer)this.posY.get(this.posY.size() - 8)).intValue() + (int)(Math.random() * 9.0D) - 4);
  }
}