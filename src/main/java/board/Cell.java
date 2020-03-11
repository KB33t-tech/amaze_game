package board;

import java.awt.Color;

import java.awt.Graphics2D;
import java.util.ArrayList;
import board.Reward;
import board.Punishment;

public class Cell {
	
	public int cellSize;
	
	// temporary map!!!! Insert new map here:
	// 10x10 map, 0 is a wall; 1 is a tile
	public int map[][] = {
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
	
	// create an arrayList of regular rewards
	private ArrayList <Reward> reward = new ArrayList<Reward>();
		
	// create an arrayList of punishments
	private ArrayList <Punishment> punishment = new ArrayList<Punishment>();
	
	
	public Cell() {
		cellSize = 60;
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
	
		}

}
