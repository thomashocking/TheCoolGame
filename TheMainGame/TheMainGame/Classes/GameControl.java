package TheMainGame.Classes;

import TheMainGame.Classes.*;

import java.util.Random;
import java.util.Scanner;


interface GameConstants {
	public static final long DEFAULT_SEED = 1;
}

public class GameControl implements GameConstants {
	HumanCharacter theHumanCharacter;
	Random generator;
	
	public GameControl() {
		this(DEFAULT_SEED);
	}
	
	public GameControl(long seed){
		generator = new Random(seed);
		theHumanCharacter = new HumanCharacter();		
	}
	
	public void startGame(){
		this.characterStart(theHumanCharacter);
		this.characterChoice();
	}
	
	private void characterStart(HumanCharacter theHumanCharacter){
		System.out.println("What is your name?");
		Scanner input = new Scanner(System.in);
		String humanName = input.next();
		theHumanCharacter.setName(humanName);
		theHumanCharacter.description();
	}
	
	
	//Prompt Choice from user...
	private void characterChoice(){
		Scanner input = new Scanner(System.in);
		int userInput;
		
		System.out.println("What would you like to do?");
		System.out.println("1 = Stats");
		System.out.println("2 = Dungeon");
		System.out.println("3 = Shop");
		
		userInput = input.nextInt();
		
		if(userInput == 1){
			theHumanCharacter.description();
		}
		else if(userInput == 2){
			System.out.println("You've entered in a dungeon");
			this.enterDungeon(theHumanCharacter);		
			}
		else if(userInput == 3){
			System.out.println("You've entered the shop!");
			//Shop code
		}
		else{
			System.out.println("You didn't enter in a valid command");
			System.out.println(userInput);
			
			while(userInput <= 0 || userInput > 4){
				System.out.println("What would you like to do?");
				System.out.println("1 = Stats");
				System.out.println("2 = Dungeon");
				System.out.println("3 = Shop");
				userInput = input.nextInt();
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
		//monster encounter code here...
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
				//human magic code here...
				combatDone = true;
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
}
