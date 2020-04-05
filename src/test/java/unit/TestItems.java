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
		
		itemCount = c.rewardGenerator(5, itemCount);
		assertEquals(5, itemCount);
		
		itemCount = 0;
		itemCount = c.rewardGenerator(10, itemCount);
		assertEquals(10, itemCount);
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
		itemCount = c.punishmentGenerator(2, itemCount);
		assertEquals(2, itemCount);
		
		itemCount = 0;
		itemCount = c.punishmentGenerator(7, itemCount);
		assertEquals(7, itemCount);
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
		itemCount = c.rewardGenerator(5, itemCount);
		p.move(9, 7);
		System.out.println(itemCount);
		state = c.detectWin(10, 5, itemCount);
		assertEquals("WIN", state);
	}
	
	@Test
	void testRewardAndDetectWinTwo() {
		itemCount = c.rewardGenerator(10, itemCount);
		p.move(9, 7);
		state = c.detectWin(10, 8, itemCount);
		assertEquals("GAME", state);
	}
	
	@Test
	void testRewardAndDetectWinThree() {
		itemCount = c.rewardGenerator(12, itemCount);
		p.move(8, 7);
		state = c.detectWin(-10, 12, itemCount);
		assertEquals("LOSE", state);
	}
}
