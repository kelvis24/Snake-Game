package snakeGame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 500, HEIGHT = 500;
	private boolean running;
	private Thread thread;
	private BodyParts b;
	private Apple apple;
	private ArrayList<Apple> apples;
	private ArrayList<BodyParts> snakes;
	private Random r;
	private int xCoor = 10, yCoor = 10, size = 15, ticks = 0;
	private boolean up = false, down = false, right = true, left = false;
	
	
	public GamePanel() {
		
		setFocusable(true);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		snakes = new ArrayList<BodyParts>();
		apples = new ArrayList<Apple>();
		r = new Random();
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
    	
    	if (apples.size() == 0) {
    	
    		
    		int xCoor = r.nextInt(49);
       		int yCoor = r.nextInt(49);
       		
       		apple = new Apple(xCoor, yCoor, 10);
       		apples.add(apple);
    	}
    	
    	for (int i = 0; i < apples.size(); i++) {
    		
    		if (xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) {
    			size++;
    			apples.remove(i);
    			i++;
    			
    		}
			
		}
//    	collision on snake body part
    	for (int i = 0; i < snakes.size(); i++) {
		
    		if (xCoor == snakes.get(i).getxCoor() && yCoor == snakes.get(i).getyCoor()) {
    			if (i != snakes.size() - 1) {
    				System.out.println("Game Over");
    	    		stop();
    			}
    		}
		}
    	 
//    	collision on border
//    	if (xCoor < 0 || xCoor > 49 || yCoor < 0 || yCoor > 49) {
//    		System.out.println("Game Over");
//    		stop();
//    	}	
	
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
    	for (int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(g);
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
