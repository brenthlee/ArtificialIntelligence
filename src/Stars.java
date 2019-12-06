import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Stars
{
  private boolean initialized = false;
  private ArrayList<Integer> STARx = new ArrayList();
  private ArrayList<Integer> STARy = new ArrayList();
  private ArrayList<Integer> STAR1x = new ArrayList();
  private ArrayList<Integer> STAR1y = new ArrayList();
  private ArrayList<Integer> STAR2x = new ArrayList();
  private ArrayList<Integer> STAR2y = new ArrayList();
  
  public void initialize() {
    for (int i = 0; (i <= 1000) && (!this.initialized); i += 25) {
      this.STARx.add(new Integer(i));
      int ycurrent = (int)(Math.random() * 800.0D);
      this.STARy.add(new Integer(ycurrent));
    }
    int tempdist = 10;
    for (int i = 0; (i <= 2000) && (!this.initialized); i += tempdist) {
      this.STAR1x.add(Integer.valueOf(new Integer(i).intValue() - 500));
      int ycurrent = (int)(Math.random() * 1600.0D);
      this.STAR1y.add(new Integer(ycurrent - 400));
      tempdist = (int)(Math.random() * 30.0D) + 20;
    }
    for (int i = 0; (i <= 2000) && (!this.initialized); i += tempdist) {
      this.STAR2x.add(Integer.valueOf(new Integer(i).intValue() - 500));
      int ycurrent = (int)(Math.random() * 1600.0D);
      this.STAR2y.add(new Integer(ycurrent - 400));
      tempdist = (int)(Math.random() * 80.0D) + 20;
    }
    this.initialized = true;
  }
  
  public void act(Player p) {
    for (int i = 0; (i < this.STAR1x.size()) && ((p.movingUP) || (p.movingDOWN) || (p.movingLEFT) || (p.movingRIGHT)); i++) {
      Integer temp = new Integer(((Integer)this.STAR1x.get(i)).intValue() - p.moveX / 5);
      this.STAR1x.set(i, temp);
      Integer temp1 = new Integer(((Integer)this.STAR1y.get(i)).intValue() + p.moveY / 5);
      this.STAR1y.set(i, temp1);
    }
    for (int i = 0; (i < this.STAR2x.size()) && ((p.movingUP) || (p.movingDOWN) || (p.movingLEFT) || (p.movingRIGHT)); i++) {
      Integer temp = new Integer(((Integer)this.STAR2x.get(i)).intValue() - p.moveX / 3);
      this.STAR2x.set(i, temp);
      Integer temp1 = new Integer(((Integer)this.STAR2y.get(i)).intValue() + p.moveY / 3);
      this.STAR2y.set(i, temp1);
    }
  }
  
  public void paint(Graphics gg) {
    gg.setColor(Color.PINK);
             gg.setColor(Color.WHITE);
    for (int i = 0; i < this.STARx.size(); i++) {
      gg.fillRect(((Integer)this.STARx.get(i)).intValue(), ((Integer)this.STARy.get(i)).intValue(), 1, 1);
    }
    //gg.setColor(Color.PINK);
    for (int i = 0; i < this.STAR1x.size(); i++) {
      gg.fillRect(((Integer)this.STAR1x.get(i)).intValue(), ((Integer)this.STAR1y.get(i)).intValue(), 1, 1);
    }
    //gg.setColor(Color.PINK);
    for (int i = 0; i < this.STAR2x.size(); i++) {
      gg.fillRect(((Integer)this.STAR2x.get(i)).intValue(), ((Integer)this.STAR2y.get(i)).intValue(), 1, 1);
    }
  }
}