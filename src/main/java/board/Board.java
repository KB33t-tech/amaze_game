package board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import panel.Panel;

public class Board {
	
	private BufferedImage boardImg;
	
	public Board(){
		try {
			boardImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Board.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void drawMe(Graphics2D g2) {
		g2.drawImage(boardImg, 0, 0, Panel.WIN_W, Panel.WIN_H, null);	
	}
}
