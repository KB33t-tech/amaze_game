package integration;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import board.Player;
import board.Reward;


public class ITIntegration {
	
	private Player player;
	private Reward r;
	
	@BeforeEach
	void init() {
		player = new Player();
	}
	
	@Test
	void TestRewardCollision() {
		r = new Reward(5, 5);
		player.move(5, 4);
		boolean hit = r.detectCollision(player.getPlayerX(), player.getPlayerY(), r.getPosX(), r.getPosY());
		
		assertTrue(hit);
//		System.out.println(hit);
	}

}
