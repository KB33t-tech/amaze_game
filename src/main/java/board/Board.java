package board;

import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import panel.Panel;
import board.Cell;

/**
 * This class holds the properties of the board and draws everything inside {@link Cell}.
 */
public class Board {
	
	// the board background is displayed as a bufferedImage named "boardImg"
	private BufferedImage boardImg;
	
	// creates a Cell object
	private Cell cell;
	
	// text that is used to display the score
	private Font scoreText;
	
	/**
	 * The constructor assigns the position to each regular reward and load an image to {@link Board#boardImg}.
	 */
	public Board(){
		try {
			boardImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Board.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		cell = new Cell();
		
		// set the property of the text 
		scoreText = new Font("Courier New", Font.PLAIN | Font.ROMAN_BASELINE, 25);
	}
	
	/**
	 * This method draws everything that will be displayed during the game.
	 * This includes the board background, individual cells, regular rewards, bonus rewards, punishments, and score.
	 * It also calls {@link Cell#detectWin()} to detect whether Player has won or lost.
	 * @param g2  Java's Graphics2D drawing tool
	 */
	public void drawMe(Graphics2D g2) {
		
		// draw the board background image
		g2.drawImage(boardImg, 0, 0, Panel.WIN_W, Panel.WIN_H, null);
		
		// draw the cells
		cell.drawMap(g2);
		
		// detect if Player won or lost
		cell.detectWin();
		
		// display score
		displayScore(g2, 400, 650);
		displayTime(g2, 100, 650);
		
	}
	
	/**
	 * This method displays the current score {@link Cell#updateScore()} of Player on the screen.
	 * It also sets the text colour to be white.
	 * @param g2	Java's Graphics2D drawing tool
	 * @param x	    x-coordinate of the score
	 * @param y		y-coordinate of the score
	 */
	// display score
	public void displayScore(Graphics2D g2, int x, int y) {
		g2.setColor(Color.white);
		g2.setFont(scoreText);
		g2.drawString("Score: " + cell.updateScore(), x, y);
	}
	/**
	 * This method displays the current time {@link Cell#updateTime()} of Player on the screen.
	 * It also sets the text colour to be white.
	 * @param g2	Java's Graphics2D drawing tool
	 * @param x	    x-coordinate of the time
	 * @param y		y-coordinate of the time
	 */
	// display time
	
	public void displayTime(Graphics2D g2, int x, int y) {
		g2.setColor(Color.white);
		g2.setFont(scoreText);
		g2.drawString("Time: " + cell.updateTime() + " Seconds", x, y);
	}
	
	public void stopTime(Graphics2D g2, int x, int y) {
		g2.setColor(Color.white);
		g2.setFont(scoreText);
		g2.drawString("Time: " + cell.stopTime() + " Seconds", x, y);
	}
	
}
