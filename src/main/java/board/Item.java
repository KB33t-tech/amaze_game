package board;

import java.awt.Graphics2D;

// to be extended by Reward and Punishment classes
public abstract class Item {
	
	// draws the reward and punishment
	public abstract void drawMe(Graphics2D g2);
	
	// changes the score when Player collects rewards or moves into punishments
	public abstract int changeScore(int score);
	
	// detects collision between 1) Player and rewards; 2) Player and punishments
	public boolean detectCollision(int a, int b, int x, int y, int w, int h) {
		  if ((a > x && a < x + w) && (b > y && b < y + h)) {
		    return true;
		  } else {
		    return false;
		  }
		}
}
