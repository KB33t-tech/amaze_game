package board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import panel.Panel;

public class Enemy {
	
	private static BufferedImage enemyImg;
	private int enemyX, enemyY;
	private static int posX, posY;
	public double xSpeed, ySpeed;
	public double enemySpeed = 7.0;
	private double targetX, targetY;
	private boolean moving = false;
	private String prev; // keeps the direction the enemy came in so it can't turn back



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
	//Search and move ends here
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
	private String smallest(int u ,int d , int l ,int r) {
		String dir = "g";
		int small = 10000;
		if (u < small && prev!="up") {small = u; dir = "up";}
		if (d < small && prev!="down") {small = d; dir = "down";}
		if (l < small && prev!="left") {small = l; dir = "left";}
		if (r < small && prev!="right") {small = r; dir = "right";}
		return dir;
	}
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
	

	
	public static void drawMe(Graphics2D g2) {
		g2.drawImage(enemyImg, posX, posY, 60, 60, null);
	}
	
	
	public int getEnemyX() {
		return enemyX;
	}

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
}
