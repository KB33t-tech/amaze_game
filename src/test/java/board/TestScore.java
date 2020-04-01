package board;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TestScore {

	@Test
    void testRewardScore() {
    	
		// Test if score accumulates correctly for every reward collected
		Reward reward = new Reward(60, 60);
		int result = reward.updateScore(10);
		assertEquals(20, result);
	
    }
	
	@Test
	void testBonusScore() {
	
		// Test if score accumulates correctly for every bonus collected
		Bonus bonus = new Bonus(60, 60);
		int result = bonus.updateScore(10);
		assertEquals(40, result);
	}
	
	
	@Test
	void testPunishmentScore() {
	
		// Test if score decreases correctly for every punishment collected
		Punishment bonus = new Punishment(60, 60);
		int result = bonus.updateScore(10);
		assertEquals(-40, result);
	}
	
}
