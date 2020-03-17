package board;

import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import panel.Panel;
import board.Cell;

public class Board {
	
	private BufferedImage boardImg;
	private Cell cell;
	private Font scoreText;
	
	public Board(){
		try {
			boardImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Board.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		cell = new Cell();
		scoreText = new Font("Courier New", Font.PLAIN | Font.ROMAN_BASELINE, 25);
	}
	
	// draw the board
	public void drawMe(Graphics2D g2) {
		g2.drawImage(boardImg, 0, 0, Panel.WIN_W, Panel.WIN_H, null);
		
		// draw the walls
		cell.drawMap(g2);
		
		// detect if Player won or lost
		cell.detectWin();
		
		// display score
		displayScore(g2, 400, 650);
	}
	
	// display score
	public void displayScore(Graphics2D g2, int x, int y) {
		g2.setColor(Color.white);
		g2.setFont(scoreText);
		g2.drawString("Score: " + cell.updateScore(), x, y);
	}
}
