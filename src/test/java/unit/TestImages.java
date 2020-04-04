package unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

/**
 * This class is for testing all the images used in the game.
 *
 */
public class TestImages {
	
	@Test
	void testPlayerImg() throws IOException {
		BufferedImage playerImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Player.png"));
		assertNotNull(playerImg);	
	}
	
	@Test
	void testEnemyImg() throws IOException {
		BufferedImage enemyImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Enemy.png"));
		assertNotNull(enemyImg);
	}
	
	@Test
	void testRewardImg() throws IOException {
		BufferedImage rewardImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Fire.png"));
		assertNotNull(rewardImg);
	}
	
	@Test
	void testBonusImg() throws IOException {
		BufferedImage bonusImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Bonus.png"));
		assertNotNull(bonusImg);
	}
	
	@Test
	void testPunishmentImg() throws IOException {
		BufferedImage punishmentImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Punishment.png"));
		assertNotNull(punishmentImg);
	}
	
	@Test
	void testBoardImg() throws IOException {
		BufferedImage boardImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Board.png"));
		assertNotNull(boardImg);
	}
	
	@Test
	void testMenuImg() throws IOException {
		BufferedImage menuImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Menu.png"));
		assertNotNull(menuImg);
	}
	
	@Test
	void testInstructionImg() throws IOException {
		BufferedImage instructionImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Instruction.png"));
		assertNotNull(instructionImg);
	}
	
	@Test
	void testWinImg() throws IOException {
		BufferedImage winImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Win.png"));
		assertNotNull(winImg);
	}
	
	@Test
	void testLoseImg() throws IOException {
		BufferedImage loseImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Lose.png"));
		assertNotNull(loseImg);
	}
		
}
