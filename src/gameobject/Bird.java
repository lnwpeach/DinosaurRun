package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import util.Animation;

public class Bird extends Enemy {
	
	private int posX;
	private int posY;
	private int width;
	private int height;
	
	private Animation image;
	private MainCharacter mainCharacter;
	
	private Rectangle rectBound;
	
	public Bird(MainCharacter mainCharacter, int posX, Animation image) {
		this.posX = posX;
		this.posY = getPos();
		this.width = image.getFrame().getWidth() - 15;
		this.height = image.getFrame().getHeight() - 28;
		this.image = image;
		this.mainCharacter = mainCharacter;
		rectBound = new Rectangle();
	}
	
	public void update() {
		image.updateFrame();
		posX -= mainCharacter.getSpeedX();
	}
	
	public void draw(Graphics g) {
		g.drawImage(image.getFrame(), posX, posY - image.getFrame().getHeight(), null);
		g.setColor(Color.red);
//		Rectangle bound = getBound();
//		g.drawRect(bound.x, bound.y, bound.width, bound.height);
	}
	
	public Rectangle getBound() {
		rectBound = new Rectangle();
		rectBound.x = (int) posX + (image.getFrame().getWidth() - width)/2;
		rectBound.y = posY - image.getFrame().getHeight() + (image.getFrame().getHeight() - height)/2;
		rectBound.width = width;
		rectBound.height = height;
		return rectBound;
	}

	@Override
	public boolean isOutOfScreen() {
		if(posX < -image.getFrame().getWidth()) {
			return true;
		}
		return false;
	}
	
	public int getPos() {
		Random rand = new Random();
		int y;
		int pos = rand.nextInt(3);
		if(pos == 0) {
			y = 155;
		} else if(pos == 1) {
			y = 135;
		} else {
			y = 115;
		}
		return y;
	}
	
}
