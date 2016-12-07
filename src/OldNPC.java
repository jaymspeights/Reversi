//The NPC class represents a Non Player Controlled player.
//It's main purpose is invoking the move method, which contains the simple AI
//used to carry out a turn.
//The move method logic first gathers all the moves, and then chooses the move that will flip the most pieces.
//if multiple moves flip the same amount of pieces, then it chooses one of those moves randomly.
import java.util.ArrayList;
public class OldNPC extends Player{
    private String color;
    private GameBoard board;
    public OldNPC(String color, GameBoard board){
        super(color, board);
        this.color = color;
        this.board = board;
    }
    
    /**
     *
     */
    @Override
    public void move(){
        ArrayList<ArrayList<Move>> moves = board.getAllMoves(color);
        double size = -10;
        double temp = -10;
        ArrayList<ArrayList<Move>> pick = new ArrayList();
        for (ArrayList<Move> m: moves){
            temp = 0;
            for (Move mm: m){
                temp += mm.getNum();
            }
            if (!m.isEmpty() && (m.get(0).getX()==0 || m.get(0).getX()==board.getN()-1) && (m.get(0).getY()==0 || m.get(0).getY()==board.getN()-1))
            	temp+=1.5;
            if (!m.isEmpty() && (m.get(0).getX()==1 || m.get(0).getX()==board.getN()-2))
            	temp-=3;
            if (!m.isEmpty() && (m.get(0).getY()==1 || m.get(0).getY()==board.getN()-2))
            	temp -=3;
            temp += Math.abs((double)m.get(0).getX() - (((double)board.getN()-1)/2))/board.getN()*2;
            temp += Math.abs((double)m.get(0).getY() - (((double)board.getN()-1)/2))/board.getN()*2;
            if (temp>size){
                size = temp;
            }
        }
        for (ArrayList<Move> m: moves){
            temp = 0;
            for (Move mm: m){
                temp += mm.getNum();
            }
            if (!m.isEmpty() && (m.get(0).getX()==0 || m.get(0).getX()==board.getN()-1) && (m.get(0).getY()==0 || m.get(0).getY()==board.getN()-1))
            	temp+=1.5;
            if (!m.isEmpty() && (m.get(0).getX()==1 || m.get(0).getX()==board.getN()-2))
            	temp-=3;
            if (!m.isEmpty() && (m.get(0).getY()==1 || m.get(0).getY()==board.getN()-2))
            	temp -=3;
            temp += Math.abs((double)m.get(0).getX() - (((double)board.getN()-1)/2))/board.getN()*2;
            temp += Math.abs((double)m.get(0).getY() - (((double)board.getN()-1)/2))/board.getN()*2;
            if (temp-size >= -.245){
                pick.add(m);
            }
        }
        board.makeMove(pick.get((int)(Math.random()*pick.size())), color);
    }
}
