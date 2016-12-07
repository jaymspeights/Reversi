//This class is the engine for the reversi game. It initializes the board
//and the players, and then loops until the game is over, alternatingly calling
//each players move methods and checking to make sure that player has any valid moves remaining.
//if a player is unable to make a move, then the player with the most pieces on
//the board wins.
//I implemented diagonal checking, Player vs. Player, and Computer vs Computer in addition to
//the required criteria.

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.script.ScriptEngine.FILENAME;
public class Reversi {
    
    public static void main(String[] args) throws IOException{
    	System.out.println("how many games would you like to play?");
        Scanner scanner = new Scanner(System.in);
        int count1= 0;
        int number = scanner.nextInt();
        while(number>0){
        int size = 100;
        String temp;
        GameBoard board = new GameBoard(size);
        while(true){
            System.out.println("What size board would you like to play on?\n");
            temp = "100";
            //temp = scanner.next();
            try{
                size = Integer.parseInt(temp);
            }catch (Exception e){
            }  
            if (size<2)
                System.out.println("Please enter a number greater than 1");
            else break;
        }
        
        int num = -1;
        Player player1 = null, player2 = null;
        boolean set = false;
        while(!set){
            System.out.println("How many human players?\n");
            temp = "0";
            //temp = scanner.next();
            try{
                num = Integer.parseInt(temp);
            }catch (Exception e){
            }  
            switch(num){
                case 0:
                    player1 = new NPC("w", board);
                    player2 = new OldNPC("b", board);
                    set = true;
                    break;
                case 1:
                    player1 = new PC("w", board);
                    player2 = new NPC("b", board);
                    set = true;
                    break;
                case 2:
                    player1 = new PC("w", board);
                    player2 = new PC("b", board);
                    set = true;
                    break;
                default:
                    System.out.println("Please enter a number less than 2");
            }
        }
        
        
        
        boolean running = true;
        int turn = 0;
        while(running){
            switch(turn){
                case 0:
                    if (board.checkAllMoves(player1.getColor())){
                      player1.move();
                      turn = 1;
                    }
                    else{
                        running = false;
                    }
                    break;
                case 1:
                    if (board.checkAllMoves(player2.getColor())){
                      player2.move();
                      turn = 0;
                    }
                    else{
                        running = false;
                    }
                    break;
            }
        }
        System.out.println(board);
        int score1 = board.getScore()[0];
        int score2 = board.getScore()[1];
        if (score1 > score2) System.out.println("Player 2 Wins!");
        if (score1 < score2) {
        	System.out.println("Player 1 Wins!");
        	count1++;
        }
        if (score1 == score2) System.out.println("It's a draw!");
        number--;
        System.out.println(board.getCount());
        System.out.println(board.getOp()[board.getIndex()-1]);
        FileWriter fw;
        int sum = 0;
            try {
                fw = new FileWriter("data");
                BufferedWriter bw = new BufferedWriter(fw);
                for(int i = 0; i < board.getIndex(); i++) {
                   bw.write(board.getOp()[i] + "\n");
                   sum += board.getOp()[i];
                }
                bw.write("total checks = " + sum);
                bw.close();
            } catch (IOException ex) { 
                throw ex;
            }
        }
      
      System.out.println(count1);
 
    }
}
