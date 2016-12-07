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
    
    static int number = 1000; //number of games to play
    static int size = 8; //size of board
    static int num = 0; //0 = CvC, 1 = PvC, 2 = PvP
    
    static int n = number;
    
    public static void main(String[] args) throws IOException{
        int count1 = 0;
        while(number>0){          
            GameBoard board = new GameBoard(size);
            Player player1 = null, player2 = null;
            switch(num){
                case 0:
                    player1 = new NPC("w", board);
                    player2 = new OldNPC("b", board);
                    break;
                case 1:
                    player1 = new PC("w", board);
                    player2 = new OldNPC("b", board);
                    break;
                case 2:
                    player1 = new PC("w", board);
                    player2 = new PC("b", board);
                    break;
                default:
                    System.out.println("Please enter a number less than 2");
            }

            boolean running = true;
            int turn = (int)(Math.random()*2);
            while(running){
                switch(turn){
                    case 0:
                        if (board.checkAllMoves(player1.getColor())){
                            if (player1 instanceof PC)
                                System.out.println(board);
                            player1.move();
                            turn = 1;
                        }
                        else{
                            running = false;
                        }
                        break;
                    case 1:
                        if (board.checkAllMoves(player2.getColor())){
                            if (player1 instanceof PC)
                                System.out.println(board);
                            player2.move();
                            turn = 0;
                        }
                        else{
                            running = false;
                        }
                        break;
                }
            }
            int score1 = board.getScore()[0];
            int score2 = board.getScore()[1];
            if (score1 > score2) {
                    count1++;
            }
            if (player1 instanceof PC){
                System.out.println(board);
            }
    //        System.out.println(board.getCount());
    //        System.out.println(board.getOp()[board.getIndex()-1]);
    //        FileWriter fw;
    //        int sum = 0;
    //            try {
    //                fw = new FileWriter("data");
    //                BufferedWriter bw = new BufferedWriter(fw);
    //                for(int i = 0; i < board.getIndex(); i++) {
    //                   bw.write(board.getOp()[i] + "\n");
    //                   sum += board.getOp()[i];
    //                }
    //                bw.write("total checks = " + sum);
    //                bw.close();
    //            } catch (IOException ex) { 
    //                throw ex;
    //            }
            number--;
        }
    System.out.println(count1 + " / " + n);
    System.out.println("Player 2 win = " + (double)count1/(double)n + "%");
    } 
}
