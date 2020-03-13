package board;

import java.awt.Color;



import java.awt.Graphics2D;
import java.util.ArrayList;
import board.Reward;
import board.Punishment;

public class Cell {
	
	// temporary map!!!! Insert new map here:
	// 10x10 map, 0 is a wall; 1 is a tile
	private int map[][] = {
			{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 1, 1, 1, 1, 0, 1, 0, 0 },
			{ 0, 1, 1, 0, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 0, 0, 0, 1, 0, 1, 1, 0 },
			{ 0, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
			{ 0, 0, 1, 1, 0, 1, 0, 1, 1, 0 },
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
			{ 0, 0, 1, 1, 0, 1, 0, 1, 1, 0 },
			{ 0, 1, 0, 1, 1, 1, 0, 1, 0, 0 },
			{ 0, 1, 1, 0, 1, 0, 1, 1, 1, 0 },
			{ 0, 0, 1, 1, 1, 1, 1, 0, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
	};
	
	// create an arrayList of regular rewards
	private ArrayList <Reward> reward = new ArrayList<Reward>();
			
	// create an arrayList of punishments
	private ArrayList <Punishment> punishment = new ArrayList<Punishment>();
		
	// set the number of regular reward and punishment
	private int rewardNum = 0;
	private int punishmentNum = 0;
	
	// holds the random position for each reward and punishment
	private int randomX, randomY;
	
	// each cell size is 60px
	public int cellSize;
	
	
	public Cell() {
		cellSize = 60;
		
	
		// randomly generates the position for each regular reward, 
		// and change the value of that cell to 99 to indicate that it contains a regular reward
		// a total of 12 regular rewards will be generated (can be any number)
		for (int i = 0; rewardNum < 20; i++){
			randomX = (int)Item.random(1, 9);
			randomY = (int)Item.random(1, 9);		
			
			if(item_map[randomX][randomY] != 0 && item_map[randomX][randomY] != 99) {
				item_map[randomX][randomY] = 99;
				rewardNum+=1;
				
				// when an unused cell is found, add a new reward to the reward arrayList with this position:
				reward.add(new Reward (randomX, randomY));	
			}
		}
		
		
		// randomly generates the position for each punishment
		// and change the value of that cell to 5 to indicate that it contains a regular reward
		// a total of 4 regular rewards will be generated (can be any number)
		for (int i = 0; punishmentNum < 4; i++){
			randomX = (int)Item.random(1, 9);
			randomY = (int)Item.random(1, 9);		
			
			if(randomX != 1 && randomX != 2 && randomY != 1 && item_map[randomX][randomY] != 0 
					&& item_map[randomX][randomY] != 99 && item_map[randomX][randomY] != 5 ) {
				item_map[randomX][randomY] = 5;
				punishmentNum+=1;
				
				// when an unused cell is found, add a new punishment to the punishment arrayList with this position:
				punishment.add(new Punishment (randomX, randomY));
			}
		}
	}
	
	
	public void drawMap(Graphics2D g2) {
			
		try{
			for(int i = 0; i < map.length; i++){
				for(int j = 0; j < map[i].length; j++){
					if(map[i][j] == 0) {
						
						// draw temporary walls 
						g2.setColor(Color.black);
						g2.fillRect(i*cellSize, j*cellSize, cellSize, cellSize);
					}
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Out of bound");
		}catch(Exception e2){
			System.out.println("Others");
		}
		
		
		// controls the regular rewards
		for (int i = 0; i < reward.size(); i++){
			Reward rewardi = reward.get(i);
			rewardi.drawMe(g2);
			
			// detect if Player and a reward touch
			if(rewardi.detectCollision(Player.getPlayerX(), Player.getPlayerY(), rewardi.fireX, rewardi.fireY)) {
				
				// call the changeScore function to increase the score
				// need to assign score variable to this function:
				//rewardi.changeScore(score);
				
				// remove the reward that Player touches
				reward.remove(rewardi);
				
				// change the value of the cell from 99 back to 1 (meaning it's an empty cell),
				// so that a Bonus reward can reuse the cell
				item_map[Player.getPlayerX()][Player.getPlayerY()] = 1;
			}
		}
		
		/* check if all regular rewards have been collected
		if(reward.size() == 0) {
			System.out.println("Done");
		}
		*/
		
		// controls the punishments
		for (int i = 0; i < punishment.size(); i++){
			Punishment punishmenti = punishment.get(i);
			punishmenti.drawMe(g2);
			
			// detect if Player and a reward touch
			if(punishmenti.detectCollision(Player.getPlayerX(), Player.getPlayerY(), punishmenti.punishmentX, punishmenti.punishmentY)) {
				
				// call the changeScore function to decrease the score
				// need to assign score variable to this function:
				//punishmenti.changeScore(score);
				
				// remove the punishment that Player touches
				punishment.remove(punishmenti);
				
				// reset the value of the cell back to 1
				item_map[Player.getPlayerX()][Player.getPlayerY()] = 1;
			}
		}
	
	}
	public int[][] getMap(){
		return map;
	}
	
	
}
