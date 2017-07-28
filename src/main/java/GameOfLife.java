/*
 * Author:   Quinn Collins
 * Date:     Friday, July 28th, 2017
 * Purpose:  Fuse - Conway's Game of Life kata
 *  
 */
public class GameOfLife {

	public static void main(String[] args) {
		
		// decide on dimensions of the game
        int vertical = 6;
        int horizontal = 8;
        
        //create the game grid
        boolean[][] gameGrid = new boolean[vertical][horizontal];
        gameGrid[0][7] = true;
        gameGrid[1][0] = true;
        gameGrid[1][1] = true;
        gameGrid[1][2] = true;
        gameGrid[1][7] = true;
        gameGrid[2][7] = true;
        gameGrid[4][3] = true;
        gameGrid[4][4] = true;
        gameGrid[5][3] = true;
        gameGrid[5][4] = true;
        
        // print game grid
        for (int i = 0; i < vertical; i++) {
            for (int j = 0; j < horizontal; j++) {
            	if(gameGrid[i][j]) {
            		System.out.print("*");
            	}
            	else {
            		System.out.print("-");
            	}
            }
            System.out.println();
        }
		
	}
}
