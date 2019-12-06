import java.awt.Color;
import java.awt.Graphics;

public class Mouse
{
  int mouseX = 0;
  int mouseY = 0;
  
  public void paint(Graphics gg)
  {
    gg.setColor(Color.WHITE);
    gg.drawLine(this.mouseX - 12, this.mouseY, this.mouseX - 6, this.mouseY);
    gg.drawLine(this.mouseX + 12, this.mouseY, this.mouseX + 6, this.mouseY);
    gg.drawLine(this.mouseX, this.mouseY + 12, this.mouseX, this.mouseY + 6);
    gg.drawLine(this.mouseX, this.mouseY - 12, this.mouseX, this.mouseY - 6);
  }
}