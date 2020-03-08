package menu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Cover{
	
	private BufferedImage coverImg;
	private BufferedImage instructionImg;
	
	
	public Cover(){

		try {
			coverImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Menu.png"));
			instructionImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Instruction.png"));
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

	
}
