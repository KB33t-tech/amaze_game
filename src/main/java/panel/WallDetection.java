package panel;

import board.Cell;
import board.Player;

/**
 * This class is wall detection. 
 * The boolean methods are called in {@link Panel#actionPerformed(java.awt.event.ActionEvent)} 
 * @see {@link Panel}
 */
public class WallDetection {
	
	
	private Cell cell;
	
	public WallDetection() {
		cell = new Cell();
	}
	
	/**
	 * This method detects whether the cell on Player's right is a wall.
	 * If the cell is a wall, direction becomes -1 and Player cannot move.
	 * Otherwise, direction becomes RIGHT and Player will move in the right direction.
	 * @return		direction that Player will be moving in
	 */
	public boolean right_stop() {
		
		try {
		for(int i = 0; i < cell.getMap().length; i++){
			for(int j = 0; j < cell.getMap()[i].length; j++){
				
				if(cell.getMap()[Player.getPlayerX() + 1][Player.getPlayerY()] == 0) {
//					System.out.println((player.getPlayerX()+1) + " " + player.getPlayerY());
					return true;
				}
			}
		}	
		
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Out of bound");
		}catch(Exception e2) {
			System.out.println("Others");
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
		for(int i = 0; i < cell.getMap().length; i++){
			for(int j = 0; j < cell.getMap()[i].length; j++){
				
				// once Player left the start cell by moving one cell to the right, 
				// they also cannot move back to the start cell by pressing the left arrow key
				if(cell.getMap()[Player.getPlayerX() - 1][Player.getPlayerY()] == 0 ||
						(Player.getPlayerX() == 1) && Player.getPlayerY() == 1) {
//					System.out.println((player.getPlayerX()+1) + " " + player.getPlayerY());
					return true;
				}				
			}
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
		for(int i = 0; i < cell.getMap().length; i++){
			for(int j = 0; j < cell.getMap()[i].length; j++){
				
				if(cell.getMap()[Player.getPlayerX()][Player.getPlayerY() - 1] == 0) {
//					System.out.println((player.getPlayerX()) + " " + (player.getPlayerY()-1));
					return true;
				}
			}
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
		for(int i = 0; i < cell.getMap().length; i++){
			for(int j = 0; j < cell.getMap()[i].length; j++){
							
				if(cell.getMap()[Player.getPlayerX()][Player.getPlayerY() + 1] == 0) {
//					System.out.println(player.getPlayerX() + " " + (player.getPlayerY()+1));
					return true;
				}
			}
		}
		return false;
	}

}