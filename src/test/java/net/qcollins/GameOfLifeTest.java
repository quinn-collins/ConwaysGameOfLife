package net.qcollins;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class GameOfLifeTest {
	Cell[][] grid;
	Cell[][] nextFrame;
	GameOfLife life;
	Cell cell;
	
	
	
	@Before
	public void setup() {
		
		life = new GameOfLife();
		grid = new Cell[40][30];
		nextFrame = new Cell[40][30];
		
		// Create two 40x40 grids
		for (int y = 0; y < 30; y++) {
			for (int x = 0; x < 40; x++) {
				grid[x][y] = new Cell(x, y);
				nextFrame[x][y] = new Cell(x, y);
				
			}
		}
		// Make top left cell "alive" or open
		
		
		
	}
	
	@Test // WORKS
	public void can_get_tile_size() {
		Assert.assertEquals(20, GameOfLife.getTileSize());
	}
	
	@Test // NullPointerException @ GameOfLife.java:122
	public void can_get_a_count_of_live_cells() {
		grid[0][0].open();
		grid[0][1].open();
		grid[1][0].open();
		grid[1][1].open();
		Assert.assertEquals(3, life.getCountOfLiveCells(grid[0][1]));
	}
	
	@Test // NullPointerException @ GameOfLife.java:118 from GameOfLife.java:137 from GameOfLife.java:158
	public void can_update_a_new_grid_based_on_old_grid() {
		nextFrame[1][1].open();
		life.next();
		Assert.assertTrue(grid[1][1].isOpen());
	}
	
	@Test // NullPointerException @ GameOfLife.java:101 from GameOfLife.java:180
	public void can_clear_the_grid() { 
		grid[1][1].open();
		life.clear();
		Assert.assertFalse(grid[0][0].isOpen());
	}
	
	@Test // NullPointerException @ GameOfLife.java:101 from GameOfLife.java:198
	public void can_randomly_generate_live_squares_on_grid() {
		int liveSquares = 0;
		life.random();
		for(int i = 0; i < 39; i++) {
			for(int j = 0; j < 39; j++) {
				if(grid[j][i].isOpen()) {
					liveSquares++;
				}
			}
		}
		Assert.assertTrue(liveSquares > 5);
	}
	
	@Test // NullPointerException @ GameOfLife.java:101 from GameOfLife.java:296
	public void can_seed_grid_with_live_squares() {
		life.seed();
		Assert.assertTrue(grid[15][17].isOpen());
	}

}
