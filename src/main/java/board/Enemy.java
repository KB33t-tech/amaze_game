package board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import panel.Panel;

public class Enemy {
	
	private BufferedImage enemyImg;
	public int enemyX, enemyY;
	public double xSpeed, ySpeed;
	public double enemySpeed = 7.0;
	private double targetX, targetY;
	private boolean moving = false;
	
	public Enemy(int xPos, int yPos, double xs, double ys) {
		try {
			enemyImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Enemy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		enemyX = 9;
		enemyY = 8;
		xSpeed = xs;
		ySpeed = ys;
		targetX=-60;
		targetY=-60;
	}
	
	public void move() {
		//make a call to find shortest path

		if(enemyX>5) enemyX += -1;
		moving = false;
	}
	
	
	public void update() {
		if (moving) {
			enemyX += xSpeed;
			enemyY += ySpeed;
		}
		if (enemyX == targetX || enemyY == targetY) {
			
			moving = false;
			targetX=-60;
			targetY=-60;
			xSpeed = 0;
			ySpeed = 0;
		}
	}
	

	
	public void drawMe(Graphics2D g2) {
		g2.drawImage(enemyImg, enemyX*60, enemyY*60, 60, 60, null);
	}
	
	
	public int getEnemyX() {
		return enemyX;
	}

	public int getEnemyY() {
		return enemyY;
	}
	
	public double getXSpeed(){
		return xSpeed;
	}
	
	public double getYSpeed(){
		return ySpeed;
	}

}
