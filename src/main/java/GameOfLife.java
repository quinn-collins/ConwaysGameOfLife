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
        
        printOutCurrentGameGrid(gameGrid, vertical, horizontal);
        
        moveToNextGeneration(gameGrid, vertical, horizontal);
        
        
        printOutCurrentGameGrid(gameGrid, vertical, horizontal);
		
	}
	
	// print game grid
	public static void printOutCurrentGameGrid(boolean[][] gameGrid, int vertical, int horizontal) {
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
	
	//get a count for number of live cells around each cell
	public static int getCountOfLifeCellsAroundEachCell(boolean[][] gameGrid, int i, int j) {
		int count = 0;
		
		if(i>0 && j>0 && gameGrid[i-1][j-1]) {
			count++;
		}
		if(i>0 && gameGrid[i-1][j]) {
			count++;
		}
		if(j<gameGrid[0].length-1 && i>0 && gameGrid[i-1][j+1]) {
			count++;
		}
		if(j>0 && gameGrid[i][j-1]) {
			count++;
		}
		if(j<gameGrid[0].length-1 && gameGrid[i][j+1]) {
			count++;
		}
		if(i<gameGrid.length-1 && j>0 && gameGrid[i+1][j-1]) {
			count++;
		}
		if(i<gameGrid.length-1 && gameGrid[i+1][j]) {
			count++;
		}
		if(i<gameGrid.length-1 && j<gameGrid.length-1 && gameGrid[i+1][j+1]) {
			count++;
		}
		return count;
	}
	
	//let logic decide which cells die and which live to move to next generation
	public static void moveToNextGeneration(boolean[][] gameGrid, int vertical, int horizontal) {
		for(int i = 0; i < vertical; i++) {
        	for(int j = 0; j < horizontal; j++) {
        		System.out.println(i + "" + j);
        		int count = getCountOfLifeCellsAroundEachCell(gameGrid, i, j);
        		if(gameGrid[i][j] && count <= 2) {
        			gameGrid[i][j] = false;
        		}
        	}
        }
	}
	
}
