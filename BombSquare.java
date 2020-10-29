import javax.swing.*;


public class BombSquare extends GameSquare
{
    private GameBoard board;                            // Object reference to the GameBoard this square is part of.
    private boolean hasBomb;                            // True if this squre contains a bomb. False otherwise.

    public static final int MINE_PROBABILITY = 2;
    
    //private int num;
    Icon bomb = new ImageIcon("images/bomb.png");

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png");

        this.board = board;
        this.hasBomb = ((int) (Math.random() * MINE_PROBABILITY)) == 0;
    }


    public void leftClicked() {
        //System.out.println(hasBomb);
        if (hasBomb){
            this.setImage("images/bomb.png");
        }
        else{
            

            }
        }
    

    public void rightClicked() {
        setImage("images/flag.png");
    }

    /*public int buttonNum(int x, int y){
        
        
            }


        return num;
    }*/
}
