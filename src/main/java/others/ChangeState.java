package others;

import java.awt.Graphics2D;


import java.io.IOException;

import board.Board;
import board.Cell;
import board.Enemy;
import board.Player;
import others.State;
import panel.Panel;
import others.Screen;

/**
 * This class switches the state of the game and controls what is being displayed in each state.
 * @see State
 */
public class ChangeState {
	
	// declare a Screen object
	private Screen screen;
	
	// declare a Board object
	private Board board;
	
	/**
	 * The constructor create a new Screen object and a new Board object.
	 */
	public ChangeState() {
		
		screen = new Screen();
		board = new Board();
	}
	
	
	/**
	 * This method switches the state of the game and displays different items according to the current state.
	 * Is it called in {@link panel.Panel#paintComponent(java.awt.Graphics)}.
	 * @param g2 		Java's Graphics2D drawing tool
	 * @param state 	state of the game
	 */
	public void switchState(Graphics2D g2, State state) {
		
		switch(state) {
		
			// if the state is "START_SCREEN"
			case START_SCREEN:
				
				displayStart(g2);
				break;
		
			// if the state is "INSTRUCTION"
			case INSTRUCTION:
				
				displayInstruction(g2);
				break;
			 
			// if the state is "GAME"
			case GAME:
				
				displayGame(g2);
				break;
			 
			// if the state is "LOSE"	
			case LOSE:
				
				displayLose(g2);
				break;
			 
			// if the state is "WIN"
			case WIN:
			
				displayWin(g2);
				break;
	
		}
	}

	
	private void displayStart(Graphics2D g2) {
		// displays the start (menu) screen
		screen.drawCover(g2);		
		
		// set the visibility of each button
		Panel.playButton.setVisible(true);
		Panel.insButton.setVisible(true);
		Panel.gobackButton.setVisible(false);
		Panel.exitButton.setVisible(false);
		Panel.replayButton.setVisible(false);
//		System.out.println("In state START_SCREEN");
	}
	
	
	private void displayInstruction(Graphics2D g2) { 
		// displays the instruction screen
		screen.drawInstruction(g2);
		
		Panel.playButton.setVisible(false);
		Panel.insButton.setVisible(false);
		Panel.gobackButton.setVisible(true);
		Panel.replayButton.setVisible(false);
		Panel.exitButton.setVisible(false);
//		System.out.println("In state INSTRUCTION");
	}
	
	
	private void displayGame(Graphics2D g2) { 
		// displays the board, rewards, punishments, Player, and Moving Enemy.
		board.drawMe(g2);
		Player.drawMe(g2);
		Enemy.drawMe(g2);
		
		Panel.playButton.setVisible(false);
		Panel.insButton.setVisible(false);
		Panel.gobackButton.setVisible(false);
		Panel.exitButton.setVisible(true);
		Panel.replayButton.setVisible(false);
//		System.out.println("In state GAME");
	}
	
	private void displayLose(Graphics2D g2) { 
		// displays the losing screen
		screen.drawLose(g2);

		Panel.playButton.setVisible(false);
		Panel.insButton.setVisible(false);
		Panel.gobackButton.setVisible(false);
		Panel.exitButton.setVisible(true);
		
		board.stopTime(g2,Panel.WIN_W/2-120, Panel.BOARD_H/2);
//		Panel.replayButton.setVisible(true);
//		System.out.println("In state LOSE");
	}
	
	private void displayWin(Graphics2D g2) { 
		
		// displays the winning screen and score
		screen.drawWin(g2);
		board.displayScore(g2, Panel.WIN_W/2-80, Panel.BOARD_H/2);
		board.stopTime(g2,Panel.WIN_W/2-80, Panel.BOARD_H/2+30);
		Panel.exitButton.setVisible(true);
//		System.out.println("In state WIN");
	}
}
	
