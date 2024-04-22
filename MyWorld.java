import greenfoot.*;

public class MyWorld extends World
{  
    private MouseInfo mi;
    private int activePlayer = -1;
    private boolean cooldown = false;
    
    private int[][] board;
    
    public MyWorld()
    {    
        super(3, 3, 66);
        GreenfootImage bg = new GreenfootImage("grid.jpg");
        setBackground(bg);
        
        board = new int[3][3];
    }
    
    public void act() {
        mi = Greenfoot.getMouseInfo();
        if(mi != null && mi.getButton() == 1) {
            if(!cooldown) {
                if(activePlayer == 0 && valid(mi.getX(), mi.getY())) {
                    board[mi.getX()][mi.getY()] = -1;
                    addObject(new o(), mi.getX(), mi.getY());
                    activePlayer = 1;
                    if(checkWin(-1)) {
                        genText("Player 1 wins.");
                    }
                } else if (valid(mi.getX(), mi.getY())) {
                    board[mi.getX()][mi.getY()] = 1;
                    addObject(new x(), mi.getX(), mi.getY());
                    activePlayer = 0;
                    if(checkWin(1)) {
                        genText("Player 2 wins.");
                    }
                }
                cooldown = true;
            } else {cooldown = false;}
        }
    }
    
    public boolean checkWin(int player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }
        // Check diagonal
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }
    
    public boolean valid(int x, int y) {
        if(board[x][y] != 0) {
            return false;
        } else {return true;}
    }
    
    public void genText(String text) {
        GreenfootImage image =  new GreenfootImage(text, 20, Color.BLACK, new Color(0,0,0,0));
        showText(text, 1, 1);
    }
}