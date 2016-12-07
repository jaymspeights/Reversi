//the GameBoard class represents the board that contains the game being played.
//it has a 2 dimensional array that will contain the game pieces, and an assortment
//of methods that encompass the bulk of the code, such as getMove() to check if a move is valid,
//and getAllMoves() to find any remaing moves for a specific player.
//toString() is overrided in this class to allow simple printing of the board state.
import java.util.ArrayList;
public class GameBoard {
  private Piece[][] space;
  int n;
  int count = 0;
  int index = 0;
  int size;
  int[] op;
  public GameBoard(int n){
    space = new Piece[n][n];
    this.n = n;
    size = (int)(Math.pow(n,2));
    op = new int[size];
    for (int i = 0; i < n; i++){
        for (int j = 0; j < n; j++){
            space[i][j] = new Piece();
        }
      }
    space[n/2-1][n/2-1] = new Piece("b", n/2-1,n/2-1);
    space[n/2][n/2-1] = new Piece("w", n/2,n/2-1);
    space[n/2][n/2] = new Piece("b", n/2,n/2);
    space[n/2-1][n/2] = new Piece("w", n/2-1,n/2);
  }

  public int getN(){
	  return n;
  }
  public ArrayList<Move> getMove(String color, int x, int y){
      ArrayList<Move> flip = new ArrayList();
      count++;
      if (x<0 || y<0 || x>=n || y>=n || !(space[x][y].getColor().equals("-")))
          return flip;
      for (int i = -1; i < 2; i++){
        for (int j = -1; j < 2; j++){
            count++;
            int num = recursiveCheck(color, x, y, i, j);
            if (num>0)
                flip.add(new Move(color, x, y, i, j, num));
        }   
      }
      return flip;
  }
 
  private int recursiveCheck(String color, int x, int y, int xDir, int yDir){
    return recursiveCheck(color, x, y, xDir, yDir, 0);
  }
  private int recursiveCheck(String color, int x, int y, int xDir, int yDir, int count){
      count++;
      x += xDir;
      y += yDir;
      int num = 0;
      if (x<0 || x >= space.length || y<0 || y >= space.length)
          return 0;
      if (space[x][y].getColor().equals(color))
          return count;
      if (!(space[x][y].getColor().equals("-"))){
          count++;
          num = recursiveCheck(color, x, y, xDir, yDir, count);
          if (num>0)
              return num;
      }
      return 0;
  }
  
  public void makeMove(ArrayList<Move> moves, String color){
      for(Move m: moves){
          recursiveFlip(m.getColor(), m.getX(), m.getY(), m.getxDir(), m.getyDir());
      }
      space[moves.get(0).getX()][moves.get(0).getY()] = new Piece(color, moves.get(0).getX(), moves.get(0).getY());
  }
  private boolean recursiveFlip(String color, int x, int y, int xDir, int yDir){
      x += xDir;
      y += yDir;
      if (x<0 || x >= space.length || y<0 || y >= space.length)
          return false;
      if (space[x][y].getColor().equals(color))
          return true;
      if (!(space[x][y].getColor().equals("-")))
          if(recursiveFlip(color, x, y, xDir, yDir)){
              space[x][y].flip();
              return true;
          }
      return false;
  }
  
  public int[] getScore(){
    int[] score = new int[2];
    score[0]=0;
    score[1]=0;
    for (int i = 0; i < space[0].length; i++){
      for (int j = 0; j < space.length; j++){
        if (space[j][i].getColor().equals("b")) score[0]++;
        else if (space[j][i].getColor().equals("w")) score[1]++;
      }
    }
    return score;
  }

  @Override
  public String toString(){
    String str = "";
    for (int i = 0; i < space.length; i++){
      for (int j = 0; j < space[0].length; j++){
        str += space[j][i].getColor()+" ";
      }
      str += "\n";
    }
    str += "\nWhite: " + getScore()[1] + " Black: " + getScore()[0];
    return str;
  }
  
  public ArrayList<ArrayList<Move>> getAllMoves(String color){
      ArrayList<ArrayList<Move>> moves = new ArrayList();
      for (int i = 0; i < space[0].length; i++){
        for (int j = 0; j < space.length; j++){
            if (space[i][j].getColor().equals("-")){
                ArrayList<Move> m = getMove(color, i, j);
                if (!m.isEmpty() && m.get(0).getNum()>0)
                 moves.add(m);
            }
        }
      }
      return moves;
  }
  
  public boolean checkAllMoves(String color){
      count = 0;
      ArrayList<ArrayList<Move>> moves = new ArrayList();
      for (int i = 0; i < space[0].length; i++){
        for (int j = 0; j < space.length; j++){
            count++;
            if (space[i][j].getColor().equals("-")){
                ArrayList<Move> m = getMove(color, i, j);
                count++;
                if (!m.isEmpty() && m.get(0).getNum()>0)
                 moves.add(m);
            }
        }
      }
      op[index] = count;
      index++;
      return moves.size()>0;
  }
  
  public int[] getOp(){
      return op;
  }
  public int getCount(){
      return count;
  }
  public int getIndex(){
      return index;
  }
}
