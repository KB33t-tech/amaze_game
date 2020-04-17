package board;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import panel.Panel;
import board.Cell;

/**
 * This class holds the properties of Player and draws Player. 
 */
public class Player{
	
	// Player is displayed as a bufferedImage named "playerImg"
	private static BufferedImage playerImg;
	
	// the x-coordinate and the y-coordinate of Player
	private static int playerX, playerY;
	
	// the x-speed and the y-speed of Player
	private double xSpeed, ySpeed;
	
	// to be used for drawing animation
	private static int posX, posY; 

	// flag that controls when Player should be moving
	private boolean moving;
	
	// Player stops moving once reached the target coordinates
	private double targetX, targetY; 
	
	/**
	 * The constructor loads an image to {@link #playerImg}.
	 * It also sets up the initial position and speed of Player.
	 */
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
	
	/**
	 * This method controls the movement of Player.
	 * It is called every time the user presses an arrow key.
	 * @param xs  	x-speed that Player is moving at
	 * @param ys	y-speed that Player is moving at
	 */
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
	
	/**
	 * This method calculates the shortest path from Player to Moving Enemy.
	 * @param wMap	a weighted map
	 * @param map	the maze map
	 * @param x		the x-coordinate of Player
	 * @param y		the y-coordinate of Player
	 * @param count	the number of cells from Player to Moving Enemy
	 */
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
	
	/**
	 * This method detects whether Player has moved to the desired position and should stop moving if so.
	 */
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
	
	/**
	 * This method displays Player on the screen with specified location and size.
	 * @param g2 	Java's Graphics2D drawing tool
	 */
	public static void drawMe(Graphics2D g2) {
		
		g2.drawImage(playerImg, posX, posY, 60, 60, null);
	}
	

	
	/**
	 * This method is a getter for Player's x-coordinate.
	 * @return   x-coordinate of Player
	 */
	public int getPlayerX() {
		return playerX;
	}

	/**
	 * This method is a getter for Player's y-coordinate.
	 * @return   y-coordinate of Player
	 */
	public int getPlayerY() {
		return playerY;
	}
	/**
	 * This method returns the x-position where this object is drawn.
	 * @return	y-coordinate of Player
	 */
	public int getPosX() {
		return posX;
	}
	/**
	 * This method returns the y-position where this object is drawn.
	 * @return	y-coordinate of Player
	 */
	public int getPosY() {
		return posY;
	}
	
	/**
	 * This method sets the x-position used for testing.
	 * @param x		the x-position of Player
	 */
	public void setPosX(int x) {
		posX = x;
	}
	/**
	 * This method sets y-position used for testing.
	 * @param y		the y-position of Player
	 */
	public void setPosY(int y) {
		posY = y;
	}
	
}
