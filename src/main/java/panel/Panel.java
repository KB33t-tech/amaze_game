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

/**
 * Everything in the game is drawn on this JPanel object and then added to the JFrame window.
 * JPanel is like a piece of paper that gets added to the drawing board (JFrame).
 * Therefore we can have several JPanel objects that draw different things, but for this game we only have one.
 */
public class Panel extends JPanel implements ActionListener, KeyListener {
	
	public final static int WIN_X = 0;	// x-coordinate of the window
	public final static int WIN_Y = 0;	// y-coordinate of the window
	public final static int WIN_W = 600;	// width of the window
	public final static int WIN_H = 700;	// height of the window
	public final static int BOARD_H = 600;	// height of the board (space where Player moves)
	
	private JFrame frame;	// a JFrame object
	public static JButton playButton, insButton, gobackButton, exitButton, replayButton;	// different JButton objects
	
	private Player player;	// a Player object
	private Enemy enemy;	// a Moving Enemy object
	private Cell cell;		// a Cell object
	
	private ChangeState cs;	// a ChangeState object
	public static String stateStr;	// string that stores the current state of the game
	private WallDetection wd;
	
	private int tickCount;	// keeps track of each TICK of the game
	private boolean replay, exit;	// boolean that keeps track if replay or exit button is pressed
	
	private Timer timer;

	// constants that control the keyboard
	private final static int UP = 0;
	private final static int DOWN = 1;
	private final static int LEFT = 2;
	private final static int RIGHT = 3;
	
	// direction is -1 if Player does not move, otherwise it is assigned to the above constants
	private int direction;

	// value of each TICK of the game
	public final static int TICK = 30;
	
	// a weighted map for calculating the distance between Player and Moving Enemy
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

	
	/**
	 * The constructor sets the initial state of JPanel.
	 * @param frame 			Java's JFrame window
	 * @throws IOException		if an input or output exception occurred
	 */
	public Panel(JFrame frame) throws IOException {
		super();
		String startStateString = "START_SCREEN";//Modify this so that you can debug states easier 
		this.setBackground(Color.white);	// set the background colour of the window
		setPreferredSize(new Dimension(WIN_W, WIN_H));	// set the size of the window
		
		direction = -1;		// Player does not move when the game starts
		
		stateStr = startStateString;	// set initial state of the game
		cs = new ChangeState();

		player = new Player();
		enemy = spawnEnemy();
		cell = new Cell();
		wd = new WallDetection();

		tickCount = 0;	// each tick starts incrementing at 0
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
		
		// add JButtons buttons
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


	/**
	 * This method allows drawing on JPanel.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// switches the state of the game by passing the state string
		// and draws according to the state
		cs.switchState(g2, State.valueOf(stateStr));
		
		enemyCollision();

	}
	
	
	public String enemyCollision() {
		// detects if player and moving enemy touch
		if(Math.abs(player.getPosX() - enemy.getPosX())<5 &&
				Math.abs(player.getPosY() - enemy.getPosY())<5) {
			
			stateStr = "LOSE";
		}
		 return stateStr;
	}

	/**
	 * This is a method from KeyListener.
	 */
	public void keyTyped(KeyEvent e) {
		
	}

	/**
	 *  This is a method from KeyListener.
	 *  It keeps track of which arrow key is pressed and assign the direction to Player according to the arrow key.
	 */
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

	/**
	 * This is a method from KeyListener.
	 */
	public void keyReleased(KeyEvent e) {
		
	}



	/**
	 * This is a method from ActionListener.
	 * This method detects which button is pressed and changes the state of the game according to the buttons.
	 * It also controls the movement of Player and Moving Enemy in each TICK of the game.
	 */
	public void actionPerformed(ActionEvent e) {
		
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
			

		// if the state of the game is "GAME"
		if(stateStr == "GAME") {
			
		
			tickCount ++;	// increment tickCount
			
			// during each TICK of the game:
			if (tickCount == TICK) {			//Changing TICK speeds up player
				/** @author kevin
				 * I'm trying to implement AI and player detection here but should probably 
				 * be more encapsulated, could have the move function pass in the state by value
				 */
				cell.updateTime();
				
				for(int i=0;i<10;i++) {
					for(int j=0;j<10;j++) {
						weightedMap[i][j] = 10000;
					}
				}	
				
				player.beacon(weightedMap, cell.getMap(), player.getPlayerX(),player.getPlayerY(),0);
				enemy.track(weightedMap);	// Moving Enemy starts tracking Player
				
				if(player.getPlayerX() != 9) {	// prevents the player from going out of the screen when at the exit
					if (direction == RIGHT) {	// if the right arrow key is pressed
						if(!wd.right_stop()) {	// if the right cell is not a wall
							player.move(1, 0);	// move Player one cell to the right
						}
					}
				}
				
				if(player.getPlayerX() != 0) {	// prevents the player from going out of the screen when at the start point
					if (direction == LEFT) {	// if the left arrow key is pressed	
						if(!wd.left_stop()) {	// if the left cell is not a wall
							player.move(-1, 0);	// move Player one cell to the left
						}	
					}
				
				}
				
				if(direction == UP) {	// if the up arrow key is pressed
					if(!wd.up_stop()) {	// if the cell above is not a wall
						player.move(0,-1);	// move Player one cell up
					}
				}
				
				if(direction == DOWN) {		// if the down arrow key is pressed
					if(!wd.down_stop()) {	// if the cell below is not a wall
						player.move(0, 1);	// move Player one cell down
					}
				}
				
				tickCount = 0;		// reset tickCount after each TICK
				direction = -1;		// reset direction to -1 after Player is done moving one cell
			}
			
			player.update();	// update the position of Moving Enemy
			enemy.update();		// update the position of Player
			
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
		
		// if exit is pressed, dispose the JFrame window
		if(exit) {
			frame.dispose();
			exit = false;
		}
	}
	
}
	