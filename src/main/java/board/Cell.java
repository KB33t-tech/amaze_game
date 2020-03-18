package board;

import java.awt.Color;


import java.awt.Graphics2D;
import java.util.ArrayList;
import board.Reward;
import panel.Panel;
import board.Bonus;
import board.Punishment;

/**
 * This class controls the layout of the maze, 
 * the positions of regular rewards {@link Reward}, bonuses {@link Bonus}, and punishments {@link Punishment}, 
 * collision detection between Player and above items, 
 * as well as the winning/losing state of Player.
 * Everything inside this class is drawn in {@link Board}.
 */
public class Cell {
	
	// 2D array that stores the layout of the map, where 0 is a wall and 1 is a valid cell
	private int map[][] = {
			{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 1, 1, 1, 1, 0, 1, 0, 0 },
			{ 0, 1, 1, 0, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 0, 0, 0, 1, 0, 1, 1, 0 },
			{ 0, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
			{ 0, 1, 1, 1, 0, 1, 1, 1, 1, 0 },
			{ 0, 1, 0, 1, 1, 1, 0, 1, 0, 0 },
			{ 0, 1, 1, 0, 1, 0, 1, 1, 1, 0 },
			{ 0, 0, 1, 1, 1, 1, 1, 0, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
	};
	
	// a copy of the map that keeps track of what a cell contains 
	private int item_map[][] = {
			{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 1, 1, 1, 1, 0, 1, 0, 0 },
			{ 0, 1, 1, 0, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 0, 0, 0, 1, 0, 1, 1, 0 },
			{ 0, 1, 1, 0, 1, 1, 1, 0, 0, 0 },
			{ 0, 1, 1, 1, 0, 1, 1, 1, 1, 0 },
			{ 0, 1, 0, 1, 1, 1, 0, 1, 0, 0 },
			{ 0, 1, 1, 0, 1, 0, 1, 1, 1, 0 },
			{ 0, 0, 1, 1, 1, 1, 1, 0, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
	};
	
	// create an arrayList of regular rewards
	private ArrayList <Reward> reward = new ArrayList<Reward>();
			
	// create an arrayList of punishments
	private ArrayList <Punishment> punishment = new ArrayList<Punishment>();
	
	// create an arrayList of bonus
	private ArrayList <Bonus> bonus = new ArrayList<Bonus>();
		
	// set the number of regular reward and punishment
	private int rewardNum = 0;
	private int punishmentNum = 0;
	private int bonusNum = 0;
	
	// holds the random position for each reward and punishment
	private int randomX, randomY;
	
	// each cell size is 60px
	public int cellSize;
	
	// keeps track of the timing of bonus rewards
	private int bonusTick = 0;
	
	// keeps track of the score
	private int score;
	private int time;
	
	
	/**
	 * The constructor sets the initial position of each regular reward and punishment.
	 */
	public Cell() {
		cellSize = 60;
		score = 0;
		time = 0;

	
		// randomly generates the position for each regular reward, 
		// and change the value of that cell to 99 to indicate that it contains a regular reward
		// a total of 15 regular rewards will be generated (can be any number)
		for (int i = 0; rewardNum < 15; i++){
			randomX = (int)Item.random(1, 9);
			randomY = (int)Item.random(1, 9);		
			
			// only add a regular reward to the board when the cell found is empty 
			if(item_map[randomX][randomY] != 0 && item_map[randomX][randomY] != 99) {
				item_map[randomX][randomY] = 99;
				
				rewardNum+=1;
				
				// when an unused cell is found, add a new reward to the reward arrayList with this position:
				reward.add(new Reward (randomX, randomY));	
			}
		}
		
		
		// randomly generates the position for each punishment
		// and change the value of that cell to 5 to indicate that it contains a regular reward
		// a total of 3 regular rewards will be generated (can be any number)
		for (int i = 0; punishmentNum < 3; i++){
			randomX = (int)Item.random(1, 9);
			randomY = (int)Item.random(1, 9);		
			
			// only add a punishment to the board when the cell found is empty 
			if(randomX != 1 && randomX != 2 && randomY != 1 && item_map[randomX][randomY] != 0 
					&& item_map[randomX][randomY] != 99 && item_map[randomX][randomY] != 5 ) {
				item_map[randomX][randomY] = 5;
				
				punishmentNum+=1;
				
				// when an unused cell is found, add a new punishment to the punishment arrayList with this position:
				punishment.add(new Punishment (randomX, randomY));
			}
		}
	}
	
	
	/**
	 * This method draws the walls of the maze, regular rewards, punishments, and bonus rewards.
	 * It also detects collision between Player and the above items.
	 * @param g2  Java's Graphics2D drawing tool
	 */
	public void drawMap(Graphics2D g2) {
	
		// draws the walls of the maze
		try{
			for(int i = 0; i < map.length; i++){
				for(int j = 0; j < map[i].length; j++){
					if(map[i][j] == 0) {
						g2.setColor(new Color(0, 0, 0, 85));
						g2.fillRect(i*cellSize, j*cellSize, cellSize, cellSize);
					}
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Out of bound");
		}catch(Exception e2){
			System.out.println("Others");
		}
		
		
		// turn the start point into a wall once Player left the start cell
		if(Player.getPlayerX() > 0) {
			map[0][1] = 0;
		}
		
		
		// controls the regular rewards
		for (int i = 0; i < reward.size(); i++){
			Reward rewardi = reward.get(i);
			
			// display each regular reward
			rewardi.drawMe(g2);
			
			// detect if Player and a reward touch
			if(rewardi.detectCollision(Player.getPlayerX(), Player.getPlayerY(), rewardi.fireX, rewardi.fireY)) {
				
				// update the score
				score = rewardi.changeScore(score);
				
				// remove the reward that Player touches
				reward.remove(rewardi);
				
				// change the value of the cell from 99 back to 1 (meaning it's an empty cell),
				// so that a Bonus reward can reuse the cell
				item_map[Player.getPlayerX()][Player.getPlayerY()] = 1;
			}
		}
		
		
		// controls the punishments
		for (int i = 0; i < punishment.size(); i++){
			Punishment punishmenti = punishment.get(i);
			
			// display each punishment
			punishmenti.drawMe(g2);
			
			// detect if Player and a reward touch
			if(punishmenti.detectCollision(Player.getPlayerX(), Player.getPlayerY(), punishmenti.punishmentX, punishmenti.punishmentY)) {
				
				// update the score
				score = punishmenti.changeScore(score);
				
				// remove the punishment that Player touches
				punishment.remove(punishmenti);
				
				// reset the value of the cell back to 1
				item_map[Player.getPlayerX()][Player.getPlayerY()] = 1;
			}
		}
		

		bonusTick++;
//		System.out.println(bonusTick);
		
		// controls the bonus rewards
		for (int i = 0; bonusNum < 1; i++){
			randomX = (int)Item.random(1, 9);
			randomY = (int)Item.random(1, 9);		
			
			// only add a bonus rewards to the board when the cell found is empty 
			if(randomY != 8 && item_map[randomX][randomY] != 0 && 
					item_map[randomX][randomY] != 99 && item_map[randomX][randomY] != 5) {
				bonusNum+=1;
				bonus.add(new Bonus (randomX, randomY));
			}
		}
		
		if(bonusTick < Panel.TICK*30) {		// appear for 40 ticks
			for (int i = 0; i < bonus.size(); i++){
				Bonus bonusi = bonus.get(i);
				
				// display each bonus reward
				bonusi.drawMe(g2);
				
				// when the bonus reward is visible, detect if Player and bonus reward touch
				if(bonusi.detectCollision(Player.getPlayerX(), Player.getPlayerY(), bonusi.bonusX, bonusi.bonusY)) {
					
					// update the score
					score = bonusi.changeScore(score);
					
					// remove the punishment that Player touches
					bonus.remove(bonusi);
					
					// set the timer to 10 ticks before it shows up again 
					// so Player only has to wait for 10 ticks for the next bonus when they collect it early
					bonusTick = Panel.TICK*30;
				}
			}
		}
		
		// if Player didn't collect the bonus reward before it disappears,
		// then remove it from the screen
		if(bonusTick >= Panel.TICK*40) {	// disappear for 10 ticks
			for (int i = 0; i < bonus.size(); i++){
				Bonus bonusi = bonus.get(i);
				bonus.remove(bonusi);
			}

			// reset the timer every time a new bonus reward appears
			bonusTick = 0;
			bonusNum = 0;
		}
	}
	
	/**
	 * This method returns the layout of the maze.
	 * @return 	 2D array that stores the layout of the maze
	 */
	public int[][] getMap(){
		return map;
	}
	
	
	/**
	 * This method detects if Player has won or lost the game.
	 * @return	 string that shows the state of the game
	 */
	public String detectWin() {
		
		// check if the score is negative and change the game state to "LOSE" if it is
		if(score < 0) {
			Panel.stateStr = "LOSE";
		}
				
		// check if all regular rewards have been collected and if Player is on the exit cell
		// change the game state to "WIN"
		if(reward.size() == 0 && Player.getPlayerX() == 9 && Player.getPlayerY() == 8) {
			System.out.println("Done");
			Panel.stateStr = "WIN";
		}
		return Panel.stateStr;
	}
	
	/**
	 * This method is called in {@link Board#displayScore(Graphics2D, int, int)} 
	 * so that the board can display the latest score.
	 * @return   the latest score
	 */
	public int updateScore() {
		return score;
	}
	/**
	 * This method is called in {@link Board#displayTime(Graphics2D, int, int)} 
	 * so that the board can display the latest time.
	 * @return   the latest time
	 */
	public int updateTime() {
		return (++time/30);	//time is displayed in seconds
	}
	/**
	 * This method is called in {@link Board#displayTime(Graphics2D, int, int)} 
	 * Method is use to stop the time.
	 * @return   the latest time
	 */
	public int stopTime() {
		return time/30;
	}
	
	
}
