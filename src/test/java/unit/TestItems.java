package unit;

import org.junit.jupiter.api.Test;

import board.Cell;
import board.Player;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class TestItems {

	private Player p;
	private Cell c;
	private int itemCount;
	private String state;
	
	@BeforeEach
	void init() {
		p = new Player();
		c = new Cell();
		itemCount = 0;
		state = "";
	}
	
	
	@Test
	void testRewardGenerator() {
		
		itemCount = c.rewardGenerator(itemCount, 5);
		assertEquals(itemCount, 5);
		
		itemCount = 0;
		itemCount = c.rewardGenerator(itemCount, 10);
		assertEquals(itemCount, 10);
		/*
		for(int i = 0; i < c.getItemMap().length; i++){
			for(int j = 0; j < c.getItemMap()[i].length; j++){
				System.out.print(c.getItemMap()[j][i] + " ");
			}
			System.out.println();
		}
		*/
	}
	
	
	@Test
    void testPunishmentGenerator() {
		itemCount = c.punishmentGenerator(itemCount, 2);
		assertEquals(itemCount, 2);
		
		itemCount = 0;
		itemCount = c.punishmentGenerator(itemCount, 7);
		assertEquals(itemCount, 7);
		/*
		for(int i = 0; i < c.getItemMap().length; i++){
			for(int j = 0; j < c.getItemMap()[i].length; j++){
				System.out.print(c.getItemMap()[j][i] + " ");
			}
			System.out.println();
		}
		*/
	}
	
	
	@Test
	void testRewardAndDetectWinOne() {
		itemCount = c.rewardGenerator(itemCount, 5);
		p.move(9, 7);
		System.out.println(itemCount);
		state = c.detectWin(10, 5, itemCount);
		assertEquals(state, "WIN");
	}
	
	@Test
	void testRewardAndDetectWinTwo() {
		itemCount = c.rewardGenerator(itemCount, 10);
		p.move(9, 7);
		state = c.detectWin(10, 8, itemCount);
		assertEquals(state, "GAME");
	}
	
	@Test
	void testRewardAndDetectWinThree() {
		itemCount = c.rewardGenerator(itemCount, 12);
		p.move(8, 7);
		state = c.detectWin(-10, 12, itemCount);
		assertEquals(state, "LOSE");
	}
}
