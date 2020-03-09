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
	
	public Enemy(int xPos, int yPos, double xs, double ys) {
		try {
			enemyImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Enemy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		enemyX = xPos;
		enemyY = yPos;
		xSpeed = xs;
		ySpeed = ys;
	}
	
	public void move(double xs, double ys) {
		if (enemyX + xs <= 0 || enemyX + xs >= Panel.WIN_W- 100) {
			enemyX = enemyX;		
		}else enemyX += xs;
		
		if (enemyY + ys <= 0 || enemyY + ys >= Panel.WIN_H- 200) {
			enemyY = enemyY;		
		}else enemyY += ys;
	}
	
	
	public void drawMe(Graphics2D g2) {
		g2.drawImage(enemyImg, enemyX, enemyY, 100, 100, null);
	}
	
	
	public double getenemyX() {
		return enemyX;
	}

	public double getenemyY() {
		return enemyY;
	}
	
	public double getXSpeed(){
		return xSpeed;
	}
	
	public double getYSpeed(){
		return ySpeed;
	}

}
