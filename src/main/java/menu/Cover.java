package menu;

import java.awt.Color;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

//import others.ImageLoader;

//import others.ImageLoader;

public class Cover{
	
	private BufferedImage coverImg;
	private BufferedImage instructionImg;
	
	
	public Cover(){
//		img = ImageLoader.loadImage("assets/Cover no stairs.png");
//		img = ImageLoader.loadImage("assets/Start 3000.png");
//		instructionImg = ImageLoader.loadImage("assets/Instruction.png");
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
