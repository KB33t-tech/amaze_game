package board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import panel.Panel;


public class Player{
	
	private BufferedImage playerImg;
	private double playerX, playerY;
	private double xSpeed, ySpeed;
	public double playerSpeed = 7.0;
	private boolean moving;
	private double targetX, targetY; // to be replaced with coordinates of cells
	
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
		moving = false;
		targetX=-100;
		targetY=-100;
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
	}
	public void update() {
		if (moving) {
			playerX += xSpeed;
			playerY += ySpeed;
		}
		if (playerX == targetX || playerY == targetY) {
			
			moving = false;
			targetX=-100;
			targetY=-100;
			xSpeed = 0;
			ySpeed = 0;
		}
	}
	
	public void drawMe(Graphics2D g2) {
		g2.drawImage(playerImg,(int) playerX, (int)playerY, 100, 100, null);
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
