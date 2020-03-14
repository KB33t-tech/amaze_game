package board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Bonus extends Item{
	
	private BufferedImage bonusImg;
	public int bonusX, bonusY;
	
	public Bonus(int xPos, int yPos) {
		
		bonusX = xPos;
		bonusY = yPos;
		
	}

	@Override
	protected void drawMe(Graphics2D g2) {
		g2.drawImage(bonusImg, bonusX*60, bonusY*60, 60, 60, null);
	}

	@Override
	protected int changeScore(int score) {
		
		return score;
	}

}
