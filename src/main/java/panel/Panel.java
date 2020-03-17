package panel;
import java.awt.*;





import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import board.Board;
import board.Enemy;
import board.Player;
import others.ChangeState;
import board.Cell;
import others.State;

public class Panel extends JPanel implements ActionListener, KeyListener {
	
	public final static int WIN_X = 0;
	public final static int WIN_Y = 0;
	public final static int WIN_W = 600;
	public final static int WIN_H = 700;
	public final static int BOARD_H = 600;
	
	
	private JFrame frame;
	public static JButton playButton, insButton, gobackButton, exitButton, replayButton;
	
	private Player player;
	private Enemy enemy;
//	private Board board;
	private Cell cell;
	
	private ChangeState cs;
	public static String stateStr;
	
	private int tickCount;
	private boolean replay, exit;
	
	private Timer timer;

	// booleans that control the keyboard
	private final static int UP = 0;
	private final static int DOWN = 1;
	private final static int LEFT = 2;
	private final static int RIGHT = 3;
	private int direction;
	
	public final static int TICK = 30;
	private int weightedMap[][]= {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	};

	
	public Panel(JFrame frame) throws IOException {
		super();
		
		this.setBackground(Color.white);
		setPreferredSize(new Dimension(WIN_W, WIN_H));
		
		direction = -1;
		
		stateStr = "START_SCREEN";	// set initial state of the game
		cs = new ChangeState();

//		board = makeBoard();
		player = new Player();
		enemy = spawnEnemy();
		cell = new Cell();

		tickCount = 0;
		replay = false;
		exit = false;
		timer = new Timer(30, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
		
		this.frame = frame;
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				weightedMap[i][j] = 10000;
			}
		}
		
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
		gobackButton.setBounds(WIN_W/2-40, 650, 80, 30);
		gobackButton.setVisible(false);
		gobackButton.addActionListener(this);
		
		exitButton = new JButton("Exit");
		this.add(exitButton);
		exitButton.setBounds(WIN_W/2-40, 670, 80, 30);
		exitButton.setVisible(false);
		exitButton.addActionListener(this);
		
		replayButton = new JButton("Replay");
		this.add(replayButton);
		replayButton.setBounds(WIN_W/2-40, 650, 80, 30);
		replayButton.setVisible(false);
		replayButton.addActionListener(this);
		
	}
	

	//method spawn: Puts the Enemy on the board diagonal to player
	public Enemy spawnEnemy() {
		return new Enemy(WIN_W-60, WIN_H-230, 0, 0);
	}

	/*
	//Loads the board
	public Board makeBoard() {
		return new Board();
	}
	*/
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		cs.getState(g2, State.valueOf(stateStr));

		
		//Detects if player and moving enemy touch
		if(player.getPlayerX() == enemy.getEnemyX() && player.getPlayerY() == enemy.getEnemyY()) {
			stateStr = "LOSE";
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
	private int right_stop() {
		
		try {
		for(int i = 0; i < cell.getMap().length; i++){
			for(int j = 0; j < cell.getMap()[i].length; j++){
				
				if(cell.getMap()[player.getPlayerX() + 1][player.getPlayerY()] == 0) {
//					System.out.println((player.getPlayerX()+1) + " " + player.getPlayerY());
					direction = -1;
				}
			}
		}	
		
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Out of bound");
		}catch(Exception e2) {
			System.out.println("Others");
		}
		
		return direction;
	}
	
	
	private int left_stop() {
		for(int i = 0; i < cell.getMap().length; i++){
			for(int j = 0; j < cell.getMap()[i].length; j++){
							
				if(cell.getMap()[player.getPlayerX() - 1][player.getPlayerY()] == 0 ||
						(player.getPlayerX() == 1) && player.getPlayerY() == 1) {
//					System.out.println((player.getPlayerX()+1) + " " + player.getPlayerY());
					direction = -1;
				}				
			}
		}
		return direction;
	}
	
	private int up_stop() {
		for(int i = 0; i < cell.getMap().length; i++){
			for(int j = 0; j < cell.getMap()[i].length; j++){
				
				if(cell.getMap()[player.getPlayerX()][player.getPlayerY() - 1] == 0) {
//					System.out.println((player.getPlayerX()) + " " + (player.getPlayerY()-1));
					direction = -1;;
				}
			}
		}
		return direction;
	}
	
	
	private int down_stop() {
		for(int i = 0; i < cell.getMap().length; i++){
			for(int j = 0; j < cell.getMap()[i].length; j++){
							
				if(cell.getMap()[player.getPlayerX()][player.getPlayerY() + 1] == 0) {
//					System.out.println(player.getPlayerX() + " " + (player.getPlayerY()+1));
					direction = -1;;
				}
			}
		}
		return direction;
	}


	public void actionPerformed(ActionEvent e) {
		
		
//		System.out.println(tickCount);
		// if "Play" button is pressed at the start screen, change game state to GAME
		if (e.getActionCommand() == "Play") 
			stateStr = "GAME";
		
		// if "Instruction" button is pressed at the start screen, change game state to INSTRUCTION
		if (e.getActionCommand() == "Instruction") 
			stateStr = "INSTRUCTION";
		
		// if "Go back" button is pressed at the instruction screen, change game state to START_SCREEN
		if (e.getActionCommand() == "Go back") 
			stateStr = "START_SCREEN";
		
		// if "Exit" button is pressed during the game, exit the game
		if (e.getActionCommand() == "Exit") {
			stateStr = "START_SCREEN";
			exit = true;
		}
		
//		if (e.getActionCommand() == "Replay") {
//			stateStr = "START_SCREEN";
//			replay = true;
//		}
			
		
		if(stateStr == "GAME") {
			
			tickCount ++;
			
			if (tickCount == TICK) {			//Changing TICK speeds up player
				/** @author kevin
				 * I'm trying to implement AI and player detection here but should probably 
				 * be more encapsulated, could have the move function pass in the state by value
				 */
			
				for(int i=0;i<10;i++) {
					for(int j=0;j<10;j++) {
						weightedMap[i][j] = 10000;
					}
				}	
				
				player.beacon(weightedMap, cell.getMap(),player.getPlayerX(),player.getPlayerY(),0);
				enemy.track(weightedMap);
				
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
			
			player.update();
			enemy.update();
			
		}
	
		
		repaint();
		/*
		if(replay){
			frame.dispose();
			try {
				frame = new Frame("276-game-project");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			replay = false;
//			exit = false;
		}
		*/
		
		if(exit) {
			frame.dispose();
			exit = false;
		}
	}
	
}
	