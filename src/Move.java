//the Move class allows for the effects of placing a piece to be analyzed, without
//having to actually carry out the move.
//Move objects have a starting location, direction of effect, color, and number of pieces flipped
//this can be used by the NPC class to determine the strength of a move without making it.

public class Move {
    private int x;
    private int y;
    private String color;
    private int xDir;
    private int yDir;
    private int num;
    
    public Move(String color, int x, int y, int xDir, int yDir, int num){
        this.x = x;
        this.y = y;
        this.color = color;
        this.yDir = yDir;
        this.xDir = xDir;
        this.num = num;
    }
    
    public int getNum() {
        return num;
    }

    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getColor() {
        return color;
    }

    public int getxDir() {
        return xDir;
    }

    public int getyDir() {
        return yDir;
    }
}
