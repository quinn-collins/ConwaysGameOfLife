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
		grid = new Cell[life.getX_TILES()][life.getY_TILES()];
		nextFrame = new Cell[life.getX_TILES()][life.getY_TILES()];
		
		for (int y = 0; y < 30; y++) {
			for (int x = 0; x < 40; x++) {
				grid[x][y] = new Cell(x, y);
				nextFrame[x][y] = new Cell(x, y);
			}
		}
	}
	
	@Test 
	public void can_get_tile_size() {
		Assert.assertEquals(20, GameOfLife.getTileSize());
	}
	
	@Test
	public void can_get_x_tiles() {
		Assert.assertEquals(40, life.getX_TILES());
	}
	
	@Test
	public void can_get_y_tiles() {
		Assert.assertEquals(30, life.getY_TILES());
	}
	
	@Test 
	public void can_get_a_count_of_live_cells() {
		grid[0][0].open();
		grid[0][1].open();
		grid[1][0].open();
		grid[1][1].open();
		life.setGrid(grid);
		Assert.assertEquals(3, life.getCountOfLiveCells(grid[0][1]));
		
	}
	
	@Test 
	public void can_update_a_new_grid_based_on_old_grid() {
		grid[0][0].open();
		grid[4][4].open();
		grid[4][5].open();
		grid[5][4].open();
		grid[0][4].open();
		grid[0][5].open();
		grid[1][4].open();
		grid[1][5].open();
		grid[2][4].open();
		life.setNextFrame(nextFrame);
		life.setGrid(grid);
		life.next();
		Assert.assertFalse(grid[1][1].isOpen());
	}
	
	@Test 
	public void can_clear_the_grid() { 
		grid[1][1].open();
		life.setGrid(grid);
		life.clear();
		Assert.assertFalse(grid[0][0].isOpen());
	}
	
	@Test 
	public void can_seed_grid_with_live_squares() {
		life.setGrid(grid);
		life.setNextFrame(nextFrame);
		life.seed();
		Assert.assertTrue(grid[15][17].isOpen());
		life.seed();
		Assert.assertTrue(grid[35][5].isOpen());
	}

}
