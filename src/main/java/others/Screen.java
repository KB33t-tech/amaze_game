package others;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Screen{
	
	private BufferedImage coverImg;
	private BufferedImage instructionImg;
	private BufferedImage loseImg;
	private BufferedImage winImg;
	
	
	public Screen(){

		try {
			coverImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Menu.png"));
			instructionImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Instruction.png"));
			loseImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Lose.png"));
			winImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Win.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void drawCover(Graphics2D g2){
		g2.drawImage(coverImg, 0, 0, 600, 700, null);
	}
	
	
	public void drawInstruction(Graphics2D g2) {
		g2.drawImage(instructionImg, 0, 0, 600, 700, null);
	}
	
	public void drawLose(Graphics2D g2) {
		g2.drawImage(loseImg, 0, 0, 600, 700, null);
	}
	
	public void drawWin(Graphics2D g2) {
		g2.drawImage(winImg, 0, 0, 600, 700, null);
	}
	
}
