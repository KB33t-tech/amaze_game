package panel;

import board.Cell;
import board.Player;

/**
 * This class is wall detection. 
 * The boolean methods are called in {@link Panel#actionPerformed(java.awt.event.ActionEvent)} 
 */
public class WallDetection {
	
	
	private Cell cell;
	private Player player;
	
	public WallDetection() {
		cell = new Cell();
		player = new Player();
	}
	
	/**
	 * This method detects whether the cell on Player's right is a wall.
	 * If the cell is a wall, direction becomes -1 and Player cannot move.
	 * Otherwise, direction becomes RIGHT and Player will move in the right direction.
	 * @return		direction that Player will be moving in
	 */
	public boolean right_stop() {

				if(cell.getMap()[player.getPlayerX() + 1][player.getPlayerY()] == 0) {
//					System.out.println((player.getPlayerX()+1) + " " + player.getPlayerY());
					return true;
				}
		return false;
	}
	
	/**
	 * This method detects whether the cell on Player's left is a wall.
	 * If the cell is a wall, direction becomes -1 and Player cannot move.
	 * Otherwise, direction becomes LEFT and Player will move in the left direction.
	 * @return		direction that Player will be moving in
	 */
	public boolean left_stop() {

				// once Player left the start cell by moving one cell to the right, 
				// they also cannot move back to the start cell by pressing the left arrow key
				if(cell.getMap()[player.getPlayerX() - 1][player.getPlayerY()] == 0 ||
						(player.getPlayerX() == 1) && player.getPlayerY() == 1) {
//					System.out.println((player.getPlayerX()+1) + " " + player.getPlayerY());
					return true;
		}
		return false;
	}
	
	/**
	 * This method detects whether the cell above Player is a wall.
	 * If the cell is a wall, direction becomes -1 and Player cannot move.
	 * Otherwise, direction becomes UP and Player will move in the up direction.
	 * @return		direction that Player will be moving in
	 */
	public boolean up_stop() {

				if(cell.getMap()[player.getPlayerX()][player.getPlayerY() - 1] == 0) {
//					System.out.println((player.getPlayerX()) + " " + (player.getPlayerY()-1));
					return true;
					}
				
		return false;
	}
	
	/**
	 * This method detects whether the cell below Player is a wall.
	 * If the cell is a wall, direction becomes -1 and Player cannot move.
	 * Otherwise, direction becomes DOWN and Player will move in the down direction.
	 * @return		direction that Player will be moving in
	 */
	public boolean down_stop() {
				if(cell.getMap()[player.getPlayerX()][player.getPlayerY() + 1] == 0) {
//					System.out.println(player.getPlayerX() + " " + (player.getPlayerY()+1));
					return true;
					}
		return false;
	}

}
