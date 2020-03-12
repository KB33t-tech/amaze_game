package board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import panel.Panel;
import board.Cell;

public class Player{
	
	private BufferedImage playerImg;
	private static int playerX, playerY;
	private double xSpeed, ySpeed;

	private boolean moving;
	private double targetX, targetY; // to be replaced with coordinates of cells
	
	public Player() {
		try {
			playerImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Player.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		playerX = 0;
		playerY = 1;
		xSpeed = 7.0;
		ySpeed = 7.0;
		moving = false;
		targetX=-60;
		targetY=-60;
	}
	/*
	public void move(double xs, double ys) {
		if (playerX + xs <= 0 || playerX + xs >= Panel.WIN_W- 100) {
			playerX = playerX;		
		}else playerX += xs;
		
		if (playerY + ys <= 0 || playerY + ys >= Panel.WIN_H- 200) {
			playerY = playerY;		
		}else playerY += ys;
	}*/
	public void move(double xs, double ys) {
		playerX += xs;
		playerY += ys;
		/*
		if (xs!= 0) {
		if (playerX + xs >= 0 && playerX + xs <= Panel.WIN_W- 100) {
			xSpeed = xs/25;
			targetX = playerX + xs;
			moving = true;
		}
		}
		if (ys!= 0) {
		if (playerY + ys >= 0 && playerY + ys <= Panel.WIN_H- 200) {
			ySpeed = ys/25;
			targetY = playerY + ys;
			moving = true;
		}
		}
		*/
	}
	
	public void update() {
		if (moving) {
			playerX += xSpeed;
			playerY += ySpeed;
		}
		if (playerX == targetX || playerY == targetY) {
			
			moving = false;
			targetX=-60;
			targetY=-60;
			xSpeed = 0;
			ySpeed = 0;
		}
	}
	
	public void drawMe(Graphics2D g2) {
		g2.drawImage(playerImg, playerX*60, playerY*60, 60, 60, null);
	}
	
	
	public static int getPlayerX() {
		return playerX;
	}

	public static int getPlayerY() {
		return playerY;
	}
	
	public double getXSpeed(){
		return xSpeed;
	}
	
	public double getYSpeed(){
		return ySpeed;
	}
}
