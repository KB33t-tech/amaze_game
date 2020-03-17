package board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * It is a subclass of {@link Item}.
 * It holds the properties of each bonus reward.
 */
public class Bonus extends Item{
	
	// bonuses are displayed as a bufferedImage named "bonusImg"
	private BufferedImage bonusImg;
	
	// the x-coordinate and the y-coordinate of each bonus
	public int bonusX, bonusY;
	
	/**
	 * The constructor assigns the position to each punishment and load an image to {@link #bonusImg}.
	 * @param xPos	x-coordinate of bonuses
	 * @param yPos	y-coordinate of bonuses
	 */
	public Bonus(int xPos, int yPos) {
		
		bonusX = xPos;
		bonusY = yPos;
		
		try {
			bonusImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Bonus.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method displays bonuses on the screen at a specified location with a specified size.
	 */
	@Override
	protected void drawMe(Graphics2D g2) {
		g2.drawImage(bonusImg, bonusX*60, bonusY*60, 60, 60, null);
	}

	/**
	 * This method changes the score when Player collects a bonus reward by returning the new score.
	 */
	@Override
	protected int changeScore(int score) {
		
		// increase the score by this amount:
		score += 30;	
		
		return score;
	}

}
