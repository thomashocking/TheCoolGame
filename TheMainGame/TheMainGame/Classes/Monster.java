package TheMainGame.Classes;


public abstract class Monster {
	protected int health;
	protected int attack;
	protected int exp;
	protected int gold;
	final private String name;
	
	protected Monster(String name){
		this.name = name;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public void setHealthOnAttack(int health){
		this.health -= health;
	}
	
	public int getHealth(){	
		return health;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public String getName() {
		return name;
	}
	
	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public abstract void description();
	public abstract void attack(HumanCharacter theCharacter);
	
}
