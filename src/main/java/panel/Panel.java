package panel;
import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import menu.Cover;
import board.Board;
import board.Enemy;
import board.Player;
import board.Cell;

public class Panel extends JPanel implements ActionListener, KeyListener {
	
	public final static int WIN_X = 0;
	public final static int WIN_Y = 0;
	public final static int WIN_W = 600;
	public final static int WIN_H = 700;
	public final static int BOARD_H = 600;
	
	
	private JFrame frame;
	
	private JButton playButton, insButton, gobackButton, exitButton;
	
	private Cover cover;
	private Player player;
	private Enemy enemy;
	private Board board;
	private Cell cell;
	
	private int tickCount;
	
	private Timer timer;

	// booleans that control the keyboard
	private final static int UP = 0;
	private final static int DOWN = 1;
	private final static int LEFT = 2;
	private final static int RIGHT = 3;
	private int direction;
	
	private final static int START_SCREEN = -1;
	private final static int INSTRUCTION = 0;
	private final static int GAME = 1;
	private final static int TICK = 60;

	// set initial state of the game
	public static int state = START_SCREEN;

	
	public Panel(JFrame frame) throws IOException {
		super();
		
		this.setBackground(Color.white);
		setPreferredSize(new Dimension(WIN_W, WIN_H));
		
		direction = -1;

		cover = new Cover();
		board = makeBoard();
		player = new Player();
		enemy = spawnEnemy();
		cell = new Cell();

		tickCount = 0;
		timer = new Timer(30, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
		
		this.frame = frame;
		
		
		// add buttons
		playButton = new JButton("Play");
		this.add(playButton);
		playButton.setBounds(200, 630, 80, 30);
		playButton.setVisible(false);
		playButton.addActionListener(this);
		
		insButton = new JButton("Instruction");
		this.add(insButton);
		insButton.setBounds(300, 630, 100, 30);
		insButton.setVisible(false);
		insButton.addActionListener(this);
		
		gobackButton = new JButton("Go back");
		this.add(gobackButton);
		gobackButton.setBounds(WIN_W/2-40, 600, 80, 30);
		gobackButton.setVisible(false);
		gobackButton.addActionListener(this);
		
		exitButton = new JButton("Exit");
		this.add(exitButton);
		exitButton.setBounds(WIN_W/2-40, 670, 80, 30);
		exitButton.setVisible(false);
		exitButton.addActionListener(this);
		
	}
	
	
	public static boolean pointInRect(int a, int b, int x, int y, int w, int h) {
		  if ((a >= x && a <= x + w) && (b >= y && b <= y + h)) {
		    return true;
		  } else {
		    return false;
		  }
		}

	//method spawn: Puts the Enemy on the board diagonal to player
	public Enemy spawnEnemy() {
		return new Enemy(WIN_W-60, WIN_H-230, 0, 0);
	}
	
	//Loads the board
	public Board makeBoard() {
		return new Board();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// if game state is at start screen
		if (state == START_SCREEN){
			cover.drawCover(g2);		
			
			playButton.setVisible(true);
			insButton.setVisible(true);
			gobackButton.setVisible(false);
			exitButton.setVisible(false);
			timer.setDelay(150);
		}
		
		// if game state is at in-game
		if (state == GAME){
			board.drawMe(g2);
			player.drawMe(g2);
			enemy.drawMe(g2);

			playButton.setVisible(false);
			insButton.setVisible(false);
			gobackButton.setVisible(false);
			exitButton.setVisible(true);
			timer.setDelay(30);
		}
		
		// if game state is at instruction
		if (state == INSTRUCTION) {
			cover.drawInstruction(g2);
			
			playButton.setVisible(false);
			insButton.setVisible(false);
			gobackButton.setVisible(true);
			exitButton.setVisible(false);
		}
	}


	public void keyTyped(KeyEvent e) {

		
	}


	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			direction = RIGHT;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			direction = LEFT;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			direction = UP;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			direction = DOWN;
		
	}


	public void keyReleased(KeyEvent e) {
	}
	
	
	// will try to make wall detection less repetitive
	public int right_stop() {
		for(int i = 0; i < cell.map.length; i++){
			for(int j = 0; j < cell.map[i].length; j++){
				
				if(cell.map[player.getPlayerX() + 1][player.getPlayerY()] == 0) {
//					System.out.println((player.getPlayerX()+1) + " " + player.getPlayerY());
					direction = -1;
				}
			}
		}	
		return direction;
	}
	
	
	public int left_stop() {
		for(int i = 0; i < cell.map.length; i++){
			for(int j = 0; j < cell.map[i].length; j++){
							
				if(cell.map[player.getPlayerX() - 1][player.getPlayerY()] == 0) {
//					System.out.println((player.getPlayerX()+1) + " " + player.getPlayerY());
					direction = -1;
				}				
			}
		}
		return direction;
	}
	
	public int up_stop() {
		for(int i = 0; i < cell.map.length; i++){
			for(int j = 0; j < cell.map[i].length; j++){
				
				if(cell.map[player.getPlayerX()][player.getPlayerY() - 1] == 0) {
//					System.out.println((player.getPlayerX()) + " " + (player.getPlayerY()-1));
					direction = -1;;
				}
			}
		}
		return direction;
	}
	
	
	public int down_stop() {
		for(int i = 0; i < cell.map.length; i++){
			for(int j = 0; j < cell.map[i].length; j++){
							
				if(cell.map[player.getPlayerX()][player.getPlayerY() + 1] == 0) {
//					System.out.println(player.getPlayerX() + " " + (player.getPlayerY()+1));
					direction = -1;;
				}
			}
		}
		return direction;
	}


	public void actionPerformed(ActionEvent e) {
		tickCount ++;
		// if "Play" button is pressed at the start screen, 
		// change game state to GAME
		if (e.getActionCommand() == "Play" && state == START_SCREEN) 
			state = GAME;	
		
		// if "Instruction" button is pressed at the start screen, 
		// change game state to INSTRUCTION
		if (e.getActionCommand() == "Instruction" && state == START_SCREEN) 
			state = INSTRUCTION;	
		
		// if "Go back" button is pressed at the instruction screen, 
		// exit and change game state to START SCREEN
		if (e.getActionCommand() == "Go back" && state == INSTRUCTION) 
			state = START_SCREEN;
		
		// if "Exit" button is pressed during the game, 
		// exit and change game state to START SCREEN
		if (e.getActionCommand() == "Exit" && state == GAME) 
			state = START_SCREEN;	
		
		
		if (tickCount == 6) {			//Changing tick count speeds up player
			
			//Trying to implement detection so that if player and enemy touch we end game
			//For now start Screen will be Endgame state  but we should make a lose/win screen
			if(player.getPlayerX()==enemy.getEnemyX() && player.getPlayerY() == enemy.getEnemyY()) {
				state = START_SCREEN;
			}
			
			if(player.getPlayerX() != 9) {	// prevents the player from going out of the screen when at the exit
				if (direction == RIGHT) {
					if(right_stop() != -1) {
						player.move(1, 0);
					}
				}
			}
			
			if(player.getPlayerX() != 0) {	// prevents the player from going out of the screen when at the start point
				if (direction == LEFT) {
					if(left_stop() != -1) {
						player.move(-1, 0);
					}	
				}
			
			}
			if(direction == UP) {
				if(up_stop() != -1) {
					player.move(0,-1);		
				}
			}
			
			if(direction == DOWN) {
				if(down_stop() != -1) {
					player.move(0, 1);
				}
			}
			
			tickCount = 0;
			direction = -1;
		}
//		player.update();
	
		repaint();
	}
	
}
	