package board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import panel.Panel;

public class Enemy {
	
	private BufferedImage enemyImg;
	public int enemyX, enemyY;
	public double xSpeed, ySpeed;
	public double enemySpeed = 7.0;
	private double targetX, targetY;
	private boolean moving = false;
	private int weightedMap[][]= {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	};//will be used to find shortest path
	




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
	
	
	
	public void move(int playerX, int playerY, int map[][], int weightedMap[][] ) {
		//make a call to find shortest path
		shortestPath(weightedMap[][],map[playerX][playerY]);

				
	}
	*/
	//Search and move ends here
	
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
	public int[][] getWeightedMap() {
		return weightedMap;
	}
	public void setWeightedMap(int[][] weightedMap) {
		this.weightedMap = weightedMap;
	}

}
