package board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bonus extends Item{
	
	private BufferedImage bonusImg;
	public int bonusX, bonusY;
	
	public Bonus(int xPos, int yPos) {
		
		bonusX = xPos;
		bonusY = yPos;
		
		try {
			bonusImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Bonus.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void drawMe(Graphics2D g2) {
		g2.drawImage(bonusImg, bonusX*60, bonusY*60, 60, 60, null);
	}

	@Override
	protected int changeScore(int score) {
		score += 30;	//temporary amount
		
		return score;
	}

}
