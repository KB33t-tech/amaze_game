package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import board.Cell;
import board.Enemy;
import board.Player;

public class ITEnemyTracker {
	
	private int weightedMap[][]= {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	};
	
	
	private Player player = new Player();
//	private Enemy enemy = new Enemy(540, 470, 0, 0);
	private Enemy enemy = new Enemy(9, 8, 0, 0);
	private Cell cell = new Cell();
	
	@Test
	void TestTrack() {
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				weightedMap[i][j] = 10000;
			}
		}	

		player.beacon(weightedMap, cell.getMap(), player.getPlayerX(), player.getPlayerY(), 0);
		enemy.track(weightedMap);
		enemy.update();
		
		int enemyX = enemy.getEnemyX();
		assertEquals(8, enemyX);
//		System.out.println("Enemy X " + enemyX);
	}
	

}
