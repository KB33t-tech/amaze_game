package board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Reward extends Item{
	
	private BufferedImage fireImg;
	public int fireX, fireY;
	
	public Reward(int xPos, int yPos) {
		
		fireX = xPos;
		fireY = yPos;
		
		try {
			fireImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Fire.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// draw regular rewards
	@Override
	public void drawMe(Graphics2D g2) {
		g2.drawImage(fireImg, fireX*60, fireY*60, 60, 60, null);
	}

	// change the score when Player collects a regular reward
	@Override
	public int changeScore(int score) {
		
		// increase the score by this amount:
		score += 10;	// temporary amount, can be changed to anything
		
		return score;
	}

}
