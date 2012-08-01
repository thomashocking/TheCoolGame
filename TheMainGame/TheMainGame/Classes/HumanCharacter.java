package TheMainGame.Classes;

import java.util.Random;

public class HumanCharacter implements GameConstants {
	
	//Human character attributes 
	private int health;
	private int mana;
	private int exp;
	private int gold;
	private String name;
	private int attack;
	
	//Human Constructor
	public HumanCharacter(){
		this.statGeneration();
	}
	//Human character methods
	public void setHealth(int health){
		this.health = health;
	}
	
	public void setHealthOnAttack(int health){
		this.health -= health;
	}
	
	public int getHealth(){
		return health;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public void setExpOnLoot(int exp){
		this.exp += exp;
	}
	
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void setGoldOnLoot(int gold){
		this.gold += gold;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void description(){
		System.out.println("Your characters name is: " + name);
		System.out.println("Your health is: " + health);
		System.out.println("Your attack is: " + attack);
		System.out.println("Your mana is: " + mana);
		System.out.println("Your exp is: " + exp);
		System.out.println("Your gold is: " + gold);
	}
	
	public boolean meleeAttack(Monster theMonster){
		int attack = this.getAttack();
		boolean monsterDead = false;
		
		theMonster.setHealthOnAttack(attack);
		System.out.println("The " + theMonster.getName() + " has taken " + attack + " damage!");
		if(theMonster.getHealth() <= 0){
			theMonster.setHealth(0);
			monsterDead = true;
		System.out.println("The moster is now at " + theMonster.getHealth() + " health!");
		}
		
		if(monsterDead){
			System.out.println("The " + theMonster.getName() + " has died!");
			return monsterDead;
		}
	
		return false;
	}
	
	private void statGeneration(){
		Random generator = new Random();
		int randNum = generator.nextInt(10) + 6;
		this.setHealth(randNum);
		randNum = generator.nextInt(10) + 1;
		this.setMana(randNum);
		randNum = generator.nextInt(6) + 1;
		this.setAttack(randNum);
		this.setGold(0);
		this.setExp(0);
	}
}

