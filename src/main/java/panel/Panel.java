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
	
	private Timer timer;

	// booleans that control the keyboard
	private boolean up, down, left, right;
	
	private final static int START_SCREEN = -1;
	private final static int INSTRUCTION = 0;
	private final static int GAME = 1;

	// set initial state of the game
	public static int state = START_SCREEN;

	
	public Panel(JFrame frame) throws IOException {
		super();
		
		this.setBackground(Color.white);
		setPreferredSize(new Dimension(WIN_W, WIN_H));
		
		up= false;
		down = false;
		left = false;
		right = false;

		cover = new Cover();
		board = makeBoard();
		player = respawn();
		enemy = spawnEnemy();

		
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

	// method respawn: respawn player at (0, 0)
	public Player respawn() {
		return new Player(0, 0, 0, 0);

	}
	
	//method spawn: Puts the Enemy on the board diagonal to player
	public Enemy spawnEnemy() {
		return new Enemy(WIN_W-100, WIN_H-200, 0, 0);
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
			right = true;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			left = true;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			up = true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			down = true;
		
	}


	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			right = false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			left = false;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			up = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			down = false;
	}


	public void actionPerformed(ActionEvent e) {

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
		
	
		if (right) {
			player.move(player.playerSpeed, player.getYSpeed());	
			}
		
		if (left) {
			player.move(-player.playerSpeed, player.getYSpeed());
		}
		
		if(!(right || left)) {
			if(up) {
				player.move(player.getXSpeed(), -player.playerSpeed);
			}
			if(down) {
				player.move(player.getXSpeed(), player.playerSpeed);
			}
		}
	
		repaint();
	}
	
}
	