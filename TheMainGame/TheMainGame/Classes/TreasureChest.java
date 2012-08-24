package TheMainGame.Classes;

import java.util.Random;

import TheMainGame.Classes.*;

public class TreasureChest implements GameConstants {
	private static int gold;
	private static int potion;
	Random generator;
	
	public void setPotion(int numPotion){
		potion = numPotion;
	}
	
	
	public int getPotion(){
		return potion;
	}
	
	public void setGold(int numOfGold){
		this.gold = numOfGold;
	}
	
	public int getGold(){
		return gold;
	}
	
	public TreasureChest(long seed){
		generator = new Random(seed);
	}
	
	public int open(){
		System.out.println("A TREASURE CHEST!");
		this.setRandomizeDrop();
		return this.getRandomizeDrop();
	}
	private int getRandomizeDrop(){
		if(this.getGold() == 0){
			System.out.println("Its a potion!");
			return this.getPotion();
		}
		else{
			System.out.println("Its gold!");
			return this.getGold();
		}
	}
	private void setRandomizeDrop(){
		int randNum = generator.nextInt(100) + 1;
		if(randNum > 25){
			this.setPotion(1);
		}
		else{
			this.setGold(randNum);
		}
	}
}
