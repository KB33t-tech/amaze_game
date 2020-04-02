package board;

import org.junit.jupiter.api.Test;

import panel.Panel;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.io.IOException;



public class TestWallDetection {

	private Player p;
	private Cell c;
	private Panel panel;
	
	@BeforeEach
	void init() throws IOException {
		p = new Player();
		c = new Cell();
		panel = new Panel(null);
	}
	
	
	@Test
	void testRightWall() {
		int yPos = p.getPlayerY() + 1;
//		p.move(1, 1);
		
		for(int i = 0; i < c.getMap().length; i++){
			for(int j = 0; j < c.getMap()[i].length; j++){
				System.out.print(c.getMap()[j][i] + " ");
			}
			System.out.println();
		}
		
	}
	
}
