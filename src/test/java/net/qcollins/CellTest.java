package net.qcollins;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CellTest {
	
	Cell cell;
	
	@Before
	public void setup() {
		cell = new Cell(1,1);
	}
	
	@Test
	public void can_open_cell() {
		cell.open();
		Assert.assertEquals(true, cell.isOpen());
	}
	
	@Test
	public void can_get_x_coordinate() {
		Assert.assertEquals(1, cell.getX());
	}
	
	@Test
	public void can_set_x_coordinate() {
		cell.setX(2);
		Assert.assertEquals(2, cell.getX());
	}
	
	@Test
	public void can_get_y_coordinate() {
		Assert.assertEquals(1, cell.getX());
	}
	
	@Test
	public void can_set_y_coordinate() {
		cell.setY(2);
		Assert.assertEquals(2, cell.getY());
	}
	
	@Test
	public void can_close_cell() {
		cell.open();
		cell.close();
		Assert.assertEquals(false, cell.isOpen());
	}
}
