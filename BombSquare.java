import javax.swing.*;


public class BombSquare extends GameSquare
{
    /**
     *
     */
    private static final long serialVersionUID = 8456708344160245595L;
    private GameBoard board; // Object reference to the GameBoard this square is part of.
    private boolean hasBomb;                            // True if this squre contains a bomb. False otherwise.

    public static final int MINE_PROBABILITY = 10;
    
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
            int index = buttonNum(this.getXLocation(), this.getYLocation());
            //System.out.println(this.getXLocation());
            //System.out.println(this.getYLocation());
            //buttonNum(this.getXLocation(), this.getYLocation());
            System.out.println("The num on this square should be:" + buttonNum(this.getXLocation(), this.getYLocation()));
            if (index == 0) this.setImage("images/0.png");
            if (index == 1) this.setImage("images/1.png");
            if (index == 2) this.setImage("images/2.png");
            if (index == 3) this.setImage("images/3.png");
            if (index == 4) this.setImage("images/4.png");
            if (index == 5) this.setImage("images/5.png");
            if (index == 6) this.setImage("images/6.png");
            if (index == 7) this.setImage("images/7.png");
            if (index == 8) this.setImage("images/8.png");
            if (index == 9) this.setImage("images/9.png");
            }
        }
    

    public void rightClicked() {
        setImage("images/flag.png");
    }

    public int buttonNum(int x, int y){
        int count = 0;

        for (int a = - 1; a < 2; a++){
            for (int b = -1 ; b < 2; b++){

                BombSquare compBomb = (BombSquare) board.getSquareAt(a + x, b + y);

                System.out.println("x is" + (a + x));
                System.out.println("y is" + (b + y));
                try{ 
                    if(compBomb.hasBomb){
                        System.out.println("Got a bomb");
                        count ++;
                    }
                    else{
                        System.out.println("No bomb");
                    }
                }catch(NullPointerException e){ System.out.println("Null error");} 
            }
        }
         return count;
    }
}
