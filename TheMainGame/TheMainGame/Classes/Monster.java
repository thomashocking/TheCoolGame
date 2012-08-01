package TheMainGame.Classes;

public abstract class Monster {
	private int health;
	private int attack;
	private int exp;
	private int gold;
	private String name;
	
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

	public void setName(String name) {
		this.name = name;
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
