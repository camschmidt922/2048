import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

//Cameron Schmidt
//Program description: 
//May 13, 2020

public class Board2048
{
   private Tile2048 [][] board;

   public Board2048()
   {
      board = new Tile2048[4][4];
      for(int r = 0; r < board.length; r++)
         for(int c = 0; c < board[r].length; c++)
            board[r][c] = new Tile2048(0);
   }

   public void showBoard()
   {
      for(int r = 0; r < board.length; r++)
      {
         for(int c = 0; c < board[0].length; c++)
            System.out.print(board[r][c].getValue() + " ");
         System.out.println();
      }
      System.out.println();
   }

   public void moveLeft()
   {
      boolean[][] combined = new boolean[board.length][board[0].length];

      for(int r = 0; r < board.length; r++)
      {
         for(int c = 1; c < board[0].length; c++)
         {
            if(board[r][c].getValue() != 0)
            {
               // col is column to right of first non-zero location
               int col = c;
               while(col-1 >= 0 && board[r][col-1].getValue() == 0)
                  col--;
               //if col makes it to column 0, then move to that column
               if(col == 0)
               {
                  board[r][col].setValue(board[r][c].getValue());
                  board[r][c].setValue(0);
               }
               //if col is not first column, see if col can combine
               else if(!combined[r][col-1] && board[r][col-1].getValue() == board[r][c].getValue())
               {
                  board[r][col-1].setValue(board[r][col-1].getValue() + board[r][c].getValue());
                  board[r][c].setValue(0);
                  combined[r][col-1] = true;
               }
               //tile moved, but cannot combine
               else if(c != col)
               {
                  board[r][col].setValue(board[r][c].getValue());
                  board[r][c].setValue(0);
               }
            }
         }//end of c loop
      }//end of r loop
   }

   public void moveRight()
   {
      boolean[][] combined = new boolean[board.length][board[0].length];

      for(int r = 0; r < board.length; r++)
      {
         for(int c = board[r].length-2; c >= 0; c--)
         {
            if(board[r][c].getValue() != 0)
            {
               // col is column to left of last non-zero location
               int col = c;
               while(col+1 <= board[r].length-1 && board[r][col+1].getValue() == 0)
                  col++;
               //if col makes it to last column, then move to that column
               if(col == board[r].length - 1)
               {
                  board[r][col].setValue(board[r][c].getValue());
                  board[r][c].setValue(0);
               }
               //if col is not last column, see if col can combine
               else if(!combined[r][col+1] && board[r][col+1].getValue() == board[r][c].getValue())
               {
                  board[r][col+1].setValue(board[r][col+1].getValue()+board[r][c].getValue());
                  board[r][c].setValue(0);
                  combined[r][col+1] = true;
               }
               //tile moved, but cannot combine
               else if(c != col)
               {
                  board[r][col].setValue(board[r][c].getValue());
                  board[r][c].setValue(0);
               }
            }
         }//end of c loop
      }//end of r loop
   }

   public void moveUp()
   {
      boolean[][] combined = new boolean[board.length][board[0].length];

      for(int c = 0; c < board[0].length; c++)
      {
         for(int r = 1; r < board[0].length; r++)
         {
            if(board[r][c].getValue() != 0)
            {
               // row is row above of first non-zero location
               int row = r;
               while(row-1 >= 0 && board[row-1][c].getValue() == 0)
                  row--;
               //if row makes it to row 0, then move to that row
               if(row == 0)
               {
                  board[row][c].setValue(board[r][c].getValue());
                  board[r][c].setValue(0);
               }
               //if rpw is not first row, see if row can combine
               else if(!combined[row-1][c] && board[row-1][c].getValue() == board[r][c].getValue())
               {
                  board[row-1][c].setValue(board[row-1][c].getValue() + board[r][c].getValue());
                  board[r][c].setValue(0);
                  combined[row-1][c] = true;
               }
               //tile moved, but cannot combine
               else if(r != row)
               {
                  board[row][c].setValue(board[r][c].getValue());
                  board[r][c].setValue(0);
               }
            }
         }//end of r loop
      }//end of c loop
   }

   public void moveDown()
   {
      boolean[][] combined = new boolean[board.length][board[0].length];

      for(int c = 0; c < board[0].length; c++)
      {
         for(int r = board.length - 2; r >= 0; r--)
         {
            if(board[r][c].getValue() != 0)
            {
               // row is row below of first non-zero location
               int row = r;
               while(row+1 <= board.length-1 && board[row+1][c].getValue() == 0)
                  row++;
               //if row makes it to last row , then move to that row
               if(row == board.length - 1)
               {
                  board[row][c].setValue(board[r][c].getValue());
                  board[r][c].setValue(0);
               }
               //if row is not first row, see if row can combine
               else if(!combined[row+1][c] && board[row+1][c].getValue() == board[r][c].getValue())
               {
                  board[row+1][c].setValue(board[row+1][c].getValue()+board[r][c].getValue());
                  board[r][c].setValue(0);
                  combined[row+1][c] = true;
               }
               //tile moved, but cannot combine
               else if(r != row)
               {
                  board[row][c].setValue(board[r][c].getValue());
                  board[r][c].setValue(0);
               }
            }
         }//end of r loop
      }//end of c loop
   }

   public boolean canMoveLeft()
   {
      for(int r = 0; r < board.length; r++)
      {
         for(int c = 1; c < board[0].length; c++)
         {
            if(board[r][c].getValue() != 0)
            {
               // col is column to right of first non-zero location
               int col = c;
               while(col-1 >= 0 && board[r][col-1].getValue() == 0)
                  col--;
               //can a move be made?
               if(col != c || board[r][c-1].getValue() == board[r][c].getValue())
                  return true;
            }
         }//end of c loop
      }//end of r loop
      return false;
   }

   public boolean canMoveRight()
   {
      for(int r = 0; r < board.length; r++)
      {
         for(int c = board[r].length-2; c >= 0; c--)
         {
            if(board[r][c].getValue() != 0)
            {
               // col is column to left of last non-zero location
               int col = c;
               while(col+1 <= board.length-1 && board[r][col+1].getValue() == 0)
                  col++;
               //can a move be made?
               if(col != c || board[r][c+1].getValue() == board[r][c].getValue())
                  return true;
            }
         }//end of c loop
      }//end of r loop
      return false;
   }

   public boolean canMoveUp()
   {
      for(int c = 0; c < board[0].length; c++)
      {
         for(int r = 1; r < board[0].length; r++)
         {
            if(board[r][c].getValue() != 0)
            {
               // row is row above of first non-zero location
               int row = r;
               while(row-1 >= 0 && board[row-1][c].getValue() == 0)
                  row--;
               //can a move be made?
               if(row != r || board[r-1][c].getValue() == board[r][c].getValue())
                  return true;
            }
         }//end of r loop
      }//end of c loop
      return false;
   }
   
   public boolean canMoveDown()
   {
      boolean[][] combined = new boolean[board.length][board[0].length];

      for(int c = 0; c < board[0].length; c++)
      {
         for(int r = board.length - 2; r >= 0; r--)
         {
            if(board[r][c].getValue() != 0)
            {
               // row is row below of first non-zero location
               int row = r;
               while(row+1 <= board.length-1 && board[row+1][c].getValue() == 0)
                  row++;
               //can a move be made?
               if(row != r || board[row+1][c].getValue() == board[r][c].getValue())
                  return true;
            }
         }//end of r loop
      }//end of c loop
      return false;
   }
   
   public boolean canMove()
   {
      if(canMoveLeft() && canMoveRight() && canMoveUp() && canMoveDown())
         return true;
      return false;
   }
   
   public int getNumEmpyLocations()
   {
      int count = 0;
      for(Tile2048[] rows : board)
         for(Tile2048 t : rows)
            if(t.getValue()==0)
               count++;
      return count;
   }

   public void set()
   {
      spawnValue(2);
      spawnValue(2);
   }
   
   public void spawnValue(int num)
   {
      int numEmpty = getNumEmpyLocations();
      int randomLocation = (int)(Math.random() * numEmpty) + 1;
      //      System.out.println("LOCATION: "+randomLocation);
      int currentEmptyCount = 0;
      for(int r = 0; r < board.length; r++)
         for(int c = 0; c < board[r].length; c++)
            if(board[r][c].getValue() == 0)
            {
               currentEmptyCount++;
               //               System.out.println(currentEmptyCount);
               if(currentEmptyCount == randomLocation)
               {
                  board[r][c].setValue(num);
                  //                  System.out.println("generate");
               }
            }
   }

   public void spawnValue(int r, int c, int num)
   {
      board[r][c].setValue(num);
   }
   
   public void draw(Graphics2D g2) 
   {
      int w = 100;
      int h = 100;
      for(int r = 0; r < board.length; r++)
      {
         for(int c = 0;  c < board[r].length; c++)
            board[r][c].draw(g2, c*w, r*h, w, h);
      }
   }
   
   public boolean isGameOver()
   {
      return(!canMoveLeft() && !canMoveRight() && !canMoveUp() && !canMoveDown());
   }
   
   public void showGameOverMessage(Graphics2D g2)
   {
      g2.setColor(new Color(230, 225, 220, 190));
      g2.fillRect(0, 0, 400, 400);
      
      g2.setColor(new Color (250, 246, 242, 255));
      g2.setFont(new Font("Futura", Font.PLAIN, 30));
      FontMetrics fm = g2.getFontMetrics();
      int width = fm.stringWidth("GAME OVER");
      g2.drawString("GAME OVER", 200 - width/2,190);
      g2.setFont(new Font("Futura", Font.PLAIN, 20));
      fm = g2.getFontMetrics();
      width = fm.stringWidth("PRESS R TO RESTART");
      g2.drawString("PRESS R TO RESTART", 200 - width/2, 225);
      
      
   }
   
   public void reset()
   {
      for(int r = 0; r < board.length; r++)
         for(int c = 0; c < board[r].length; c++)
            board[r][c].setValue(0);
      set();
   }
}
