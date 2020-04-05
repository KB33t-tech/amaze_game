package integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import panel.Frame;

public class ITMainFrame {

	private Frame frame;
	
	@Test
	void testFrame() throws IOException {
		System.out.println("main");
		frame = new Frame("test-276-game-project");
		assertNotNull(frame);
	}
	
	
	@Test
	void testMain() {
		Frame.main(null);
		return;
	}
	
}
