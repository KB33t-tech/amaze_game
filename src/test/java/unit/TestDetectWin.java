package unit;
import panel.Panel;


import org.junit.jupiter.api.Test;

import board.Cell;
import board.Enemy;
import board.Player;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;


public class TestDetectWin {
	
	private Cell cell;
	private Player p;
	private String state;
	private Enemy e;
	
	@BeforeEach()
	void init() throws IOException {
		cell = new Cell();
		p = new Player();
		
	}
	
	
	@Test
	void testStillInGameOne() {
		// Player is still playing when on the exit cell but has not collected all the regular rewards
		// detectWin(score, collected, rewardNum)
		p.move(9, 7);
		state = cell.detectWin(10, 8, 15);
		assertEquals("GAME", state);

//		System.out.println(e.getEnemyX() + " " + e.getEnemyY());
//		System.out.println(state);
	}
	
	@Test
	void testStillInGameTwo() {
		// Player is still playing when not on the exit cell but has collected all the regular rewards
		p.move(1, 0);
		state = cell.detectWin(10, 15, 15);
		assertEquals("GAME", state);			
	}

	
	@Test
    void testLoseOne() {
		// Player loses when score is negative and has not collected all the regular rewards
		// detectWin(score, collected, rewardNum)
		state = cell.detectWin(-10, 10, 15);
		assertEquals("LOSE", state);
	}
	
	
	@Test
	void testLoseTwo() {
		// Player loses when score is negative despite all the regular rewards have been collected
		state = cell.detectWin(-10, 15, 15);
		assertEquals("LOSE", state);
	}


	@Test
	void testWin() {
		// Player only wins when score is positive, has collected all the regular rewards, and on the exit cell
		p.move(9, 7);
		state = cell.detectWin(10, 15, 15);
		assertEquals("WIN", state);
	}
	

}
