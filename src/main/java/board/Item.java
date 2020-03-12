package board;

import java.awt.Graphics2D;

// to be extended by Reward and Punishment classes
public abstract class Item {
	
	// draws the reward and punishment
	protected abstract void drawMe(Graphics2D g2);
	
	// changes the score when Player collects rewards or moves into punishments
	protected abstract int changeScore(int score);
	
	// detects collision between 1) Player and rewards; 2) Player and punishments
	public boolean detectCollision(int a, int b, int x, int y) {
		if ((a == x) && (b == y)) {
			return true;
		} else {
			return false;
		}
	}
	
	// random function that randomly generates an item's position
	public static double random(double low, double high) {
		return low + Math.random() * (high - low);
	}
}
