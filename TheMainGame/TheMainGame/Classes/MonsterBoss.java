package TheMainGame.Classes;

import java.util.Random;

public class MonsterBoss extends Monster {

	public MonsterBoss(String name) {
		super(name);
		this.statGeneration();
	}

	@Override
	public void description() {
		System.out.println("The Final Boss...");
		System.out.println("It's health is: " + this.getHealth());
		System.out.println("It's attack is: " + this.getAttack());
	}

	@Override
	public boolean attack(HumanCharacter theCharacter) {
		boolean isDead = false;
		theCharacter.setHealthOnAttack(this.getAttack());
		System.out.println("The " + this.getName() + " attacks!");
		System.out.println("You have taken " + attack + " damage!");
		if(theCharacter.getHealth() <= 0){
			theCharacter.setHealth(0);
			isDead = true;
			return isDead;
		}
		System.out.println("Your health is now: " + theCharacter.getHealth());
		return false;
	}

	private void statGeneration(){
		Random generator = new Random();
		int randNum = generator.nextInt(20) + 13;
		this.setHealth(randNum);
		randNum = generator.nextInt(9) + 4;
		this.setAttack(randNum);
		randNum = generator.nextInt(8) + 4;
		this.setGold(randNum);
		randNum = generator.nextInt(20) + 8;
		this.setExp(randNum);
	}
}
