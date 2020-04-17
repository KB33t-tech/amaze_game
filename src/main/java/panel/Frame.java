

package panel;


import java.awt.BorderLayout;

import java.io.IOException;

import javax.print.DocFlavor.URL;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This class extends {@link JFrame}.
 * Is creates a Panel object and adds it to he JFrame window.
 */
public class Frame extends JFrame{
	
	public Frame(String title) throws IOException {
		super(title);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		Panel panel = new Panel(this);
		panel.setLayout(null);
		this.add(panel);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	/**
	 * This is the main method.
	 * @param args	an args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame app = new Frame("276-game-project");
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
		);
	}

}
