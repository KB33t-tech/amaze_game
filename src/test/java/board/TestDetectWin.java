package board;
import panel.Panel;

import org.junit.jupiter.api.Test;
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
		e = new Enemy(9, 8, 0, 0);
	}
	
	
	@Test
	void testStillInGame() {
		int score = 10;
		
		
		// Player is still playing when on the exit cell but has not collected all the regular rewards
		p.move(9, 7);
		state = cell.detectWin(score, 8);
		assertEquals(state, "GAME");
		
		// Player is still playing when not on the exit cell but has collected all the regular rewards
		p.move(-1, 0);
		state = cell.detectWin(score, 15);
		assertEquals(state, "GAME");
		
//		System.out.println(e.getEnemyX() + " " + e.getEnemyY());
//		System.out.println(state);

	}

	
	@Test
    void testLose() throws IOException {
		
		Panel panel = new Panel(null);
		// Player loses when score is negative and has not collected all the regular rewards
		int score = -10;
		state = cell.detectWin(score, 10);
		assertEquals(state, "LOSE");
		
		// Player loses when score is negative despite all the regular rewards have been collected
		state = cell.detectWin(score, 15);
		assertEquals(state, "LOSE");
		
		// Player and Enemy collide
		p.move(9, 7);
		state = panel.enemyCollision();
		assertEquals(state, "LOSE");
	}
	

	@Test
	void testWin() {
		int score = 10;
		
		// Player only wins when score is positive, has collected all the regular rewards, and on the exit cell
		p.move(9, 7);
		state = cell.detectWin(score, 15);
		assertEquals(state, "WIN");
	}
	

}
