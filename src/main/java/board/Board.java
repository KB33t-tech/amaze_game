package board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import panel.Panel;
import board.Cell;

public class Board {
	
	private BufferedImage boardImg;
	private Cell cell;
	
	public Board(){
		try {
			boardImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Board.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		cell = new Cell();
	}
	public void drawMe(Graphics2D g2) {
		g2.drawImage(boardImg, 0, 0, Panel.WIN_W, Panel.WIN_H, null);
		
		// draw the walls
		cell.drawMap(g2);
		
		// detect if Player won
		cell.detectWin();
	}
}
