package gameobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.Animation;
import util.Resource;

public class EnemiesManager {
	
	private BufferedImage cactus1;
	private BufferedImage cactus2;
	private Animation bird;
	private Random rand;
	
	private List<Enemy> enemies;
	private MainCharacter mainCharacter;
	
	public EnemiesManager(MainCharacter mainCharacter) {
		rand = new Random();
		cactus1 = Resource.getResouceImage("data/cactus1.png");
		cactus2 = Resource.getResouceImage("data/cactus2.png");
		bird = new Animation(160);
		bird.addFrame(Resource.getResouceImage("data/bird1.png"));
		bird.addFrame(Resource.getResouceImage("data/bird2.png"));
		enemies = new ArrayList<Enemy>();
		this.mainCharacter = mainCharacter;
		createEnemy();
	}
	
	public void update() {
		for(Enemy e : enemies) {
			e.update();
		}
		Enemy enemy = enemies.get(enemies.size()-1);
		if(enemy.isOutOfScreen()) {
			enemies.clear();
			createEnemy();
			
		}
	}
	
	public void draw(Graphics g) {
		for(Enemy e : enemies) {
			e.draw(g);
		}
	}
	
	private Enemy randomEnemy(int PosX) {
		int type = rand.nextInt(3);
		if(type == 0) {
			return new Cactus(mainCharacter, PosX, cactus1.getWidth() - 10, cactus1.getHeight() - 10, cactus1);
		} else if(type == 1) {
			return new Cactus(mainCharacter, PosX, cactus2.getWidth() - 10, cactus2.getHeight() - 10, cactus2);
		}
		else {
			return new Bird(mainCharacter, PosX, bird);
		}	
	}
	
	private void createEnemy() {
		int numEnemy = rand.nextInt(3);
		int posX = 800, nextPosX = 350;
		if(mainCharacter.getSpeedX() >= 8)
			nextPosX = 400;
		for(int i=0;i<=numEnemy;i++) {
			enemies.add(randomEnemy(posX));
			posX += nextPosX;
		}	
	}
	
	public boolean isCollision() {
		for(Enemy e : enemies) {
			if (mainCharacter.getBound().intersects(e.getBound())) {
				return true;
			}
		}
		return false;
	}
	
	public void reset() {
		enemies.clear();
		createEnemy();
	}
	
}
