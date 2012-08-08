package TheMainGame.Classes;

import java.util.Random;

public class Ghost extends Monster {

	public Ghost(){
		super("Ghost");
		this.statGeneration();
	}
	
	private void statGeneration(){
		Random generator = new Random();
		int randNum = generator.nextInt(6) + 1;
		this.setHealth(randNum);
		randNum = generator.nextInt(2) + 1;
		this.setAttack(randNum);
		randNum = generator.nextInt(8) + 4;
		this.setGold(randNum);
		randNum = generator.nextInt(20) + 8;
		this.setExp(randNum);
	}
	
	@Override
	public void description() {
		System.out.println("It is a " + this.getName());
		System.out.println("It's health is: " + this.getHealth());
		System.out.println("It's attack is: " + this.getAttack());
	}
	
	@Override
	public boolean attack(HumanCharacter theCharacter){
		boolean isDead = false;
		int attack = this.getAttack();
		theCharacter.setHealthOnAttack(attack);
		System.out.println("The " + this.getName() + " attacks!");
		System.out.println("You have taken " + attack + " damage!");
		if(theCharacter.getHealth() <= 0){
			theCharacter.setHealth(0);
			isDead = true;
			return isDead;
		}
		System.out.println("You are now at " + theCharacter.getHealth() + " health!");
		return false;
	}
	

}
