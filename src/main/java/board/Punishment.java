package board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Punishment extends Item{
	
	private BufferedImage punishmentImg;
	private int punishmentX, punishmentY;
	
	public Punishment(int xPos, int yPos) {
		
		punishmentX = xPos;
		punishmentY = yPos;
		
		try {
			punishmentImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Punishment.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	// draw punishments
	@Override
	public void drawMe(Graphics2D g2) {
		g2.drawImage(punishmentImg, punishmentX, punishmentY, 60, 60, null);
	}

	
	// changes the score when Player moves into punishments
	@Override
	public int changeScore(int score) {
		
		// decrease the score by this amount:
		score -= 100;	// temporary amount, can be changed to anything
		
		return score;
	}

}
