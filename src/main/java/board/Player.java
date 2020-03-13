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
	private int posX, posY; //to be used for drawing animation

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
		posX = playerX*60;
		posY = playerY*60;
		xSpeed = 0;
		ySpeed = 0;
		moving = false;
		targetX=-60;
		targetY=-60;
	}
	
	public void move(double xs, double ys) {
		playerX+=xs;
		playerY+=ys;
		if (xs!= 0) {
		if (playerX + xs >= 0 && playerX + xs <= 10) {
			xSpeed = xs*4;
			targetX = (playerX)*60;
			moving = true;
		}
		}
		if (ys!= 0) {
		if (playerY + ys >= 0 && playerY + ys <= 10) {
			ySpeed = ys*4;
			targetY = (playerY)*60;
			moving = true;
		}
		}
		
	}
	public void beacon( int wMap[][], int map[][],int x, int y, int count) { // every tick the player will update the weighted map to show the
		/*for (int i=0;i<10;i++) {					 // shortest path to it
			for(int j=0;j<10;j++) {
				if (map[i][j]==0) {
					wMap[i][j] = 1000;
				}else {
					wMap[i][j] = Math.abs(playerX - i)+ Math.abs(playerY - j);
				}
			}
		}*/
		wMap[playerX][playerY] = 0;
		count++;
		if(x>=1) {
			if(map[x-1][y]!=0 && count < wMap[x-1][y]) {
				wMap[x-1][y]= count;
				beacon(wMap,map,x-1,y,count);
			}
		}
		if(x<=8) {
			if(map[x+1][y]!=0 && count < wMap[x+1][y]) {
				wMap[x+1][y]= count;
				beacon(wMap,map,x+1,y,count);
			}
		}
		if(y>=1) {
			if(map[x][y-1]!=0 && count < wMap[x][y-1]) {
				wMap[x][y-1]= count;
				beacon(wMap,map,x,y-1,count);
			}
		}
		if(y<=8) {
			if(map[x][y+1]!=0 && count < wMap[x][y+1]) {
				wMap[x][y+1]= count;
				beacon(wMap,map,x,y+1,count);
			}
		}
		
	}
	public void update() {
		if (moving) {
			posX += xSpeed;
			posY += ySpeed;
		}
		if (posX == targetX || posY == targetY) {
			
			moving = false;
			targetX=-60;
			targetY=-60;
			xSpeed = 0;
			ySpeed = 0;
		}
	}
	
	public void drawMe(Graphics2D g2) {
		g2.drawImage(playerImg, posX, posY, 60, 60, null);
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
