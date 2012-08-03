package TheMainGame.Classes;

import TheMainGame.Classes.*;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


interface GameConstants {
	public static final long DEFAULT_SEED = 1;
	public static final int EXP_CAP = 100;
	public static final int HEALTH_POTION_COST = 15;
	public static final int MANA_POTION_COST = 20;
}

public class GameControl implements GameConstants {
	HumanCharacter theHumanCharacter;
	Random generator;
	Shop theShop;
	
	public GameControl() {
		this(DEFAULT_SEED);
	}
	
	public GameControl(long seed){
		generator = new Random(seed);
		theHumanCharacter = new HumanCharacter();	
		theShop = new Shop();
	}
	
	public void startGame(){
		this.characterStart(theHumanCharacter);
		this.characterChoice();
	}
	
	private void characterStart(HumanCharacter theHumanCharacter){
		System.out.println("What is your name?");
		System.out.print("Name:");
		Scanner input = new Scanner(System.in);
		String humanName = input.next();
		theHumanCharacter.setName(humanName);
		theHumanCharacter.description();
	}
	
	
	//Prompt Choice from user...
	private void characterChoice(){
		boolean endGame = false;
		while(!endGame){
		Scanner input = new Scanner(System.in);
		String userInput;
		
		this.levelCheck(theHumanCharacter);
		
		System.out.println("What would you like to do?");
		System.out.println("1 = Stats");
		System.out.println("2 = Dungeon");
		System.out.println("3 = Shop");
		System.out.print("Command:");
	
		userInput = input.next();
		
		if(userInput.compareTo("1") == 0){
			theHumanCharacter.description();
		}
		else if(userInput.compareTo("2") == 0){
			System.out.println("You've entered in a dungeon");
			this.enterDungeon(theHumanCharacter);		
			}
		else if(userInput.compareTo("3") == 0){
			System.out.println("You've entered the shop!");
			theShop.enterShop(theHumanCharacter);
			}
		else{
			
			System.out.println("You didn't enter in a valid command");
			while((userInput.compareTo("1") == 1 )||( userInput.compareTo("2") == 1 )||( userInput.compareTo("3") == 1)){
				System.out.println("What would you like to do?");
				System.out.println("1 = Stats");
				System.out.println("2 = Dungeon");
				System.out.println("3 = Shop");
				userInput = input.next();
			}
			
		}
	}
}
	//Create dungeon code
	private void enterDungeon(HumanCharacter theHumanCharacter){
		
		int randNum = generator.nextInt(100) + 1;
		if(randNum >= 100){
			//treasure chest code
		}
		else{
			this.monsterEncouter(theHumanCharacter);
		}
		//Enter in treasure and monster code here.
	}
	
	private void enterShop(HumanCharacter theHumanCharacter){
		System.out.println("Welcome to the shop!");
		//Enter in shop code.
	}
	
	private void monsterEncouter(HumanCharacter theHumanCharacter){
		//monster encounter code done.
		Monster generatedMonster = this.monsterArrayGenerator();
		System.out.println("You've encountered a " + generatedMonster.getName() + "!");
		this.humanAction(theHumanCharacter, generatedMonster);
	}
	
	private Monster monsterArrayGenerator(){
		Monster[] monsterArray = new Monster[3];
		monsterArray[0] = new Zombie();
		monsterArray[1] = new Ghost();
		monsterArray[2] = new SerialKiller();
		
		int randNum = generator.nextInt(3) + 0;
		return (monsterArray[randNum]);
	}
	
	private void humanAction(HumanCharacter theHumanCharacter,Monster theMonster){
		System.out.println("What would you like to do?");
		System.out.println("Type 'melee' to use melee attack");
		System.out.println("Type 'magic' to use magic attack");
		System.out.println("Type 'potion' to use a health, or mana potion.");
		System.out.println("Type 'info' followed by either 'me' or 'monster' to see stats");
		String choice;
		Scanner input = new Scanner(System.in);
		
		boolean combatDone = false;
		while(!combatDone){
			System.out.print("Command: ");
			choice = input.nextLine();
			if(choice.compareTo("melee") == 0){
				//Code is complete.
				combatDone = theHumanCharacter.meleeAttack(theMonster);
				this.willMonsterAttack(theHumanCharacter, theMonster);
			}
			else if(choice.compareTo("magic") == 0){
				
				//Code is complete.
				combatDone = theHumanCharacter.magicAttack(theMonster);
				this.willMonsterAttack(theHumanCharacter, theMonster);
			}
			else if(choice.compareTo("potion") == 0){
				theHumanCharacter.usePotion();
			}
			else if(choice.compareTo("info me") == 0){
				theHumanCharacter.description(); 
			}
			else if(choice.compareTo("info monster") == 0){
				theMonster.description();
			}
			else{
				System.out.println("You've entered in a invalid command");
			}
		}
		if(combatDone){
			this.distributeLoot(theHumanCharacter, theMonster);
		}
	}
	
	public void distributeLoot(HumanCharacter theCharacter, Monster theMonster){
		this.distributeLootOnKill(theCharacter, theMonster);
	}
	
	private void distributeLootOnKill(HumanCharacter theCharacter, Monster theMonster){
		int newExp = theMonster.getExp();
		int newGold = theMonster.getGold();
		theCharacter.setExpOnLoot(newExp);
		theCharacter.setGoldOnLoot(newGold);
		System.out.println("You've looted " + newGold + " gold!");
		System.out.println("You've gained " + newExp + " exp!");
	}
	
	private void willMonsterAttack(HumanCharacter theCharacter,Monster theMonster){
		int willMonsterAttack;
		willMonsterAttack = generator.nextInt(100) + 1;
		if(willMonsterAttack >= 45){
			theMonster.attack(theCharacter);
		}
		else {
			System.out.println("You've dodged the monsters attack!");
		}
	}
	
	private void levelCheck(HumanCharacter theHumanCharacter){
		if(theHumanCharacter.getExp() >= EXP_CAP){
			System.out.println("LEVEL UP!!!");
				theHumanCharacter.levelUp();
			
		}
	}
}
