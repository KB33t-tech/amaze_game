package board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * It is a subclass of {@link Item}.
 * It holds the properties of each regular reward.
 */
public class Reward extends Item{
	
	// regular rewards are displayed as a bufferedImage named "fireImg"
	private BufferedImage fireImg;
	
	/**
	 * The constructor assigns the position to each regular reward and load an image to {@link #fireImg}.
	 * @param xPos  x-coordinate of regular rewards
	 * @param yPos  y-coordinate of regular rewards
	 */
	public Reward(int xPos, int yPos) {
		
		PosX = xPos;
		PosY = yPos;
		
		try {
			fireImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Fire.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * This method displays regular rewards on the screen at a specified location with a specified size.
	 */
	@Override
	public void drawMe(Graphics2D g2) {
		g2.drawImage(fireImg, PosX*60, PosY*60, 60, 60, null);
	}

	/**
	 * This method changes the score when Player collects a regular reward by returning the new score.
	 */
	@Override
	public int updateScore(int score) {
		
		// increase the score by this amount:
		score += 10;
		
		return score;
	}

}
