package snakeGame;

import javax.swing.JFrame;

public class SnakeGame {
	
	public SnakeGame() {
		
		JFrame frame = new JFrame();
		GamePanel gamepanel = new GamePanel();
		
		frame.add(gamepanel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("SNAKE GAME");
		
		
		
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
	}
	
	

	public static void main(String args[]) {
		new SnakeGame();
		
		
	}
}