package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import board.Cell;
import board.Enemy;
import board.Item;
import board.Player;
import panel.Panel;

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
	
	
	private Player player;
	private Enemy enemy;
	private int enemyX, enemyY;
	private Cell cell;
	
	@BeforeEach
	void init() {
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				weightedMap[i][j] = 10000;
			}
		}
		
		player = new Player();
		cell = new Cell();
	}
	
	@Test
	void TestTrack() {
		
		enemy = new Enemy(9, 8, 0, 0);
		player.move(8, 4);
		
		// test enemy moving left
		player.beacon(weightedMap, cell.getMap(), player.getPlayerX(), player.getPlayerY(), 0);
		enemy.track(weightedMap);
		enemy.update();
		
		enemyX = enemy.getEnemyX();
		enemyY = enemy.getEnemyY();
		assertEquals(8, enemyX);
		assertEquals(8, enemyY);
		
		
		player.beacon(weightedMap, cell.getMap(), player.getPlayerX(), player.getPlayerY(), 0);
		enemy.track(weightedMap);
		enemy.update();
		
		enemyX = enemy.getEnemyX();
		enemyY = enemy.getEnemyY();
		assertEquals(7, enemyX);
		assertEquals(8, enemyY);
		
		// test enemy moving up
		player.beacon(weightedMap, cell.getMap(), player.getPlayerX(), player.getPlayerY(), 0);
		enemy.track(weightedMap);
		enemy.update();
		
		enemyX = enemy.getEnemyX();
		enemyY = enemy.getEnemyY();
		assertEquals(7, enemyX);
		assertEquals(7, enemyY);
		
		// test enemy moving up
		player.beacon(weightedMap, cell.getMap(), player.getPlayerX(), player.getPlayerY(), 0);
		enemy.track(weightedMap);
		enemy.update();
				
		enemyX = enemy.getEnemyX();
		enemyY = enemy.getEnemyY();
		System.out.println("XXXX " + enemyX + "YYYY " + enemyY);
		System.out.println("Player XXXX " + player.getPlayerX() + "Player YYYY " + player.getPlayerY());
		assertEquals(7, enemyX);
		assertEquals(6, enemyY);
		
		// test enemy moving right
		player.beacon(weightedMap, cell.getMap(), player.getPlayerX(), player.getPlayerY(), 0);
		enemy.track(weightedMap);
		enemy.update();
				
		enemyX = enemy.getEnemyX();
		enemyY = enemy.getEnemyY();
		
//		System.out.println("XXXX " + enemyX + "YYYY " + enemyY);
//		System.out.println("Player XXXX " + player.getPlayerX() + "Player YYYY " + player.getPlayerY());
		assertEquals(8, enemyX);
		assertEquals(6, enemyY);
		
		// test enemy moving up
		player.beacon(weightedMap, cell.getMap(), player.getPlayerX(), player.getPlayerY(), 0);
		enemy.track(weightedMap);
		enemy.update();
				
		enemyX = enemy.getEnemyX();
		enemyY = enemy.getEnemyY();
		assertEquals(8, enemyX);
		assertEquals(5, enemyY);
	}
}