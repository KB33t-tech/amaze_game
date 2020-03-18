package board;

import java.awt.Graphics2D;

/**
 * It is a superclass of {@link Reward}, {@link Punishment}, {@link Bonus}.
 * @see Reward
 * @see Punishment
 * @see Bonus
 */
public abstract class Item {
	
	// x-coordinate and y-coordinate of Item
	protected int PosX, PosY;
	
	/**
	 * This abstract method lets Reward, Punishment, and Bonus classes draw their corresponding images.
	 * @param g2  Java's Graphics2D drawing tool
	 */
	protected abstract void drawMe(Graphics2D g2);
	
	/**
	 * This abstract method changes the score when Player collects rewards and bonuses.
	 * or when Player moves into punishments
	 * @param score  current score that is to be updated
	 * @return       the new updated score
	 */
	protected abstract int updateScore(int score);
	
	/**
	 * This method is a getter for retrieving an Item's x-coordinate. 
	 * @return  x-coordinate of Item
	 */
	public int getPosX() {
		return PosX;
	}
	
	/**
	 * This method is a getter for retrieving an Item's y-coordinate. 
	 * @return  y-coordinate of Item
	 */
	public int getPosY() {
		return PosY;
	}
	
	/**
	 * This is a boolean function that detects the collision between 
	 * 1) Player and regular rewards
	 * 2) Player and punishments
	 * 3) Player and bonus rewards.
	 * @param a  x-coordinate of {@link Player}
	 * @param b	 y-coordinate of {@link Player}
	 * @param x  x-coordinate of {@link Reward}, {@link Punishment}, or {@link Bonus}
	 * @param y  y-coordinate of {@link Reward}, {@link Punishment}, or {@link Bonus}
	 * @return   true if collision happened, false otherwise
	 */
	public boolean detectCollision(int a, int b, int x, int y) {
		if ((a == x) && (b == y)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This is a random method that randomly generates 
	 * the position of each item {@link Reward}, {@link Punishment}, and {@link Bonus}.
	 * @param low   the lowest number (left-most position/top-most position) that the function can generate
	 * @param high  the highest number (right-most position/bottom-most position) that the function can generate
	 * @return      position of the item
	 */
	public static double random(double low, double high) {
		return low + Math.random() * (high - low);
	}
}
