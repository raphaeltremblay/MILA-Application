package assignment4Game;

import java.io.*;

public class Game {
 
 public static int play(InputStreamReader input){
  BufferedReader keyboard = new BufferedReader(input);
  Configuration c = new Configuration();
  int columnPlayed = 3; int player;
  
  // first move for player 1 (played by computer) : in the middle of the grid
  c.addDisk(firstMovePlayer1(), 1);
  int nbTurn = 1;
  
  while (nbTurn < 42){ // maximum of turns allowed by the size of the grid
   player = nbTurn %2 + 1;
   if (player == 2){
    columnPlayed = getNextMove(keyboard, c, 2);
   }
   if (player == 1){
    columnPlayed = movePlayer1(columnPlayed, c);
   }
   System.out.println(columnPlayed);
   c.addDisk(columnPlayed, player);
   if (c.isWinning(columnPlayed, player)){
    c.print();
    System.out.println("Congrats to player " + player + " !");
    return(player);
   }
   nbTurn++;
  }
  return -1;
 }
 
 public static int getNextMove(BufferedReader keyboard, Configuration c, int player){
  int thatCol = -1;
  while(thatCol < 0 || thatCol>6 || c.available[thatCol] > 5 && c.spaceLeft) {
	  //Getting the next move of the user
   try {
    c.print();
    System.out.println("Please enter in which column you wish to play");
    thatCol = Integer.parseInt(keyboard.readLine());
   } catch (Exception e) {
    System.out.println("Invalid input");
   }
  }
  return thatCol; 
 }
 
 public static int firstMovePlayer1 (){
  return 3;
 }
 
 public static int movePlayer1 (int columnPlayed2, Configuration c){
  int lastR = -1;
  //making the move of the user
  if(c.canWinNextRound(1) != -1) {
   lastR=c.canWinNextRound(1);
  } else if(c.canWinTwoTurns(1) != -1){
   lastR = c.canWinTwoTurns(1);
  } else{
   double lastvalue = 0.5;
   if(c.available[columnPlayed2]<6) {
    return columnPlayed2;
   }
   while(lastR<0 || lastR>6 || c.available[lastR]>5) {
    lastR = columnPlayed2;
    if(lastvalue>=0) {
     lastvalue = -(lastvalue+0.5);
    } else {
     lastvalue = -lastvalue+0.5;
    }
    lastR = lastR+((int) lastvalue);
   }
  }
  return lastR;
 }
 
}
