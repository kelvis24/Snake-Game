package snakeGame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 500, HEIGHT = 500;
	private boolean running;
	private Thread thread;
	private BodyParts b;
	private ArrayList<BodyParts> snakes;
	private int xCoor = 10, yCoor = 10, size = 5, ticks = 0;
	private boolean up = false, down = false, right = true, left = false;
	
	public GamePanel() {
		
		setFocusable(true);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		snakes = new ArrayList<BodyParts>();
		start();
		
	}
	
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}

    public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public void tick() {
    	if (snakes.size() == 0) {
    		b = new BodyParts(xCoor, yCoor, 10);
    		snakes.add(b);
    		
    	}
    	
    	ticks++;
    	
    	if (ticks > 250000) {
    		if (up) yCoor--;
    		if (down) yCoor++;
    		if (right) xCoor++;
    		if (left) xCoor--;
    		
    		ticks = 0;
    		b = new BodyParts(xCoor, yCoor, 10);
    		snakes.add(b);
    		
    		if (snakes.size() > size) {
    			snakes.remove(0);
    		}
    		
    		
    	}
	
    }
    
    public void paint(Graphics g) {
    	
    	g.clearRect(0, 0, WIDTH, HEIGHT);
    	
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 0, WIDTH, HEIGHT);
    	
    	
    	for (int i = 0; i <WIDTH/10; i++) {
			g.drawLine(i*10, 0, i*10, HEIGHT);
		}
		
    	for (int i = 0; i <HEIGHT/10; i++) {
			g.drawLine(0, i*10, WIDTH, i*10);
		}
    	
    	for (int i = 0; i < snakes.size(); i++) {
    		snakes.get(i).draw(g);
		}
	}
    
    
    public void run() {
    	
    	while (running) {
    	
    		tick();
    		repaint();
    	}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		
		if (key == com.sun.glass.events.KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;
		}
		
		if (key == com.sun.glass.events.KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;
		}
		
		if (key == com.sun.glass.events.KeyEvent.VK_UP && !down) {
			up = true;
			left = false;
			right = false;
		}
		
		if (key == com.sun.glass.events.KeyEvent.VK_DOWN && !up) {
			right = false;
			left = false;
			down = true;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
