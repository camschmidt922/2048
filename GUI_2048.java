import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//Cameron Schmidt
//Program description: 
//May 27, 2020

public class GUI_2048 extends JPanel implements KeyListener
{
   //variables
   private final int PREF_W = 400;
   private final int PREF_H = 400;
   
   private Board2048 board;

   
   
   //constructor
   public GUI_2048()
   {
      this.addKeyListener(this);
      this.setFocusable(true);
//      this.requestFocus();
      
      board = new Board2048();
      board.set();      
   }
   
   
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      board.draw(g2);
      
      if(board.isGameOver())
         board.showGameOverMessage(g2);
   }
   
   


   @Override
   public void keyPressed(KeyEvent e)
   {
      int key = e.getKeyCode();
      
      if((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && board.canMoveLeft()) {
         board.moveLeft();
         board.spawnValue(getSpawnValue());
      }
      if((key == KeyEvent.VK_RIGHT  || key == KeyEvent.VK_D) && board.canMoveRight()) {
         board.moveRight();
         board.spawnValue(getSpawnValue());
      }
      if((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && board.canMoveUp()) {
         board.moveUp();
         board.spawnValue(getSpawnValue());
      }
      if((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && board.canMoveDown()) {
         board.moveDown();
         board.spawnValue(getSpawnValue());
      }
      
      if(key == KeyEvent.VK_R && board.isGameOver())
         board.reset();
      
      
//      System.out.println();
//      board.showBoard();
      repaint();
   }

 

   @Override
   public void keyReleased(KeyEvent e)
   {
      int key = e.getKeyCode();
   }
   
   
   @Override
   public void keyTyped(KeyEvent e){}
   
   public static int getSpawnValue()
   {
      int[] nums = new int[10];
      for(int i = 0; i < nums.length - 1; i++)
         nums[i] = 2;
      nums[9] = 4;
      
      return nums[(int)(Math.random()*10)];
   }
   
   
   //************ CODE TO CREATE THE FRAME FOR THE PANEL ************\\
   
   public Dimension getPreferredSize()
   {
      return new Dimension(PREF_W, PREF_H);
   }
   
   public static void createAndShowGUI()
   {
      JFrame frame = new JFrame("2048"); //Insert title
      JPanel gamePanel = new GUI_2048();
      
      frame.getContentPane().add(gamePanel);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setResizable(false); //SET DEPENDING ON GAME
   }
   
   public static void main(String[] args)
   {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGUI();
         }
      });
   }
}
