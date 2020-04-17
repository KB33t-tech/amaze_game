package board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import panel.Panel;

/**
 * This class holds the properties of Moving Enemy and draws Moving Enemy. 
 */
public class Enemy {
	
	// Moving Enemy is displayed as a bufferedImage named "enemyImg"
	private static BufferedImage enemyImg;
	
	// the x-coordinate and the y-coordinate of Moving Enemy
	private int enemyX, enemyY;
	
	// the x-speed and the y-speed of Moving Enemy
	public double xSpeed, ySpeed;
	
	// to be used for drawing animation
	private static int posX, posY;
	
	// Player stops moving once reached the target coordinates
	private double targetX, targetY;
	
	// flag that controls when Player should be moving
	private boolean moving = false;
	
	 // keeps the direction the enemy came in so it can't turn back
	private String prev;

	
	/**
	 * The constructor loads an image to {@link #enemyImg}.
	 * It also sets the initial location and speed of Moving Enemy.
	 * @param xPos 		x-coordinate of Moving Enemy
	 * @param yPos		y-coordinate of Moving Enemy
	 * @param xs		x-speed of Moving Enemy
	 * @param ys		y-speed of Moving Enemy
	 */
	public Enemy(int xPos, int yPos, double xs, double ys) {
		try {
			enemyImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Enemy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		enemyX = 9;
		enemyY = 8;
		xSpeed = 0;
		ySpeed = 0;
		posX = enemyX*60;
		posY = enemyY*60;
		targetX=-60;
		targetY=-60;

	}
	/**@author kevin
	 * Implementing a recursive array searching function
	 * still under development
	 */
	//this function will check if something is a wall on map
	/*
	public int valueCheck(int map[][], int x, int y) {
		int val=0;
		if (x<=0 || y <= 9 ||x>=9 ||y<=0) return val;	//So we stop at boundary of array
		if(map[x][y] == 0) return val;	//base case where we hit a wall
		if(x == enemyX && y==enemyY) return val; //we got to enemy
		if(map[x][y]>= 1) {
			val = 1;
			return val;		//all other scenarios
		}

	}
	
	//this will recursively generate weighted map
	
	public int shortestPath(int map[][], int playerx, int playery) {
		int up = -1;
		int down = +1;
		int left = -1;
		int right = +1;
		
		if(valueCheck(map, playerx, playery)==0) return 0; //base case
		//recursion happens here
		return weightedMap[playerx][playery] = 1 
		+ shortestPath(map[][],playerx+left,playery)
		+ shortestPath(map[][],playerx+right,playery)
		+shortestPath(map[][],playerx,playery+up)
		+shortestPath(map[][],playerx,playery+down);
				
	}
	
	*/
	
	/**
	 * This method controls the movement of Moving Enemy.
	 * @param xs	x-speed that Moving Enemy is moving at
	 * @param ys	y-speed that Moving Enemy is moving at
	 */
	public void move(double xs, double ys) {
		enemyX+=xs;
		enemyY+=ys;
		System.out.println(xs);
		System.out.println(ys);
		if (xs!= 0) {
			if (enemyX + xs >= 0 && enemyX + xs <= 9) {
				xSpeed = xs*4;
				targetX = (enemyX)*60;
				moving = true;
			}
		}
		if (ys!= 0) {
			if (enemyY + ys >= 0 && enemyY + ys <= 9) {
				ySpeed = ys*4;
				targetY = (enemyY)*60;
				moving = true;
			}
		}
		
	}
	
	/**
	 * This method searches and moves ends here.
	 * @param map	the maze map
	 */
	public void track(int map[][]) {
		int up;
		int down;
		int left;
		int right;
		if (enemyY>=1) {
			up = map[enemyX][enemyY - 1];
		}else {
			up = 10000;
		}
		if (enemyY<=8) {
			down = map[enemyX][enemyY + 1];
		}else {
			down = 10000;
		}
		if (enemyX >=1) {
			left = map[enemyX - 1][enemyY];
		}else {
			left = 10000;
		}
		if (enemyX<=8) {
			right = map[enemyX + 1][enemyY];
		}else {
			right = 10000;
		}
		String shortest = smallest(up,down,left,right);
			
		switch(shortest) {
		case("up"):
			move(0,-1);
			prev = "down";
		break;
		case("down"):
			move(0,1);
			prev = "up";
		break;
		case("left"):
			move(-1,0);
			prev = "right";
		break;
		case("right"):
			move(1,0);
			prev = "left";
		break;
		default:
			break;
		}
		System.out.println("UP: " + up + " DOWN: "+ down + " LEFT: "+left+" RIGHT: "+right);
		System.out.println(shortest);
		
	}
	
	/**
	 * This method calculates the shortest path from Player to Moving Enemy.
	 * @param u		the up direction
	 * @param d		the down direction
	 * @param l		the left direction
	 * @param r		the right direction
	 * @return		return the direction
	 */
	private String smallest(int u ,int d , int l ,int r) {
		String dir = "g";
		int small = 10000;
		if (u < small && prev!="up") {small = u; dir = "up";}
		if (d < small && prev!="down") {small = d; dir = "down";}
		if (l < small && prev!="left") {small = l; dir = "left";}
		if (r < small && prev!="right") {small = r; dir = "right";}
		return dir;
	}
	
	/**
	 * This method detects whether Moving Enemy has moved to the desired position and should stop moving if so.
	 */
	public void update() {
		if (moving) {
			posX += xSpeed;
			posY += ySpeed;/*
			System.out.println("Target X: " + targetX);
			System.out.println("Target Y: " + targetY);
			System.out.println("Enemy X: " + enemyX);
			System.out.println("Enemy Y: " + enemyY);*/
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
	 * This method displays Moving Screen on the screen with specified location and size.
	 * @param g2 	Java's Graphics2D drawing tool
	 */
	public static void drawMe(Graphics2D g2) {
		g2.drawImage(enemyImg, posX, posY, 60, 60, null);
	}
	
	/**
	 * This method is a getter for Moving Enemy's x-coordinate.
	 * @return   x-coordinate of Moving Enemy
	 */
	public int getEnemyX() {
		return enemyX;
	}

	/**
	 * This method is a getter for Moving Enemy's y-coordinate.
	 * @return   y-coordinate of Moving Enemy
	 */
	public int getEnemyY() {
		return enemyY;
	}
	
	/*
	public int[][] getWeightedMap() {
		return weightedMap;
	}
	public void setWeightedMap(int[][] weightedMap) {
		this.weightedMap = weightedMap;
	}
	*/
	/**
	 * This method returns the x-position where this object is drawn.
	 * @return	x-position of Moving Enemy
	 */
	public int getPosX() {
		return posX;
	}
	/**
	 * This returns the y-position where this object is drawn.
	 * @return	y-position of Moving Enemy
	 */
	public int getPosY() {
		return posY;
	}
	/**
	 * This method sets x-position used for testing.
	 * @param x		x-position of Moving Enemy
	 */
	public void setPosX(int x) {
		posX = x;
	}
	/**
	 * This method sets y-position used for testing.
	 * @param y		y-position of Moving Enemy
	 */
	public void setPosY(int y) {
		posY = y;
	}
}
