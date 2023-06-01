import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

//Cameron Schmidt
//Program description: 
//May 29, 2020

public class Tile2048
{
   private final Color BORDER_COLOR = new Color(190, 175, 160);
   private final Color DARK_FONT_COLOR = new Color(34, 34, 34);
   private final Color LIGHT_FONT_COLOR = new Color(250, 246, 242);
   private Color[] colors = {new Color(206, 193, 177),  //empty tile
                             new Color(240, 228, 217),  //2
                             new Color(240, 225, 197),  //4
                             new Color(250, 175, 108),  //8
                             new Color(255, 145, 82),   //16
                             new Color(255, 115, 82),   //32
                             new Color(255, 78, 16),    //64
                             new Color(240, 210, 96),   //128
                             new Color(240, 210, 72),   //256
                             new Color(240, 204, 42),   //512
                             new Color(240, 200, 0),    //1024
                             new Color(242, 198, 0),    //2048
                             new Color(60, 60, 50),     //4096
                           };
   
   private int value;
   private Color color;
   
   public Tile2048(int value)
   {
      this.value = value;
      assignColor();
   }

   public void draw(Graphics2D g2, int x, int y, int w, int h)
   {
      g2.setColor(color);
      g2.fillRect(x, y, w, h);
      g2.setStroke(new BasicStroke(10));
      g2.setColor(BORDER_COLOR);
      g2.drawRect(x, y, w, h);
      g2.setFont(new Font("Futura", Font.PLAIN, 35));
      FontMetrics fm = g2.getFontMetrics();
      int width = fm.stringWidth(value+"");
      int height = fm.getHeight();
      if(value == 2 || value == 4)
         g2.setColor(DARK_FONT_COLOR);
      else
         g2.setColor(LIGHT_FONT_COLOR);
      if(value != 0)
         g2.drawString(value+"", x+w/2 - width/2, y+h/2 + height/2 - 8);
   }
   
   public void assignColor()
   {
      int index = 0;
      if(value != 0)
         index = (int)(Math.log(value)/Math.log(2));
      color = colors[index];
   }
   
   public int getValue()
   {
      return value;
   }

   public void setValue(int value)
   {
      this.value = value;
      assignColor();
   }

   public Color getColor()
   {
      return color;
   }

   public void setColor(Color color)
   {
      this.color = color;
   }
}
