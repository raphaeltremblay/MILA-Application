
package assignment4Game;

public class Configuration {
	
	public int[][] board;
	public int[] available;
	boolean spaceLeft;
	
	public Configuration(){
		board = new int[7][6];
		available = new int[7];
		spaceLeft = true;
	}
	
	public void print(){
		System.out.println("| 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
		System.out.println("+---+---+---+---+---+---+---+");
		for (int i = 0; i < 6; i++){
			System.out.print("|");
			for (int j = 0; j < 7; j++){
				if (board[j][5-i] == 0){
					System.out.print("   |");
				}
				else{
					System.out.print(" "+ board[j][5-i]+" |");
				}
			}
			System.out.println();
		}
	}

	
	public void addDisk (int index, int player){
		int num =this.available[index];
		this.board[index][num]=player;
		this.available[index]+=1;
		num=0;
		for(int j=0 ; j<7 ; j++) {
			if(this.available[j]<6) {
				num++;
				break; 
			}
		}
		if(num==0) {
			this.spaceLeft=false;
		}

	}
	
	public boolean isWinning (int lastColumnPlayed, int player){
		int count=0;
		int thatRow=this.available[lastColumnPlayed]-1;
		for(int i=thatRow ; i>thatRow-4 ; i--) {
			if (i<0) {
				break;//Minimizing the number of iterations
			}
			if(this.board[lastColumnPlayed][i]==player) {
				count++;
			}
		}
		if(count==4) {
			return true;
		}
		count=0;
		int firstCol=0;
		int lastCol=6;
		if(lastColumnPlayed>=4) {
			firstCol=lastColumnPlayed-4+1; 
		}
		if(lastColumnPlayed<3) {
			lastCol=lastColumnPlayed+4-1;
		}
		for(int i=firstCol ; i<=lastCol ; i++) {
			for(int l=i ; l<i+4 ; l++) {
				if (l>=lastCol) {
					break;
				}
				 if(this.board[l][thatRow]==player) {
					 count++;
				 }
			}
			if(count==4) {
				return true;
			}
			count=0;
		}

		if(winDiagDn(lastColumnPlayed , player)) return true;
		if(winDiagU(lastColumnPlayed , player)) return true;
		
		return false;
	}
	//Testing if isWinning in diagonal (Up)
	private boolean winDiagUp(int lastColumnPlayed , int player , int thatRow) {
		int thatCol=lastColumnPlayed;
		int counter=0;
		for (int i=0 ; i<4 ; i++) {
			if((thatRow+i)<0||(thatCol+i)<0|| (thatRow+i)>5 || (thatCol+i)>6) {
				break;
			}
			if(this.board[thatCol+i][thatRow+i]==player) {
				counter++;
			}
		}
		if (counter==4) return true;
		
		return false;
	}
	//Testing if isWinning in diagonal (Up)
	private boolean winDiagDn(int thatCol , int player) {
		int thatRow=this.available[thatCol]-1;
		for (int i=0 ; i<4 ; i++) {
			if(winDiagDown(thatCol-i , player , thatRow+i)==true) {
				return true;
			}
		}
		return false;
	}
	private boolean winDiagU(int thatCol , int player) {
		int thatRow = this.available[thatCol]-1;
		for(int i=0 ; i<4 ; i++) {
			if(winDiagUp(thatCol-i , player , thatRow-i )==true) return true;
		}
		return false;
	}
	
	private boolean winDiagDown(int lastColumnPlayed , int player , int thatRow) {
		int thatCol =lastColumnPlayed;
		int count=0;
		for (int i=0 ; i<4 ; i++) {
			if((thatRow-i)<0||(thatCol+i)>6||(thatCol+i<0)||(thatRow-i>5)) {
				
				break;
			}
			if(this.board[thatCol+i][thatRow-i]==player) {
				count++;
				}
		}
		if (count==4) return true;
		
		return false;
	}
		
	//Checking if it can win next round
	public int canWinNextRound (int player){
		Configuration conf = new Configuration();
		conf=this;
		for(int i=0; i<7; i++) {
			if(conf.available[i]>5){
				continue;
			}
			conf.addDisk(i, player);
			if(conf.isWinning(i, player)) {
				conf.removeDisk(i);
				return i;
			}
			conf.removeDisk(i);
		}
		return -1;
	}
	
	private void removeDisk(int thatCol) {
		this.available[thatCol]--;
		this.board[thatCol][this.available[thatCol]] = 0;
	}
	

	//Checking if there is a possibility to win in 2 turns
	public int canWinTwoTurns (int player){
		int player2 = 1;
		if(player == 1) {
			player2 = 2;
		}
		if(this.canWinNextRound(player2) != -1) {
			return this.canWinNextRound(player2);
		}  
		for(int i=0; i<7; i++) {
			if(this.available[i]<6) {
				this.addDisk(i, player);
				if(this.canWinNextRound(player2) != -1) {
					this.removeDisk(i);
					continue;
				}
				int cond = this.canWinNextRound(player);
				if(cond != -1) {
					this.addDisk(cond, player2);
					if(this.canWinNextRound(player) != -1) {
						this.removeDisk(cond);
						this.removeDisk(i);
						return i;
					}
					this.removeDisk(cond);
				}
				this.removeDisk(i);
			}
		}
		return -1;
	}
	
	
}