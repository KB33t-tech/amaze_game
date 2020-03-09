package board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import panel.Panel;


public class Player{
	
	private BufferedImage playerImg;
	public int playerX, playerY;
	public double xSpeed, ySpeed;
	public double playerSpeed = 7.0;
	
	public Player(int xPos, int yPos, double xs, double ys) {
		try {
			playerImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Player.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		playerX = xPos;
		playerY = yPos;
		xSpeed = xs;
		ySpeed = ys;
	}
	
	public void move(double xs, double ys) {
		if (playerX + xs <= 0 || playerX + xs >= Panel.WIN_W- 100) {
			playerX = playerX;		
		}else playerX += xs;
		
	 	playerY += ys;
	}
	
	
	public void drawMe(Graphics2D g2) {
		g2.drawImage(playerImg, playerX, playerY, 100, 100, null);
	}
	
	
	public double getPlayerX() {
		return playerX;
	}

	public double getPlayerY() {
		return playerY;
	}
	
	public double getXSpeed(){
		return xSpeed;
	}
	
	public double getYSpeed(){
		return ySpeed;
	}
}
