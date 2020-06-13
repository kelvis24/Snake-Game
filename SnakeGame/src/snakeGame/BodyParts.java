package snakeGame;

import java.awt.Color;
import java.awt.Graphics;

public class BodyParts {
	
	private int xCoor, yCoor, width, height;
	

	public BodyParts(int xCoor, int yCoor, int size) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.height = size;
		this.width = size;
		
	}
	
	public void tick() {
		
	}
	
    public void draw(Graphics g) {
    	g.setColor(Color.YELLOW);	
    	g.fillRect(xCoor*width, yCoor*height, width, height);
    
	} 
    
	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}

  	

}
