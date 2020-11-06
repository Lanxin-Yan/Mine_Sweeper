import javax.swing.JOptionPane;

public class BombSquare extends GameSquare
{

    private GameBoard board;                            // Object reference to the GameBoard this square is part of.
    private boolean hasBomb;                            // True if this squre contains a bomb. False otherwise.
    private int count;                                  
    BombSquare compBomb;                                
    private boolean blank;

    public static final int MINE_PROBABILITY = 7;       // Probability of bombs, the higher the number, the less the bombs, vice-versa.

    /**
     * This class inherited all methods and variables from GameSquare.java, edited with extensive methods to allow actionlistener to function.
     * @param x y location of this square
     * @param y y location of this square
     * @param board Game Board
     */
	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png");

        this.board = board;
        this.hasBomb = ((int) (Math.random() * MINE_PROBABILITY)) == 0;
        this.blank = true;
    }

    public void leftClicked()
    {
        count = 0;                                       // Reset every click so numbers on square won't double.
        if (hasBomb){   
            this.setImage("images/bomb.png");

            for (int i = 0; i < 29; i ++){
                for (int x = 0; x < 29; x++){
                    BombSquare e = (BombSquare)board.getSquareAt(i, x);
                    if (e.hasBomb) e.setImage("images/bomb.png");
                }
            }

            JOptionPane.showMessageDialog(null, "You lose!");
            board.dispose();
            GameBoard b = new GameBoard("BugSweeper", 30, 30, "BombSquare");
        }   
        else{
            recursiveCall(this.getXLocation(), this.getYLocation());
            }
        }
    

    public void rightClicked() { 
        this.setImage("images/flag.png");                  
    }

    /**
     * recursive function that opens up all the non-bomb squares around
     * @param x the x loc of 'this' bombsquare
     * @param y the y loc of 'this' bombsquare
     */
    public void recursiveCall(int x, int y) {

        if (x < 0 || y < 0 || x > 29 || y > 29) return;                     //there are chances where boxes are out-of bounds, so here a checker to stop NullPointerException error.
    
        for (int dx = -1; dx < 2; ++dx)
            for (int dy = -1; dy < 2; ++dy) {
                try {
                    BombSquare nei = (BombSquare)board.getSquareAt(x+dx,y+dy);  
                    if (nei.hasBomb) count++;                               //count the amount of bomb after scanning
                    this.blank = false;                 

                } catch (NullPointerException e) {  
                }   
            }

        this.setImage("images/" + count + ".png");                          //set the image regarding on to the 'count' of number of bombs
    
        if (count > 0) return;
        
        for (int dx = -1; dx < 2; ++dx)
            for (int dy = -1; dy < 2; ++dy) {
                if (x+dx < 0 || x+dx > 29 || y+dy < 0 || y+dy > 29) continue;
                BombSquare nei = (BombSquare)board.getSquareAt(x+dx,y+dy);
                if (nei.blank)                              //blank means there is no bomb 
                    nei.recursiveCall(x+dx,y+dy);   
                }
            }
}
