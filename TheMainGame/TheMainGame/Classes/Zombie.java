package TheMainGame.Classes;

import java.util.Random;

public class Zombie extends Monster {
	
	public Zombie(){
		this.statGeneration();
	}
	
	private void statGeneration() {
		this.setName("Zombie");
		Random generator = new Random();
		int randNum = generator.nextInt(5) + 1;
		this.setHealth(randNum);
		randNum = generator.nextInt(3) + 1;
		this.setAttack(randNum);
		randNum = generator.nextInt(10) + 1;
		this.setGold(randNum);
		randNum = generator.nextInt(15) + 1;
		this.setExp(randNum);
	}
	
	@Override
	public void description() {
		System.out.println("It is a " + this.getName());
		System.out.println("It's health is: " + this.getHealth());
		System.out.println("It's attack is: " + this.getAttack());
	}
	
	@Override
	public void attack(HumanCharacter theCharacter){
		int attack = this.getAttack();
		theCharacter.setHealthOnAttack(attack);
		System.out.println("The " + this.getName() + " attacks!");
		System.out.println("You have taken " + attack + " damage!");
		if(theCharacter.getHealth() <= 0){
			theCharacter.setHealth(0);
		}
		System.out.println("You are now at " + theCharacter.getHealth() + " health!");
	}
	
}
