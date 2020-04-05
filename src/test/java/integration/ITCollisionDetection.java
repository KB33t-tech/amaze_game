package integration;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import board.Bonus;
import board.Item;
import board.Player;
import board.Punishment;
import board.Reward;


public class ITCollisionDetection {
	
	private Player player;
	private ArrayList <Item> item;
	private boolean hit;
	
	@BeforeEach
	void init() {
		player = new Player();
		item = new ArrayList<Item>();
	}
	
	
	@Test
	void TestRewardCollision() {
		item.add(new Reward(5, 6));
		item.add(new Reward(1, 5));
		item.add(new Reward(5, 1));
		player.move(5, 5);
		
		hit = item.get(0).detectCollision(player.getPlayerX(), player.getPlayerY(), 
				item.get(0).getPosX(), item.get(0).getPosY());
		assertTrue(hit);
		
		hit = item.get(1).detectCollision(player.getPlayerX(), player.getPlayerY(), 
				item.get(1).getPosX(), item.get(1).getPosY());
		assertFalse(hit);
		
		hit = item.get(2).detectCollision(player.getPlayerX(), player.getPlayerY(), 
				item.get(2).getPosX(), item.get(2).getPosY());
		assertFalse(hit);
//		System.out.println(hit);
	}
	
	
	@Test
	void TestPunishmentCollision() {
		item.add(new Punishment(6, 8));
		item.add(new Punishment(8, 5));
		player.move(6, 7);
	
		hit = item.get(0).detectCollision(player.getPlayerX(), player.getPlayerY(), 
				item.get(0).getPosX(), item.get(0).getPosY());
		assertTrue(hit);
		
		hit = item.get(1).detectCollision(player.getPlayerX(), player.getPlayerY(), 
				item.get(1).getPosX(), item.get(1).getPosY());
		assertFalse(hit);
	}
	
	@Test
	void TestBonusCollision() {
		item.add(new Bonus(7, 6));
		item.add(new Bonus(8, 5));
		player.move(7, 5);
	
		hit = item.get(0).detectCollision(player.getPlayerX(), player.getPlayerY(), 
				item.get(0).getPosX(), item.get(0).getPosY());
		assertTrue(hit);
		
		hit = item.get(1).detectCollision(player.getPlayerX(), player.getPlayerY(), 
				item.get(1).getPosX(), item.get(1).getPosY());
		assertFalse(hit);
	}

}
