//Player is an abstract class that sets the framework for
//the PC and NPC class
public abstract class Player {
    private String color;
    private GameBoard board;
    public Player(String color, GameBoard board){
        this.color = color;
        this.board = board;
    }
    
    public abstract void move();
    
    public String getColor(){
        return color;
    }
}
