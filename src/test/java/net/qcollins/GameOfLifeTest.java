package net.qcollins;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class GameOfLifeTest {
	
	@Before
	public void setup() {
		
	}
	
	@Test
	public void can_get_tile_size() {
		Assert.assertEquals(20, GameOfLife.getTileSize());
	}
}
