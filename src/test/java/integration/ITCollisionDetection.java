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
		item.add(new Reward(5, 5));
		item.add(new Reward(1, 5));
		player.move(5, 4);
		
		hit = item.get(0).detectCollision(player.getPlayerX(), player.getPlayerY(), 
				item.get(0).getPosX(), item.get(0).getPosX());
		assertTrue(hit);
		
		hit = item.get(1).detectCollision(player.getPlayerX(), player.getPlayerY(), 
				item.get(1).getPosX(), item.get(1).getPosX());
		assertFalse(hit);
//		System.out.println(hit);
	}
	
	
	@Test
	void TestPunishmentCollision() {
		item.add(new Punishment(6, 6));
		item.add(new Punishment(8, 5));
		player.move(6, 5);
	
		hit = item.get(0).detectCollision(player.getPlayerX(), player.getPlayerY(), 
				item.get(0).getPosX(), item.get(0).getPosX());
		assertTrue(hit);
		
		hit = item.get(1).detectCollision(player.getPlayerX(), player.getPlayerY(), 
				item.get(1).getPosX(), item.get(1).getPosX());
		assertFalse(hit);
	}
	
	@Test
	void TestBonusCollision() {
		item.add(new Bonus(7, 7));
		item.add(new Bonus(8, 5));
		player.move(7, 6);
	
		hit = item.get(0).detectCollision(player.getPlayerX(), player.getPlayerY(), 
				item.get(0).getPosX(), item.get(0).getPosX());
		assertTrue(hit);
		
		hit = item.get(1).detectCollision(player.getPlayerX(), player.getPlayerY(), 
				item.get(1).getPosX(), item.get(1).getPosX());
		assertFalse(hit);
	}

}
