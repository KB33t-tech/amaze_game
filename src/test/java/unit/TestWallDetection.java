package unit;

import org.junit.jupiter.api.Test;



import board.Cell;
import board.Player;
import panel.WallDetection;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;



public class TestWallDetection {

	private Player p;
	private Cell c;
	private WallDetection wd = new WallDetection();
	private boolean stop;

	void printMap() {
		for(int i = 0; i < c.getMap().length; i++){
			for(int j = 0; j < c.getMap()[i].length; j++){
				System.out.print(c.getMap()[j][i] + " ");
			}
			System.out.println();
		}
	}

	@BeforeEach
	void init() {
		p = new Player();
		c = new Cell();
		printMap();
	}
	
	
	@Test
	void testRightWall() {

		p.move(1, 0);
		stop = wd.right_stop();
		assertFalse(stop);
		
		p.move(6, 0);
		stop = wd.right_stop();
		assertTrue(stop);
		
//		System.out.println(p.getPlayerX() + " " + p.getPlayerY());
	}
	
	
	@Test
	void testLeftWall() {
	
		// should not be able to move back to start cell after leaving
		p.move(1, 0);
		stop = wd.left_stop();
		assertTrue(stop);
		
		p.move(0, 3);
		stop = wd.left_stop();
		assertTrue(stop);
		
		p.move(3, 0);
		stop = wd.left_stop();
		assertTrue(stop);
		
		p.move(0, 1);
		stop = wd.left_stop();
		assertFalse(stop);
	}
	
	
	@Test
	void testUpWall() {
		
		// should not be able to move up when on start cell
		stop = wd.up_stop();
		assertTrue(stop);
		
		p.move(5, 0);
		stop = wd.up_stop();
		assertTrue(stop);
		
		p.move(0, 4);
		stop = wd.up_stop();
		assertTrue(stop);
		
		p.move(0, 2);
		stop = wd.up_stop();
		assertFalse(stop);
	}
	
	
	@Test
	void testDownWall() {
		// should not be able to move down when on start cell
		stop = wd.down_stop();
		assertTrue(stop);
		
		p.move(3, 0);
		stop = wd.down_stop();
		assertTrue(stop);
		
		p.move(1, 0);
		stop = wd.down_stop();
		assertFalse(stop);
		
		p.move(0, 1);
		stop = wd.down_stop();
		assertTrue(stop);
		
		p.move(0, 6);
		stop = wd.down_stop();
		assertTrue(stop);
	}
	
	
}
