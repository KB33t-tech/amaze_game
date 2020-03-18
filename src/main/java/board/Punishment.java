package board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * It is a subclass of {@link Item}.
 * It holds the properties of each punishment.
 */
public class Punishment extends Item{
	
	// punishments are displayed as a bufferedImage named "punishmentImg"
	private BufferedImage punishmentImg;
	
	// the x-coordinate and the y-coordinate of each punishment
	private int punishmentX, punishmentY;
	
	/**
	 * The constructor assigns the position to each punishment and load an image to {@link #punishmentImg}.
	 * @param xPos	x-coordinate of punishments
	 * @param yPos	y-coordinate of punishments
	 */
	public Punishment(int xPos, int yPos) {
		
		punishmentX = xPos;
		punishmentY = yPos;
		
		try {
			punishmentImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Punishment.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * This method displays punishments on the screen at a specified location with a specified size.
	 */
	@Override
	public void drawMe(Graphics2D g2) {
		g2.drawImage(punishmentImg, punishmentX*60, punishmentY*60, 60, 60, null);
	}

	
	/**
	 * This method changes the score when Player moves into a punishment by returning the new score.
	 */
	@Override
	public int changeScore(int score) {
		
		// decrease the score by this amount:
		score -= 50;
		
		return score;
	}
	public int getPunishX() {
		return punishmentX;
	}
	public int getPunishY() {
		return punishmentY;
	}

}
