package others;

import java.awt.Graphics2D;

import board.Board;
import board.Enemy;
import board.Player;
import others.State;
import panel.Panel;
import others.Screen;

public class ChangeState {
	
	State state;	
	private Screen screen;
	private Board board;
	
	public ChangeState() {
		
		screen = new Screen();
		board = makeBoard();
	}
	
	
	//Loads the board
	public Board makeBoard() {
		return new Board();
	}
	
	
	public void getState(Graphics2D g2, State state) {
	
		switch(state) {
			case START_SCREEN:
				screen.drawCover(g2);		
				
				Panel.playButton.setVisible(true);
				Panel.insButton.setVisible(true);
				Panel.gobackButton.setVisible(false);
				Panel.exitButton.setVisible(false);
				Panel.replayButton.setVisible(false);
//				System.out.println("In state START_SCREEN");
				break;
		
			case INSTRUCTION:
				screen.drawInstruction(g2);
				Panel.playButton.setVisible(false);
				Panel.insButton.setVisible(false);
				Panel.gobackButton.setVisible(true);
				Panel.replayButton.setVisible(false);
				Panel.exitButton.setVisible(false);
//				System.out.println("In state INSTRUCTION");
				break;
			 
			case GAME:
				board.drawMe(g2);
				Player.drawMe(g2);
				Enemy.drawMe(g2);
				
				Panel.playButton.setVisible(false);
				Panel.insButton.setVisible(false);
				Panel.gobackButton.setVisible(false);
				Panel.exitButton.setVisible(true);
				Panel.replayButton.setVisible(false);
//				System.out.println("In state GAME");
				break;
			 
			case LOSE:
				screen.drawLose(g2);
				
				Panel.playButton.setVisible(false);
				Panel.insButton.setVisible(false);
				Panel.gobackButton.setVisible(false);
				Panel.exitButton.setVisible(false);
//				Panel.replayButton.setVisible(true);
//				System.out.println("In state LOSE");
				break;
			 
			case WIN:
				screen.drawWin(g2);
//				System.out.println("In state WIN");
				break;
	
		}
	}

}
