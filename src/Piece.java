//The piece class is used to populate the board with each players pieces.
//the pieces have a color, and a location on the board. the flip() method 
//is used when a new piece is played in order to flip the color of the current
//captured pieces
public class Piece {
    private String color;
    private int x;
    private int y;
    
    public Piece(){
        color = "-";
        x = 0;
        y = 0;
    }
    public Piece(String color, int x, int y){
        this.color = color;
        this.x = x;
        this.y = y;
    }
    
    public void flip(){
        if (color.equals("b"))
            color = "w";
        else 
            color = "b";
    }
    
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
