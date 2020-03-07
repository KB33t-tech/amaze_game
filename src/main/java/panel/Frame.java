// Coded by Vera Yang

package panel;


import java.awt.BorderLayout;
import java.io.IOException;

import javax.print.DocFlavor.URL;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Frame extends JFrame{
	
	public Frame(String title) throws IOException {
		super(title);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		Panel panel = new Panel(this);
		panel.setLayout(null);
		this.add(panel);
		this.pack();
		this.setVisible(true);	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame app = new Frame("276-gmae-project");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
		);
	}

}
