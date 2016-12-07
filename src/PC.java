//PC represents a Player Controlled player in the game, and it's main use is 
//invoking it's move method, which allows keyboard input to control the game.
import java.util.ArrayList;
import java.util.Scanner;
public class PC extends Player{
    Scanner scanner = new Scanner(System.in);
    private String color;
    private GameBoard board;
    public PC(String color, GameBoard board){
        super(color, board);
        this.color = color;
        this.board = board;
    }
    
    /**
     *
     */
    @Override
    public void move(){
        String x, y;
        int x1 = -1;
        int y1 = -1;
        ArrayList<Move> move;
        while(true){
            System.out.println("Where would you like to place your piece? ('X Location' [space] 'Y Location')\n");
            
            x = scanner.next();
            try{
                x1 = Integer.parseInt(x);
            }catch (Exception e){
            }
            
            y = scanner.next();
            try{
                y1 = Integer.parseInt(y);
            }catch (Exception e){
            }
            
            move = board.getMove(color, x1-1, y1-1);
            if (move.isEmpty())
                System.out.println("This is not a valid move");
            else break;
        }
        board.makeMove(move, color);
    }
}
