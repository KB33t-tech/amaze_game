package others;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class draws the screen background for different states of the game.
 */
public class Screen{
	
	// the menu is displayed as a bufferedImage named "coverImg"
	private BufferedImage coverImg;
	
	// the instruction is displayed as a bufferedImage named "instructionImg"
	private BufferedImage instructionImg;
	
	// the losing screen is displayed as a bufferedImage named "loseImg"
	private BufferedImage loseImg;
	
	// the winning screen is displayed as a bufferedImage named "winImg"
	private BufferedImage winImg;
	
	
	/** 
	 * The constructor loads images to:
	 * {@link Screen#coverImg}, {@link Screen#instructionImg}, {@link #loseImg}, and {@link #winImg}.
	 */
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
	
	
	/**
	 * This method displays the menu image on the screen at the specified location with specified size.
	 * @param g2 	Java's Graphics2D drawing tool
	 */
	public void drawCover(Graphics2D g2){
		g2.drawImage(coverImg, 0, 0, 600, 700, null);
	}
	
	/**
	 * This method displays the instruction image on the screen at the specified location with specified size.
	 * @param g2 	Java's Graphics2D drawing tool
	 */
	public void drawInstruction(Graphics2D g2) {
		g2.drawImage(instructionImg, 0, 0, 600, 700, null);
	}
	
	/**
	 * This method displays the losing image on the screen at the specified location with specified size.
	 * @param g2 	Java's Graphics2D drawing tool
	 */
	public void drawLose(Graphics2D g2) {
		g2.drawImage(loseImg, 0, 0, 600, 700, null);
	}
	
	/**
	 * This method displays the winning image on the screen at the specified location with specified size.
	 * @param g2 	Java's Graphics2D drawing tool
	 */
	public void drawWin(Graphics2D g2) {
		g2.drawImage(winImg, 0, 0, 600, 700, null);
	}
	
}
