
public class BombSquare extends GameSquare
{

    private GameBoard board;                            // Object reference to the GameBoard this square is part of.
    private boolean hasBomb;                            // True if this squre contains a bomb. False otherwise.
    private int count;
    BombSquare compBomb;
    private boolean blank;

    public static final int MINE_PROBABILITY = 5;

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png");

        this.board = board;
        this.hasBomb = ((int) (Math.random() * MINE_PROBABILITY)) == 0;
        this.blank = true;
    }

    public void leftClicked()
    {
        if (hasBomb){
            this.setImage("images/bomb.png");
        }
        else{
            recursiveCall(this.getXLocation(), this.getYLocation());
            }
        }
    

    public void rightClicked() {
        setImage("images/flag.png");
    }

    public void recursiveCall(int x, int y) {

        if (x < 0 || y < 0 || x > 29 || y > 29) return;
    
        for (int dx = -1; dx < 2; ++dx)
            for (int dy = -1; dy < 2; ++dy) {
                try {
                    BombSquare nei = (BombSquare)board.getSquareAt(x+dx,y+dy);
                    if (nei.hasBomb) count++;
                
                } catch (NullPointerException e) {
                }   
            }

        this.setImage("images/" + count + ".png");
        this.blank = false;
    
        if (count > 0) return;
        for (int dx = -1; dx < 2; ++dx)
            for (int dy = -1; dy < 2; ++dy) {
                if (x+dx < 0 || x+dx > 29 || y+dy < 0 || y+dy > 29) continue;
                BombSquare nei = (BombSquare)board.getSquareAt(x+dx,y+dy);
                if (nei.blank)
                    nei.recursiveCall(x+dx,y+dy);
                }
            }
}
